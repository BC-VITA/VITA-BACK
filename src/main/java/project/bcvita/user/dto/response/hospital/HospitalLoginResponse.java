package project.bcvita.user.dto.response.hospital;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HospitalLoginResponse {
    private String hospitalId;
    private String hospitalPw;

}
