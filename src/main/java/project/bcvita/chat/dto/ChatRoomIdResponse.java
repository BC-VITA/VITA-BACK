package project.bcvita.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChatRoomIdResponse {

    private Long roomId;

    private String userId;
}
