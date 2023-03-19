package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatMessageRequestDto {
    private String roomId;
    private String writer; //채팅방 만든 사람
    private String sender; //채팅방에 들어온 사람
    private String message;

}
