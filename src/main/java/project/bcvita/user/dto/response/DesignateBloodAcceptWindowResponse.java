package project.bcvita.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.bcvita.user.entity.User;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter
public class DesignateBloodAcceptWindowResponse {

    //의료기관명
    private String hospitalName;

    //의료기관 전화번호
    private String hospitalPhoneNumber;

    //의료기관 주소
    private String hospitalAddress;

    //혈액형
    private String patientBlood;

    //rh여부
    private String isRH;

    //혈액종류
    private String bloodType;

    //필요혈액체제
    private String needBloodSystem;

    //환자명
    private String patientName;

    //수혈자 등록번호
    private String bloodPersonNumber;

    //환자 나이
    private String patientAge;

    //보호자명
    //@ManyToOne
    //private User userId;

    private String userName;


    public DesignateBloodAcceptWindowResponse(String hospitalName, String hospitalPhoneNumber, String hospitalAddress, String patientBlood, String isRH, String bloodType, String needBloodSystem, String patientName, String bloodPersonNumber, String userName) {
        this.hospitalName = hospitalName;
        this.hospitalPhoneNumber = hospitalPhoneNumber;
        this.hospitalAddress = hospitalAddress;
        this.patientBlood = patientBlood;
        this.isRH = hospitalAddress;
        this.bloodType = bloodType;
        this.needBloodSystem = needBloodSystem;
        this.patientName = patientName;
        this.bloodPersonNumber = bloodPersonNumber;
        this.userName = userName;
    }


}
