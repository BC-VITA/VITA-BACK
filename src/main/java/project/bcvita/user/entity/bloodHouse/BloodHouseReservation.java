package project.bcvita.user.entity.bloodHouse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.bcvita.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BloodHouseReservation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //헌혈의집 번호
    @ManyToOne
    private BloodHouse bloodHouse;

    //회원번호
    @ManyToOne
    private User user;


    //전혈
    private String wholeBlood;

    //혈장
    private String plasma;

    //혈소판
    private String platelet;

    //헌혈종류 하나로 합치기
    private String isBloodType;

    //시간대
    private String time;

    //예약날짜->현재 실시간 날짜
    private LocalDateTime localDateTime;

    //날짜
    private String date;
}
