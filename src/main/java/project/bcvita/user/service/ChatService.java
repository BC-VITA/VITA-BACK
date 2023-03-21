package project.bcvita.user.service;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import project.bcvita.user.dto.ChatMessage;
import project.bcvita.user.dto.request.ChatMessageRequestDto;
import project.bcvita.user.dto.request.ChatRoomRequestDto;
import project.bcvita.user.dto.response.ChatMessageResponse;
import project.bcvita.user.dto.response.ChatRoomResponse;
import project.bcvita.user.entity.*;
import project.bcvita.user.repository.*;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

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
//
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

//    public void sendMessage(ChatMessageRequestDto chatMessageRequestDto) {
//        BloodChatRoom bloodChatRoom = bloodChatRoomRepository.findByRoomID(chatMessageRequestDto.getRoomId());
//        User writer = userRepository.findByUserID(chatMessageRequestDto.getWriter());
//        User sender = userRepository.findByUserID(chatMessageRequestDto.getSender());
//        BloodChatMessage bloodChatMessage = BloodChatMessage.builder()
//                .bloodChatRoom(bloodChatRoom)
//                .chatMessage(chatMessageRequestDto.getMessage())
                //.writer(bloodChatRoom.getCreateWriteUser())
                //.sender(bloodChatRoom.getViewWriteUser())
//                .build();
//        bloodChatMessageRepository.save(bloodChatMessage);
//        simpMessageSendingOperations.convertAndSend("/topic/chat/room/" + chatMessageRequestDto.getRoomId(),chatMessageRequestDto);
//    }
//
//
//}
