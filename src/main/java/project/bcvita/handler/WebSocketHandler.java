package project.bcvita.handler;

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