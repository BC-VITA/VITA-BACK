package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BloodHouseRegisterRequestDto {

    //날짜
    private String date;

    //시간
    private String time;

    //헌혈종류
    //전혈
    private String wholeBlood;

    //혈장
    private String plasma;


    //혈소판
    private String platelet;

    //센터명
    private String centerName;

}
