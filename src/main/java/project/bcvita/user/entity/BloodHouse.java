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
@AllArgsConstructor
@Builder
public class BloodHouse {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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
}
