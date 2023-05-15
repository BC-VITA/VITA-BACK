package project.bcvita.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BloodHouseReservationResponse {

    //센터명
    private String centerName;

    //날짜
    private String date;

    //시간
    private String time;

    //전혈
    private String wholeBlood;

    //혈장
    private String plasma;

    //혈소판
    private String platelet;

    //private String isBloodType;

    private LocalDateTime localDateTime;

}
