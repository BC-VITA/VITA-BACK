package project.bcvita.user.entity;

import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodChatMessage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    private BloodChatRoom bloodChatRoom;
    private String chatMessage;
    private LocalDateTime chatTime;

    public BloodChatMessage(BloodChatRoom bloodChatRoom, String chatMessage, LocalDateTime chatTime) {
        this.bloodChatRoom = bloodChatRoom;
        this.chatMessage = chatMessage;
        this.chatTime = chatTime;
    }


}
