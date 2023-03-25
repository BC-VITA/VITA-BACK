package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class BloodHouseReservationRequestDto {
    //센터명
    private String bloodHouseName;

    //날짜
    private String date;

    //시간
    private String time;

    //헌혈 종류
//    private String bloodType;

    //전혈
    private String wholeBlood;

    //혈장
    private String plasma;

    //혈소판
    private String platelet;


}
