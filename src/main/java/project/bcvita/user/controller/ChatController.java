package project.bcvita.user.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.ChatMessageRequestDto;
import project.bcvita.user.dto.request.ChatRoomRequestDto;
import project.bcvita.user.dto.response.ChatRoomResponse;
import project.bcvita.user.service.ChatService;

import java.util.List;

//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/chat")
//public class ChatController {

    //private final SimpMessageSendingOperations simpMessageSendingOperations;
//    private final ChatService chatService;
//
//    @PostMapping
//    public ChatRoomResponse createRoom(@RequestBody ChatRoomRequestDto chatRoomRequestDto) {
//        return chatService.createRoom(chatRoomRequestDto);
//    }
//
//    @MessageMapping("/enter")
//    public void enter(ChatMessageRequestDto messageRequestDto) {
//        messageRequestDto.setMessage(messageRequestDto.getWriter() + "님이 채팅방에 참여하였습니다.");
//        //simpMessageSendingOperations.convertAndSend("/topic/chat/room/"+messageRequestDto.getRoomId(),messageRequestDto);
//    }
//
//    @MessageMapping(value = "/message")
//    public void message(ChatMessageRequestDto messageRequestDto) {
//        chatService.sendMessage(messageRequestDto);
//    }
//}
