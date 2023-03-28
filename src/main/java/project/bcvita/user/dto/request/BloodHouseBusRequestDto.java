package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BloodHouseBusRequestDto {
    private Long id;

    private String date;

    private double latitude;

    private double longitude;

    private String busAddress;

    private String locationAddress;

    private String busTime;

    private String busPhoneNumber;

    private int busPersonNumber;
}
