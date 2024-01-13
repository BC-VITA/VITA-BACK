package project.bcvita.user.dto.request.hospital;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HospitalLoginRequestDto {
    //병원 로그인
    private String hospitalId;
    private String hospitalPw;
}
