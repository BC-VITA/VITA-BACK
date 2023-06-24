package project.bcvita.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatApproveOrCancelRequest {
    private String userId;
    private Long roomId;
    private Boolean isAgree;
}
