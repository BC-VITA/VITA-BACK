package project.bcvita.heart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MypageVolunteerResponse {

    private Long volunteerId;

    private String userId;

    //봉사 타입 (봉사 종류)
    private String volunteerType;

    //내용
    private String content;

    //제목(봉사명)
    private String title;

    //봉사시작기간
    private String volunteerStartDate;

    //봉사마감기간
    private String volunteerEndDate;

    //모집인원
    private String needVolunteerNumber;

    //활동구분
    private String activitySection;

    //봉사 주소
    private String volunteerAddress;

    //봉사 장소
    private String volunteerPlace;
}
