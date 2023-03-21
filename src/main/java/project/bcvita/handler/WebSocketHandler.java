package project.bcvita.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import project.bcvita.user.dto.ChatMessage;
import project.bcvita.user.entity.BloodChatMessage;
import project.bcvita.user.entity.BloodChatRoom;
import project.bcvita.user.repository.BloodChatMessageRepository;
import project.bcvita.user.service.ChatService;

//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class WebSocketHandler extends TextWebSocketHandler {
//    private final ObjectMapper objectMapper;
//    private final ChatService chatService;
//
//    private final BloodChatMessageRepository bloodChatMessageRepository;
//
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        log.info("{}", payload);
//        BloodChatMessage bloodChatMessage = objectMapper.readValue(payload, BloodChatMessage.class);
//
////        BloodChatRoom bloodChatRoom = chatService.findByRoomID(bloodChatMessage.getBloodChatRoom());
//    }
//}