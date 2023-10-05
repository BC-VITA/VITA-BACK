package project.bcvita.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.chat.dto.*;
import project.bcvita.chat.entity.ChatRoom;
import project.bcvita.chat.entity.ChattingMessage;
import project.bcvita.user.entity.DesignatedBloodWriteUser;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.DesignatedBloodWriteUserRepository;
import project.bcvita.user.repository.UserRepository;
import project.bcvita.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final DesignatedBloodWriteUserRepository bloodWriteUserRepository;


    //채팅방 list
    public List<ChatListResponse> chatList(String userId) {
        User user = userRepository.findByUserID(userId);
        List<ChatRoom> boardWriterList = chatRoomRepository.findAllByBoardWriter(user);
        List<ChatRoom> boardSeeUserList = chatRoomRepository.findAllByBoardSeeUser(user);
        List<ChatListResponse> list = new ArrayList<>();
        for (ChatRoom chatRoom : boardWriterList) {
                list.add(new ChatListResponse(chatRoom.getId(), chatRoom.getBoardWriter().getUserID(), chatRoom.getBoardSeeUser().getUserID()
                        , chatRoom.getDesignatedBloodWriteUser().getDesignatedBloodWrite().getTitle(), chatRoom.getDesignatedBloodWriteUser().getCreatedAt(),
                        chatRoom.getDesignatedBloodWriteUser().getDesignatedBloodWrite().getId(), chatRoom.getIsAgree()));
        }
        for (ChatRoom chatRoom : boardSeeUserList) {
            list.add(new ChatListResponse(chatRoom.getId(),chatRoom.getBoardWriter().getUserID(), chatRoom.getBoardSeeUser().getUserID(),
                    chatRoom.getDesignatedBloodWriteUser().getDesignatedBloodWrite().getTitle(), chatRoom.getDesignatedBloodWriteUser().getCreatedAt(),
                    chatRoom.getDesignatedBloodWriteUser().getDesignatedBloodWrite().getId(), chatRoom.getIsAgree()));
        }
        return list;

    }


    public ChatMessageInfoResponse detailRoom(Long roomId, String accountId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).get();
        User user = userRepository.findByUserID(accountId);
        List<ChattingMessage> messageList = chatMessageRepository.findAllByChatRoom(chatRoom);
        messageList.stream().filter(messsage -> messsage.getSender() != user)
            .forEach(message -> message.updateIsRead(true));
        List<ChatMessageResponse> chatMessageInfoList = messageList.stream().map(x ->
                        new ChatMessageResponse(x.getSender().getUserID(),
                                x.getSender().getUserName(),x.getReceiver().getUserID(),
                                x.getReceiver().getUserName(),x.getMessage(),x.getSendTime()))
                .collect(Collectors.toList());

        return new ChatMessageInfoResponse(chatRoom.getDesignatedBloodWriteUser().getDesignatedBloodWrite().getTitle(),
                chatRoom.getId(),chatMessageInfoList);
    }

    public String deleteRoom(Long roomId) {
        chatRoomRepository.deleteById(roomId);
        return "삭제 됨";
    }

    public ChatMessageResponse chatSend(ChatMessageRequest chatMessageRequest) {
        //1. 메시지를 보냈을때 해당 채팅방이 없을 경우 생성
        ChatRoom chatRoom = chatRoomRepository.findById(chatMessageRequest.getRoomId()).orElse(null);
        User sender =  userRepository.findByUserID(chatMessageRequest.getSenderId());
        User recevier =  userRepository.findByUserID(chatMessageRequest.getReceiverId());
        DesignatedBloodWriteUser designatedBloodWriteUser = bloodWriteUserRepository.findById(chatMessageRequest.getBoardId()).orElse(null);
        if(chatRoom == null) {
            //채팅방 존재하지 않으면 게시글 본 사람이 senderId, 게시글 작성자가 receiverId
            ChatRoom createRoom = new ChatRoom(recevier, sender, designatedBloodWriteUser);
            chatRoom = chatRoomRepository.save(createRoom);
        }

        //메시지 전송
        ChattingMessage chattingMessage = new ChattingMessage(chatRoom, sender, recevier, chatMessageRequest.getMessage());
        chatMessageRepository.save(chattingMessage);
        return new ChatMessageResponse(sender.getUserID(),sender.getUserName()
                ,recevier.getUserID(),recevier.getUserName(), chatMessageRequest.getMessage(), chattingMessage.getSendTime());

    }


    @Transactional
    public String agreeOrCancel(ChatApproveOrCancelRequest chatApproveOrCancelRequest) {
        User user = userRepository.findByUserID(chatApproveOrCancelRequest.getUserId());
        ChatRoom findRoom = chatRoomRepository.findByIdAndBoardWriter(chatApproveOrCancelRequest.getRoomId(), user);
        findRoom.updateIsAgree(chatApproveOrCancelRequest.getIsAgree());
        return findRoom.getIsAgree() == true ? "수락됨" : "취소됨";
    }


    //채팅방 roomId api
    public List<ChatResponse> getChatRoomId(ChatRoomIdRequest chatRoomIdRequest) {
        User user = userRepository.findByUserID(chatRoomIdRequest.getUserId());
        List<ChatRoom> boardWriterList = chatRoomRepository.findAllByBoardWriterOrBoardSeeUser(user);
        boardWriterList = chatRoomRepository.findAllByBoardWriter(user);
        boardWriterList = chatRoomRepository.findAllByBoardSeeUser(user);

        List<ChatResponse> roomIds = new ArrayList<>();

        for (ChatRoom chatRoom : boardWriterList) {
           roomIds.add(new ChatResponse(chatRoom.getId(), chatRoom.getDesignatedBloodWriteUser().getId(), chatRoom.getBoardWriter().getUserID(), chatRoom.getBoardSeeUser().getUserID()));
        }
        return roomIds;
    }
    public int alarmCount(String userId) {
        User user = userRepository.findByUserID(userId);
        return chatMessageRepository.alramList(user).size();
    }

    public List<AlarmMessageResponse> getAlarmMessage(String userId) {
        User user = userRepository.findByUserID(userId);
        List<AlarmMessageResponse> response = new ArrayList<>();
        List<Long> alarmMessage = new ArrayList<>();
        chatMessageRepository.getAlarmMessage(user)
            .forEach(message -> {
                if(!alarmMessage.contains(message.getChatRoomId())) {
                    AlarmMessageResponse alarmMessageResponse = new AlarmMessageResponse(message);
                    ChatRoom chatRoom = chatRoomRepository.findById(message.getChatRoomId())
                        .orElse(null);
                    alarmMessage.add(message.getChatRoomId());
                    if(chatRoom != null) {
                        String senderName = chatRoom.getSendUser();
                        String bloodBoardTitle = chatRoom.getBloodBoardTitle();
                        alarmMessageResponse.updateInfo(bloodBoardTitle, senderName);
                        response.add(alarmMessageResponse);
                    }
                }
            });
        return response;
    }
}