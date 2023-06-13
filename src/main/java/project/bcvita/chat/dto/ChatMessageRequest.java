package project.bcvita.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class  ChatMessageRequest {
    private long roomId;
    private Long boardId; // 게시글 id
    private Long senderId; // 메시지 보내는 유저 기본키
    private Long receiverId; // 메시지 받는 유저 기본키
    private String message;
}
