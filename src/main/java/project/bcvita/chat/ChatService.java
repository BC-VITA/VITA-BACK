package project.bcvita.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.chat.dto.ChatListResponse;
import project.bcvita.chat.dto.ChatMessageInfoResponse;
import project.bcvita.chat.dto.ChatMessageRequest;
import project.bcvita.chat.dto.ChatMessageResponse;
import project.bcvita.chat.entity.ChatRoom;
import project.bcvita.chat.entity.ChattingMessage;
import project.bcvita.user.entity.DesignatedBloodWriteUser;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.DesignatedBloodWriteRepository;
import project.bcvita.user.repository.DesignatedBloodWriteUserRepository;
import project.bcvita.user.repository.UserRepository;
import project.bcvita.user.service.UserService;

import javax.servlet.http.HttpSession;
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

    //채티방 list
    public List<ChatListResponse> chatList(String userId) {
        User user = userRepository.findByUserID(userId);
        List<ChatRoom> boardWriterList = chatRoomRepository.findAllByBoardWriter(user);
        List<ChatRoom> boardSeeUserList = chatRoomRepository.findAllByBoardSeeUser(user);
        List<ChatListResponse> list = new ArrayList<>();
        for (ChatRoom chatRoom : boardWriterList) {
            list.add(new ChatListResponse(chatRoom.getId(),chatRoom.getBoardWriter().getUserNumber(), chatRoom.getBoardSeeUser().getUserNumber()
                    ,chatRoom.getDesignatedBloodWriteUser().getDesignatedBloodWrite().getTitle(),chatRoom.getDesignatedBloodWriteUser().getCreatedAt()));
        }
        for (ChatRoom chatRoom : boardSeeUserList) {
            list.add(new ChatListResponse(chatRoom.getId(),chatRoom.getBoardWriter().getUserNumber(), chatRoom.getBoardSeeUser().getUserNumber(),
                    chatRoom.getDesignatedBloodWriteUser().getDesignatedBloodWrite().getTitle(), chatRoom.getDesignatedBloodWriteUser().getCreatedAt()));
        }
        return list;
    }

    public ChatMessageInfoResponse detailRoom(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).get();
        List<ChattingMessage> messageList = chatMessageRepository.findAllByChatRoom(chatRoom);
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
        User  sender =  userRepository.findByUserID(chatMessageRequest.getSenderId());
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


}
