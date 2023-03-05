package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatRoomRequestDto {
    private Long bloodWriteUserId; // 게시물에 대한 id
    private Long userId; // 메시지를 보내는 사용자 id
    private String message; // 메시지
}
