package project.bcvita.chat.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ChatAlarmMessage {
    LocalDateTime getSendTime();
    Long getChatRoomId();
}
