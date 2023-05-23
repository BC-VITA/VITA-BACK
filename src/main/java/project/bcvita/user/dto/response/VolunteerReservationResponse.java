package project.bcvita.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VolunteerReservationResponse {
    //봉사제목
    private String volunteerTitle;

    //선택한 예약 날짜
    private String volunteerDate;

    //봉사시작시간
    private String volunteerStartTime;

    //봉사마감시간
    private String volunteerEndTime;

    //봉사주소
    private String volunteerAddress;

    //봉사유형
    private String volunteerKind;

    //담당자명
    private String volunteerManagerName;

    //전화번호
    private String volunteerManagerPhoneNumber;

    //봉사 장소
    private String volunteerPlace;
}
