package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.ChatMessage;
import project.bcvita.user.dto.ChatRoom;
import project.bcvita.user.dto.ChatRoomRespDTO;
import project.bcvita.user.dto.request.ChatMessageRequestDto;
import project.bcvita.user.dto.request.ChatRoomRequestDto;
import project.bcvita.user.dto.response.ChatRoomResponse;
import project.bcvita.user.service.ChatService;

import java.awt.print.Pageable;
import java.util.List;

@RequiredArgsConstructor
//@Controller
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private final SimpMessagingTemplate template;

    @PostMapping
    public ChatRoom createRoom(@RequestBody String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }

    @MessageMapping("/test")
    public void test() {
        System.out.println("연결성공");
    }


    @GetMapping("/Room/uuid")
    private ChatRoomRespDTO requestConnect(@RequestParam String req, @RequestParam String resp, Pageable pageable) {
        return chatService.getChatRoomMessage(req,resp,pageable);
    }

}
