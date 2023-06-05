package project.bcvita.user.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class DesignatedBloodWrite {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

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

    private LocalDateTime localDateTime;

    public DesignatedBloodWrite(Long id, String hospitalName, String requestHospitalAddress, String hospitalPhoneNumber, String patientBlood, String patientIsRH, String bloodType, String needBloodSystem, String startDate, String endDate, String title, String content, LocalDateTime localDateTime) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.requestHospitalAddress = requestHospitalAddress;
        this.hospitalPhoneNumber = hospitalPhoneNumber;
        this.patientBlood = patientBlood;
        this.patientIsRH = patientIsRH;
        this.bloodType = bloodType;
        this.needBloodSystem = needBloodSystem;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content;
        this.localDateTime = localDateTime;
    }
}
