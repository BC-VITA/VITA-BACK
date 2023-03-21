package project.bcvita.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ChatMessageResponse {
    private Long createMessageUserId;
    private Long responseMessageUserId;
    private String message;
    @JsonFormat(pattern = "mm:ss")
    private LocalDateTime nowTime;
}
