package project.bcvita.user.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRegister {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //작성한 후기 타입
    private String reviewType;

    //작성한 회원번호
    @ManyToOne
    private User user;

    //사진주소 -> blob 타입이 안먹힘
    private String img;

    //제목
    private String title;
    
    //내용
    private String content;

    //신고여부
    private boolean isReport;

    private LocalDateTime localDateTime;
}
