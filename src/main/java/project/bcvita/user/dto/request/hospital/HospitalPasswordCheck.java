package project.bcvita.user.dto.request.hospital;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class HospitalPasswordCheck {
    @NotBlank(message = "비밀번호를 필수로 입력하셔야 됩니다.")
    private String password;
    @NotBlank(message = "비밀번호 재확인을 필수로 입력하셔야 됩니다.")
    private String confirmPassword;
}
