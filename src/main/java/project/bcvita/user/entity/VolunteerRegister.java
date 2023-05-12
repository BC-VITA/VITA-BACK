package project.bcvita.user.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerRegister {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    private Volunteer volunteer;

    //봉사 타입 (봉사 종류)
    private String volunteerType;

    //봉사 카테고리
    private String volunteerCategory;

    //내용
    private String content;

    //제목
    private String title;

    //봉사시작기간
    private String volunteerStartDate;

    //봉사마감기간
    private String volunteerEndDate;

    //봉사시작시간
    private String volunteerStartTime;

    //봉사마감시간
    private String volunteerEndTime;

    //필요봉사자수
    private String needVolunteerNumber;

    //봉사지역
    private String volunteerArea;

    //활동구분
    private String activitySection;

    //완료여부
    private boolean isFinish;

    //신고여부
    private boolean isReport;

    //봉사분야
    private String volunteerField;
    
    //모집시작기간
    private String volunteerSeekStartDate;

    //모집마감기간
    private String volunteerSeekEndDate;

    //봉사 주소
    private String volunteerAddress;

    //봉사 대상
    private String volunteerTarget;
}
