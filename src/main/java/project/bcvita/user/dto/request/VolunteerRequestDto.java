package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VolunteerRequestDto {

    //봉사시작기간
    private String volunteerStartDate;

    //봉사마감기간
    private String volunteerEndDate;

    //모집시작기간
    private String volunteerSeekStartDate;

    //모집마감기간
    private String volunteerSeekEndDate;

    //봉사지역
    private String volunteerArea;

    //봉사 주소
    private String volunteerAddress;

    //봉사시작시간
    private String volunteerStartTime;

    //봉사마감시간
    private String volunteerEndTime;

    //필요봉사자수
    private String needVolunteerNumber;

    //봉사분야
    private String volunteerField;

    //활동구분
    private String activitySection;

    //내용
    private String content;

    //제목
    private String title;

    //봉사 타입 (봉사 종류)
    private String volunteerType;

    //봉사 대상
    private String volunteerTarget;

}
