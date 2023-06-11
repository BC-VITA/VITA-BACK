package project.bcvita.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Message {
    private Long boardId;
    private String loginId;
    private String content;
    private LocalDateTime sendTime;


}
