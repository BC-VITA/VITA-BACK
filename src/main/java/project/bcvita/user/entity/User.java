package project.bcvita.user.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userNumber;

    private String userID;

    private String userPW;

    private String userName;

    private String userPhoneNumber;

    private String userEmail;

    private String userBirth;

    private String userBlood;

    private String sex;

    private String isRH;

    private String bloodHistory;

    private Integer userPoint;

    private String confirmPassword;

    //사용자 관리자 권한 여부
    private boolean isAdmin;

    


    public User(String userID, String userPW, String userName, String userBirth, String userPhoneNumber,
                String userBlood, String sex, String isRH, String bloodHistory, Integer userPoint, String userEmail
                ,String confirmPassword) {
        this.userID = userID;
        this.userPW = userPW;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userBirth = userBirth;
        this.userPhoneNumber = userPhoneNumber;
        this.userBlood = userBlood;
        this.sex = sex;
        this.isRH = isRH;
        this.bloodHistory = bloodHistory;
        this.userPoint = userPoint;
        this.confirmPassword = confirmPassword;
    }
}
