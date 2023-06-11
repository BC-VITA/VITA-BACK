package project.bcvita.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageUserInfoResponse {
    private String userId;

    private String userName;

    private String userPhoneNumber;

    private String userEmail;

    private String userBirth;

    private String userBlood;

    private String sex;

    private String isRH;

    private String bloodHistory;

    private Integer userPoint;



}
