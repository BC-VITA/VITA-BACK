package project.bcvita.chat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import project.bcvita.chat.dto.*;
import project.bcvita.user.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserService userService;
    private final ChatService chatService;
    @MessageMapping("/send")
    public String sendMessage(@Payload ChatMessageRequest message) {
        if(message.getRoomId() != 0L){
            simpMessagingTemplate.convertAndSend("/sub/chat/"+message.getRoomId(), chatService.chatSend(message));
        }else {
            simpMessagingTemplate.convertAndSend("/sub/chat", chatService.chatSend(message));
        }
        return "message";
    }
    @GetMapping("list")
    public List<ChatListResponse> chatList(HttpSession session) {
        return chatService.chatList(session);
    }

    @GetMapping("{roomId}")
    public ChatMessageInfoResponse detailRoom(@PathVariable Long roomId) {
        return chatService.detailRoom(roomId);
    }

    @DeleteMapping("{roomId}")
    public String deleteRoom(@PathVariable Long roomId) {
        return chatService.deleteRoom(roomId);
    }



}
