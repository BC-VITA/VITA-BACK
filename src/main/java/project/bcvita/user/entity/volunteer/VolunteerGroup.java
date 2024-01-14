package project.bcvita.user.entity.volunteer;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerGroup {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //1365 자원봉사 포털
    private String volunteerPortal;

    //청소년 활동정보서비스 e청소년
    private String eTeenager;

    //사회복지 자원봉사인증관리
    private String socialWelfareVolunteer;

    //걸스카우트
    private String girlScouts;

    //청소년적십자
    private String teenagerRedCross;

    //법무부 소년보호교육종합관리시스템
    private String ministryJustice;

    //국립공원공단
    private String nationalParkService;

    //문화품앗e 문화체육자원봉사 매칭 시스템
    private String cultureVolunteerService;

    //농총재능나눔
    private String shareFarmTalent;

    //대학적십자사
    private String koreanRedCross;

    //서울교통공사
    private String seoulTransportationCorporation;

    //시간인증 봉사시간
    private int date;
}
