package project.bcvita.chat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bcvita.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class ChattingMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender; //메시지 보낸사람

    @ManyToOne(fetch = FetchType.LAZY)
    private User receiver; // 받은 사람

    private String message;

    private LocalDateTime sendTime = LocalDateTime.now();
    private boolean isRead = false;

    public void updateIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public ChattingMessage(ChatRoom chatRoom, User sender, User receiver, String message) {
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }
}
