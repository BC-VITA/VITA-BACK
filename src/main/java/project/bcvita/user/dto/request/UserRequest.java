package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@AllArgsConstructor
@Getter
public class UserRequest {


    @NotBlank(message = "아이디는 필수 입력값입니다.")
    //@Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
    private String userID;

    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
 //   private String userPW;
    @NotBlank(message = "비밀번호를 필수로 입력하셔야 됩니다.")
    private String password;
    @NotBlank(message = "비밀번호 재확인을 필수로 입력하셔야 됩니다.")
    private String confirmPassword;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String userName;

    @NotBlank(message = "전화번호는 필수 입력값입니다.")
    //@Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
    private String userPhoneNumber;
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String userEmail;

    //@NotBlank(message = "생년월일은 필수 입력값입니다.")
    private String userBirth;

    //@NotBlank(message = "혈액형은 필수 선택입니다.")
    private String userBlood;

    private String sex;

    private Boolean isRH;

    private Integer bloodHistory;

    //private Integer userPoint;

    private String confirmPW;


}
