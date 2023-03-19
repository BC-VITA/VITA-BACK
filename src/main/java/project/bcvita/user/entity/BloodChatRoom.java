package project.bcvita.user.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodChatRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; //RoomID

    @ManyToOne
    private DesignatedBloodWriteUser designatedBloodWriteUser; //게시물 제목

    @ManyToOne
    private User createWriteUser; //지정헌혈 게시물 작성자

    @ManyToOne
    private User viewWriteUser; //채팅 들어온 사람


    private LocalDateTime createdAt; //시간

    private String chatIsFinish; //완료여부
}
