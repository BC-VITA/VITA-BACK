package project.bcvita.user.dto.request.bloodHouse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BloodHouseReservationRequestDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    //예약 날짜
    private LocalDateTime localDateTime;

    //전혈
    private String wholeBlood;
    //혈장
    private String plasma;
    //혈소판
    private String platelet;

    //private String isBloodType;

    //센터명
    private String bloodHouseName;
    //날짜
    private String date;
    //시간
    private String time;

    //private String isBloodType;


    public BloodHouseReservationRequestDto(String wholeBlood, String plasma, String platelet, String bloodHouseName, String date, String time) {
        this.wholeBlood = wholeBlood;
        this.plasma = plasma;
        this.platelet = platelet;
        this.bloodHouseName = bloodHouseName;
        this.date = date;
        this.time = time;
    }
}
