package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;
import project.bcvita.user.entity.BloodChatRoom;

import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ChatRoomRequestDto {
    private String bloodWriteUserId; // 게시물에 대한 id
    private Member userId; // 메시지를 보내는 사용자 id
    private Member message; // 메시지

    public static ChatRoomRequestDto createR() {
        ChatRoomRequestDto room = new ChatRoomRequestDto();
        room.bloodWriteUserId = UUID.randomUUID().toString();
        return room;
    }

    public BloodChatRoom toEntity() {
        return BloodChatRoom.builder()
                .bloodWriteUserId(this.bloodWriteUserId)
                .userId(this.userId)
                .message(this.message)
                .build();
    }
}
