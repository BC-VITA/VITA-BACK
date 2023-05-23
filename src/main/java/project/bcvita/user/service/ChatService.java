package project.bcvita.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import project.bcvita.user.dto.ChatMessage;
import project.bcvita.user.dto.ChatRoom;
import project.bcvita.user.dto.request.ChatMessageRequestDto;
import project.bcvita.user.dto.request.ChatRoomRequestDto;
import project.bcvita.user.dto.response.ChatMessageResponse;
import project.bcvita.user.dto.response.ChatRoomResponse;
import project.bcvita.user.entity.*;
import project.bcvita.user.repository.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
   // private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final DesignatedBloodWriteUserRepository designatedBloodWriteUserRepository;
    private final BloodChatRoomRepository bloodChatRoomRepository;

    private final BloodChatMessageRepository bloodChatMessageRepository;

    private final UserRepository userRepository;

    /**
     * 구현해야 할 기능
     * 1. 방 생성
     * 2. 채팅
      */


//    public ChatRoomResponse createRoom(ChatRoomRequestDto chatRoomRequestDto) {
//        DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findById(chatRoomRequestDto.getBloodWriteUserId())
//                .orElseThrow(() -> new IllegalArgumentException("값 없음"));// 요청받은 게시물 id로 게시물 엔티티 찾기
//        User sendUser = userRepository.findById(chatRoomRequestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("유저 없음")); // 보낸 사용자
//        BloodChatRoom room = new BloodChatRoom();
//        room.setViewWriteUser(sendUser);
//        room.setCreateWriteUser(designatedBloodWriteUser.getUserNumber());
//        room.setCreatedAt(LocalDateTime.now());
//        BloodChatRoom savedRoom = bloodChatRoomRepository.save(room);
//
//        BloodChatMessage bloodChatMessage = new BloodChatMessage(savedRoom, chatRoomRequestDto.getMessage(), savedRoom.getCreatedAt());
//        bloodChatMessageRepository.save(bloodChatMessage);
//        ChatRoomResponse chatRoomResponse = new ChatRoomResponse(designatedBloodWriteUser.getDesignatedBloodWrite().getTitle(),
//                sendUser.getUserNumber(), designatedBloodWriteUser.getUserNumber().getUserNumber(), chatRoomRequestDto.getMessage(), room.getCreatedAt());
//
//        return chatRoomResponse;
//    }
//
//    public void sendMessage(ChatMessageRequestDto chatMessageRequestDto) {
//        BloodChatRoom bloodChatRoom = bloodChatRoomRepository.findByRoomID(chatMessageRequestDto.getRoomId());
//        User writer = userRepository.findByUserID(chatMessageRequestDto.getWriter());
//        User sender = userRepository.findByUserID(chatMessageRequestDto.getSender());
//        BloodChatMessage bloodChatMessage = BloodChatMessage.builder()
//                .bloodChatRoom(bloodChatRoom)
//                .chatMessage(chatMessageRequestDto.getMessage())
//                .writer(bloodChatRoom.getCreateWriteUser())
//                .sender(bloodChatRoom.getViewWriteUser())
//                .build();
//        bloodChatMessageRepository.save(bloodChatMessage);
//        simpMessageSendingOperations.convertAndSend("/topic/chat/room/" + chatMessageRequestDto.getRoomId(),chatMessageRequestDto);
//    }
private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
