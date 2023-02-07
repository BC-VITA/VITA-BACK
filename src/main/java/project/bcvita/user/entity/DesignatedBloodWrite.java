package project.bcvita.user.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class DesignatedBloodWrite {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long DesignatedBloodWriteNumber;

    private String hospitalName;

    private String requestHospitalAddress;

    private String hospitalPhoneNumber;

    private String patientBlood;

    private String patientIsRH;
    private String bloodType;

    private String needBloodSystem;

    private String startDate;

    private String endDate;

    private String title;

    private String content;


    public DesignatedBloodWrite(Long DesignatedBloodWriteNumber, String hospitalName, String requestHospitalAddress, String hospitalPhoneNumber, String patientBlood, String bloodType, String needBloodSystem, String startDate, String endDate, String title, String content, String patientIsRH) {
        this.DesignatedBloodWriteNumber = DesignatedBloodWriteNumber;
        this.hospitalName = hospitalName;
        this.requestHospitalAddress = requestHospitalAddress;
        this.hospitalPhoneNumber = hospitalPhoneNumber;
        this.patientBlood = patientBlood;
        this.bloodType = bloodType;
        this.needBloodSystem = needBloodSystem;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content;
        this.patientIsRH = patientIsRH;
    }

}
