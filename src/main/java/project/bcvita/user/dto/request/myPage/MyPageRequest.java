package project.bcvita.user.dto.request.myPage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageRequest {
    private String userId;

    private String userName;

    private String userPhoneNumber;

    private String userEmail;

    private String userBirth;

    private String userBlood;

    private String sex;

    private String isRH;

    private String bloodHistory;

    private String password;
    private String confirmPassword;

    private String reviewType;


}
