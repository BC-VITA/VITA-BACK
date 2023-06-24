package project.bcvita.chat.entity;

import ch.qos.logback.core.util.COWArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bcvita.user.entity.DesignatedBloodWriteUser;
import project.bcvita.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User boardWriter; //작성자

    @ManyToOne(fetch = FetchType.LAZY)
    private User boardSeeUser; // 게시글 보고 대화 요청 보낸사람

    @ManyToOne(fetch = FetchType.LAZY)
    private DesignatedBloodWriteUser designatedBloodWriteUser; // 게시글

    @OneToMany(mappedBy = "chatRoom",cascade = CascadeType.ALL)
    private List<ChattingMessage> chattingMessages = new ArrayList<>();

    private Boolean isAgree;

    private LocalDateTime createdAt = LocalDateTime.now();

    public ChatRoom(User boardWriter, User boardSeeUser, DesignatedBloodWriteUser designatedBloodWriteUser) {
        this.boardWriter = boardWriter;
        this.boardSeeUser = boardSeeUser;
        this.designatedBloodWriteUser = designatedBloodWriteUser;
    }

    public void updateIsAgree(Boolean isAgree) {
        this.isAgree = isAgree;
    }
}
