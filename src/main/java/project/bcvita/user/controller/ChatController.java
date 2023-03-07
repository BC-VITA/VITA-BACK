package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.ChatRoom;
import project.bcvita.user.dto.request.ChatRoomRequestDto;
import project.bcvita.user.dto.response.ChatRoomResponse;
import project.bcvita.user.service.ChatService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestBody String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }

}
