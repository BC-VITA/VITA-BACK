package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginRequestDto {
    private String userId;
    private String userPw;
//    private String type; //일반회원이 로그인했는지, 기업이 했는지, 봉사단체에서 했는지
                        //일반회원 : account
                        // 봉사단체 : volunteer
                        // 기업 : corporation
}
