package project.bcvita.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ChatMessageInfoResponse {
    private String roomTitle;
    private Long roomId;
    private List<ChatMessageResponse> chatMessageList;

}
