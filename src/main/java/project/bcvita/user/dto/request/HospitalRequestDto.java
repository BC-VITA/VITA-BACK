package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HospitalRequestDto {
    private String hospitalId;

    private String hospitalPw;

    private String hospitalName;

    private String hospitalPhoneNumber;

    private String hospitalAddress;
}
