package project.bcvita.user.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewComment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //회원번호 -> 댓글 작성한 회원번호
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //후기번호
    @ManyToOne(fetch = FetchType.LAZY)
    private ReviewRegister reviewRegister;

    //댓글내용
    private String comment;

    //작성 날짜 및 시간
    private LocalDateTime localDateTime = LocalDateTime.now();

    //신고여부
    private boolean isReport;
}