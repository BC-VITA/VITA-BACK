package project.bcvita.chat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ChatMessageResponse {
    private Long senderId;// 보낸 유저의 기본키
    private String senderName;
    private Long receiverId; // 받는 유저의 기본키
    private String receiverName;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

}
