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
@Data
public class ReviewComment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //회원번호 -> 댓글 작성한 회원번호
    @ManyToOne
    private User user;

    //후기번호
    @ManyToOne
    private ReviewRegister reviewRegister;

    //댓글내용
    private String comment;

    //작성 날짜 및 시간
    private LocalDateTime localDateTime;

    //신고여부
    private boolean isReport;
}