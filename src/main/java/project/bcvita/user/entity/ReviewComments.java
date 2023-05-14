package project.bcvita.user.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewComments {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //회원번호
    @ManyToOne
    private User user;

    //댓글내용
    private String commentsContent;

    //작성날짜및시간
    private LocalDateTime localDateTime;

    //신고여부
    private boolean isReport;

    //후기 게시글 번호
    @ManyToOne
    private ReviewRegister reviewRegister;
}
