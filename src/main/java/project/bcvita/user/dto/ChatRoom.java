//package project.bcvita.user.dto;
//
//import lombok.Builder;
//import lombok.Getter;
//import org.springframework.web.socket.WebSocketSession;
//import project.bcvita.user.service.ChatService;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//public class ChatRoom {
//    private String roomId;
//    private String name;
//    private Set<WebSocketSession> sessions = new HashSet<>();
//
//    @Builder
//    public ChatRoom(String roomId, String name) {
//        this.roomId = roomId;
//        this.name = name;
//    }
//
//    // 안쓰일 것 같습니다.
////    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
////        if (chatMessage.getType().equals(MessageType.ENTER)) {
////            sessions.add(session);
////          //  chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
////        }
////        sendMessage(chatMessage, chatService);
////
////    }
//
//    // 방에 있는 모두에게 메시지를 보내는 메소드
//    private <T> void sendMessage(T message, ChatService chatService) {
//        sessions.parallelStream()
//                .forEach(session -> chatService.sendMessage(session, message));
//    }
//}
//
//
