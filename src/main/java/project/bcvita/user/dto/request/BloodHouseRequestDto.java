package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BloodHouseRequestDto {
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
