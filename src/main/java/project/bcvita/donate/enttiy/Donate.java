package project.bcvita.donate.enttiy;


import lombok.Getter;
import lombok.Setter;
import project.bcvita.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Donate {
    //영수증 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    //회원번호
    private User user;

    @ManyToOne
    //기부게시글번호
    private DonateBoard donateBoard;

    //익명여부
    private boolean isAnonymous;

    //기부날짜
    private LocalDateTime localDateTime = LocalDateTime.now();

    //사용할 포인트(기부할 포인트)
    private Integer usePoint;

}
