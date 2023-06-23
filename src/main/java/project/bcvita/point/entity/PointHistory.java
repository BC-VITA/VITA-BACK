package project.bcvita.point.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.events.Event;
import project.bcvita.user.entity.ReviewRegister;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.VolunteerRegister;
import project.bcvita.user.entity.VolunteerReservation;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 리뷰 작성 타입에 대한 설명
 *
 * 봉사, 리뷰 달때 포ㄹ인트가 적립됨
 * 그래서 후기 작성할대는 reviewRegisterPoint 사용
 *
 * 봉사는 volunteerReservationPointPoint 사용
 * */
@Entity
@NoArgsConstructor
@Getter
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int point; // 적립된 포인트

    private LocalDateTime createdAt = LocalDateTime.now(); // 포인트 적립 시간

    @ManyToOne(fetch = FetchType.LAZY)
    private ReviewRegister reviewRegister; // 리뷰 작성 타입

    @ManyToOne(fetch = FetchType.LAZY)
    private VolunteerReservation volunteerReservation; //리뷰 작성 타입

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    public void reviewRegisterPoint(User user,int point,ReviewRegister reviewRegister) {
        this.user = user;
        this.point = point;
        this.reviewRegister = reviewRegister;
    }

    public void volunteerReservationPointPoint(User user,int point, VolunteerReservation volunteerReservation) {
        this.point = point;
        this.volunteerReservation = volunteerReservation;

    }
}
