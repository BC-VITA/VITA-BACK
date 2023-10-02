package project.bcvita.chat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlarmMessageResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;
    private String senderName;
    private String boardTitle;
    private Long roomId;

    public AlarmMessageResponse(ChatAlarmMessage chatAlarmMessage) {
        this.sendTime = chatAlarmMessage.getSendTime();
        this.roomId = chatAlarmMessage.getChatRoomId();
    }

    public void updateInfo(String boardTitle, String senderName) {
        this.boardTitle = boardTitle;
        this.senderName = senderName;
    }

}
