package project.bcvita.user.entity;

import lombok.*;
import project.bcvita.MessageState;

import javax.persistence.*;
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


    @Column(length = 15, columnDefinition = "varchar(15) default 'NOT_READ'")
    @Enumerated(value = EnumType.STRING)
    private MessageState messageState = MessageState.NOT_READ;

    public void updateMessageState(MessageState messageState) {
        this.messageState = messageState;
    }


    public BloodChatMessage(BloodChatRoom bloodChatRoom, String chatMessage, LocalDateTime chatTime) {
        this.bloodChatRoom = bloodChatRoom;
        this.chatMessage = chatMessage;
        this.chatTime = chatTime;
    }
}
