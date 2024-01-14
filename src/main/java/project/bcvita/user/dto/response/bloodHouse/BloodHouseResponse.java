package project.bcvita.user.dto.response.bloodHouse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BloodHouseResponse {
    private Long id;

    private String area;

    private String centerName;

    private String bloodHouseAddress;

    private String bloodHousePhoneNumber;

    private double latitude;

    private double longitude;

    private String weekdayTime;

    private String saturdayTime;

    private String sundayRestTime;

    private String restTime;


//    //전혈
//    private String wholeBlood;
//
//    //혈장
//    private String plasma;
//
//    //혈소판
//    private String platelet;

}
