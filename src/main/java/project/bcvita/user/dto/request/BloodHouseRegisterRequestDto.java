package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BloodHouseRegisterRequestDto {

    //지역
    private String area;

    //센터명
    private String centerName;

    //주소
    private String bloodHouseAddress;

    //전화번호
    private String bloodHousePhoneNumber;

    //위도
    private double latitude;

    //경도
    private double longitude;

    //평일
    private String weekdayTime;

    //토요일
    private String saturdayTime;

    //일요일
    private String sundayRestTime;

    //공휴일
    private String restTime;

    //날짜
    private String date;

    //전혈
    private String wholeBlood;

    //혈장
    private String plasma;

    //혈소판
    private String platelet;
}
