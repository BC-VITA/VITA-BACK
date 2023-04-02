package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import project.bcvita.user.entity.BloodHouse;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BloodHouseReservationRequestDto {

    //예약 날짜
    private LocalDateTime localDateTime;
    
    //전혈
    private String wholeBlood;
    
    //혈장
    private String plasma;
    
    //혈소판
    private String platelet;

    //센터명
    private String bloodHouseName;

    //날짜
    private String date;

    //시간
    private String time;


}
