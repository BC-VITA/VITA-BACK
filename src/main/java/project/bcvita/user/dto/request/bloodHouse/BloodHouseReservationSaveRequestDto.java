package project.bcvita.user.dto.request.bloodHouse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BloodHouseReservationSaveRequestDto {
    //예약 날짜
    private LocalDateTime localDateTime;

    //전혈
    /*private String wholeBlood;
    //혈장
    private String plasma;
    //혈소판
    private String platelet;*/

    private String isBloodType;

    //센터명
    private String bloodHouseName;
    //날짜
    private String date;
    //시간
    private String time;

    public BloodHouseReservationSaveRequestDto(String isBloodType, String bloodHouseName, String date, String time) {
        this.isBloodType = isBloodType;
        this.bloodHouseName = bloodHouseName;
        this.date = date;
        this.time = time;
    }
}
