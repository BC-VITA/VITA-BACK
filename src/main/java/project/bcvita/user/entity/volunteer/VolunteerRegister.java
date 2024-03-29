package project.bcvita.user.entity.volunteer;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    //private String volunteerCategory;

    //내용
    private String content;

    //제목(봉사명)
    private String title;

    //봉사시작기간
    private String volunteerStartDate;

    //봉사마감기간
    private String volunteerEndDate;

    //봉사시작시간
    private String volunteerStartTime;

    //봉사마감시간
    private String volunteerEndTime;

    //모집인원
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

    //봉사 장소
    private String volunteerPlace;

    //위도
    private double latitude;

    //경도
    private double longitude;

    //활동요일
    private String volunteerActivityWeek;

    //자격요건
    private String qualification;

    //봉사자유형
    private String volunteerPersonType;

    //활동빈도
    private String volunteerActivityNumber;

    //요구사항
    private String requirements;

    //담당자명
    private String managerName;

    //담당자이메일
    private String managerEmail;
    
    //담당자전화번호
    private String managerPhoneNumber;

    //모집기관
    private String requireGroup;

    //사이트 url
    private String url;

    private LocalDateTime localDateTime = LocalDateTime.now();


}
