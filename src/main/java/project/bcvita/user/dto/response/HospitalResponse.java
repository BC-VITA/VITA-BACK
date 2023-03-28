package project.bcvita.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HospitalResponse {
    private String hospitalId;

    private String hospitalPw;

    private String hospitalName;

    private String hospitalPhoneNumber;

    private String hospitalAddress;
}
