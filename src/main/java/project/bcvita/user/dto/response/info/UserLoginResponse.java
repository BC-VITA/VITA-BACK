package project.bcvita.user.dto.response.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginResponse {
    private String userId;
    private String userPw;
}
