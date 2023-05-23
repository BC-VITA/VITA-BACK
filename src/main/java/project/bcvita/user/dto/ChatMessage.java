package project.bcvita.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType{
        // 방에 입장하는 요청은 ENTER 요청을 보내고,
        ENTER,
        // 방에 입장 후 채팅을 보내는 요청에는 TALK를 사용합니다.
        TALK
    }

    private MessageType type;
    // room Id 대신 session을 사용해야합니다. (보안때문!)
//    private String roomId;
    // sender 또한 위조할 수 있기 때문에 세션에서 뽑아와야 합니다.
//    private String sender;
    private String message;
}

