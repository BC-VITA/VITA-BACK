package project.bcvita.user.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerReservation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //(봉사활동 신청자)회원번호
    @ManyToOne
    private User user;

    //봉사게시글번호
    @ManyToOne
    private VolunteerRegister volunteerRegister;

    //예약날짜
    private String volunteerDate;

    //개인정보수신동의
    private boolean informationAgree;

    //상태
    private String boardStatus;

    //봉사타입
    private String volunteerType;

}
