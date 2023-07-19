package project.bcvita.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import project.bcvita.chat.dto.*;
import project.bcvita.user.service.UserService;

import java.util.List;
import java.util.Map;

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
    @GetMapping("list") //mypage chat list
    public List<ChatListResponse> chatList(String userId) {
        return chatService.chatList(userId);
    }

    @GetMapping("{roomId}") // chattingRoom into message detail
    public ChatMessageInfoResponse detailRoom(@PathVariable Long roomId) {
        return chatService.detailRoom(roomId);
    }

    @DeleteMapping("{roomId}") // chattingRoom delete -> message delete
    public String deleteRoom(@PathVariable Long roomId) {
        return chatService.deleteRoom(roomId);
    }

    //수락 또는 취소 하는 api
    @PostMapping("/agree")
    public String approveOrCancel(@RequestBody ChatApproveOrCancelRequest chatApproveOrCancelRequest) {
        return chatService.agreeOrCancel(chatApproveOrCancelRequest);

    }

    @GetMapping("/roomId")
    public List<ChatResponse> getRoomId(@RequestBody ChatRoomIdRequest chatRoomIdRequest){
        return chatService.getChatRoomId(chatRoomIdRequest);
    }



}
