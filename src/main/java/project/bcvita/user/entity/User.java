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

    //@Column(nullable = false, length = 30, unique = true)
    private String userID;

    //@Column(length = 100)
    private String userPW;

   // @Column(nullable = false, length = 50, unique = true)
    private String userName;

   // @Column(nullable = false, length = 20, unique = true)
    private String userPhoneNumber;

   // @Column(nullable = false, length = 50, unique = true)
    private String userEmail;

    //@Column(nullable = false, length = 8, unique = true)
    private String userBirth;

   // @Column(nullable = false, length = 50, unique = true)
    private String userBlood;

    private String sex;

   // @Column(nullable = false, length = 50, unique = true)
    private boolean isRH;

   // @Column(nullable = false, length = 50, unique = true)
    private Integer bloodHistory;

    private Integer userPoint;

    private String confirmPassword;


    public User(String userID, String userPW, String userName, String userBirth, String userPhoneNumber,
                String userBlood, String sex, Boolean isRH, Integer bloodHistory, Integer userPoint, String userEmail
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
