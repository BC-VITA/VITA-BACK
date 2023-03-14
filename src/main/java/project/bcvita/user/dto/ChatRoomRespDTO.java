package project.bcvita.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import project.bcvita.user.dto.request.ChatMessageRequestDto;
import project.bcvita.user.dto.request.ChatRoomRequestDto;

@Getter
@Setter
public class ChatRoomRespDTO {
    private String roomUUID;
    private Page<ChatMessageRequestDto> messages;

    public ChatRoomRespDTO(String chatRoomUUID, Page<ChatMessageRequestDto> parseChatRoomEntitytoDTO) {
        this.roomUUID = chatRoomUUID;
        this.messages = parseChatRoomEntitytoDTO;
    }
}
