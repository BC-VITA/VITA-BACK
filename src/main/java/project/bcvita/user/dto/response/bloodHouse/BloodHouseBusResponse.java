package project.bcvita.user.dto.response.bloodHouse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BloodHouseBusResponse {
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
