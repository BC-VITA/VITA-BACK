//package project.bcvita.user.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Data;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import project.bcvita.user.dto.ChatMessage;
//import project.bcvita.user.dto.ChatRoom;
//import project.bcvita.user.dto.MessageType;
//import project.bcvita.user.dto.request.ChatMessageRequestDto;
//import project.bcvita.user.dto.request.ChatRoomRequestDto;
//import project.bcvita.user.dto.response.ChatMessageResponse;
//import project.bcvita.user.dto.response.ChatRoomResponse;
//import project.bcvita.user.entity.*;
//import project.bcvita.user.repository.*;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.lang.reflect.Member;
//import java.time.LocalDateTime;
//import java.util.*;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class ChatService {
//   // private final SimpMessageSendingOperations simpMessageSendingOperations;
//    private final DesignatedBloodWriteUserRepository designatedBloodWriteUserRepository;
//    private final BloodChatRoomRepository bloodChatRoomRepository;
//
//    private final BloodChatMessageRepository bloodChatMessageRepository;
//
//    private final UserRepository userRepository;
//
//    private final ObjectMapper objectMapper;
//
//    private final Map<String, ChatRoom> chatRooms = new HashMap<>();
//
//
////    public void handleRequest(ChatMessage chatMessage, WebSocketSession session) {
////        switch (chatMessage.getType()){
////            case MessageType.ENTER:
////                // 사용자가 들어왔을 때
////                // 1. roomId를 통해 방 찾기
////                // 2. 요청한 세션을 방의 set에 추가
////
////                // 방을 생성해야할 때
////                // 1. 방 생성
////                // 2. map에 생성된 방 추가
////                // 3. 요청한 세션을 방의 set에 추가
////
////                break;
////            case MessageType.TALK:
////                // 사용자가 채팅을 보낼 때의 동작을 수행합니다.
////                // 1. roomId로 방 찾기
////                // 2. 해당 room에 메시지를 보낼 권한이 있는지 확인 (사용자가 방의 set에 있는지 확인)
////                // 3. room 안에 있는 모두에게 메시지 전송
////
////                break;
////        }
////    }
//
//
////    public ChatRoomResponse createRoom(ChatRoomRequestDto chatRoomRequestDto) {
////        DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findById(chatRoomRequestDto.getBloodWriteUserId())
////                .orElseThrow(() -> new IllegalArgumentException("값 없음"));// 요청받은 게시물 id로 게시물 엔티티 찾기
////        User sendUser = userRepository.findById(chatRoomRequestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("유저 없음")); // 보낸 사용자
////        BloodChatRoom room = new BloodChatRoom();
////        room.setViewWriteUser(sendUser);
////        room.setCreateWriteUser(designatedBloodWriteUser.getUserNumber());
////        room.setCreatedAt(LocalDateTime.now());
////        BloodChatRoom savedRoom = bloodChatRoomRepository.save(room);
////
////        BloodChatMessage bloodChatMessage = new BloodChatMessage(savedRoom, chatRoomRequestDto.getMessage(), savedRoom.getCreatedAt());
////        bloodChatMessageRepository.save(bloodChatMessage);
////        ChatRoomResponse chatRoomResponse = new ChatRoomResponse(designatedBloodWriteUser.getDesignatedBloodWrite().getTitle(),
////                sendUser.getUserNumber(), designatedBloodWriteUser.getUserNumber().getUserNumber(), chatRoomRequestDto.getMessage(), room.getCreatedAt());
////
////        return chatRoomResponse;
////    }
////
////    public void sendMessage(ChatMessageRequestDto chatMessageRequestDto) {
////        BloodChatRoom bloodChatRoom = bloodChatRoomRepository.findByRoomID(chatMessageRequestDto.getRoomId());
////        User writer = userRepository.findByUserID(chatMessageRequestDto.getWriter());
////        User sender = userRepository.findByUserID(chatMessageRequestDto.getSender());
////        BloodChatMessage bloodChatMessage = BloodChatMessage.builder()
////                .bloodChatRoom(bloodChatRoom)
////                .chatMessage(chatMessageRequestDto.getMessage())
////                .writer(bloodChatRoom.getCreateWriteUser())
////                .sender(bloodChatRoom.getViewWriteUser())
////                .build();
////        bloodChatMessageRepository.save(bloodChatMessage);
////        simpMessageSendingOperations.convertAndSend("/topic/chat/room/" + chatMessageRequestDto.getRoomId(),chatMessageRequestDto);
////    }
//
//    // 채팅방의 목록을 보여줄 때 사용하면 될 것 같습니다.
//    public List<ChatRoom> findAllRoom() {
//        return new ArrayList<>(chatRooms.values());
//    }
//
//    // roomId를 통해서 ChatRoom을 Map에서 찾아서 반환합니다.
//    public ChatRoom findRoomById(String roomId) {
//        return chatRooms.get(roomId);
//    }
//
//    public ChatRoom createRoom(String name) {
//        String randomId = UUID.randomUUID().toString();
//        ChatRoom chatRoom = ChatRoom.builder()
//                .roomId(randomId)
//                .name(name)
//                .build();
//        chatRooms.put(randomId, chatRoom);
//        return chatRoom;
//    }
//
//    public <T> void sendMessage(WebSocketSession session, T message) {
//        try{
//            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//}
