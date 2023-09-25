package project.bcvita.chat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ChatListResponse {
    private Long roomId;
    private String loginUserId;
    private String otherUserId;

    private String title;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime boardCreatedAt;

    private Long boardId;

    private Boolean isAgree;
}
