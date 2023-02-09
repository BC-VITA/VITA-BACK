package project.bcvita.user.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DesignatedBloodWriteUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long DesignatedBloodWriteUserNumber;

    @ManyToOne
    private User userNumber;

    @OneToOne
    private DesignatedBloodWrite DesignatedBloodWriteNumber;

    private String bloodPersonNumber;

    private Integer bloodNumber;

    private String patientName;

    private String patientAge;

    private String hospitalRoomNumber;

    private boolean bloodMatchType;
    private boolean isReview;

    private boolean isReport;


    public DesignatedBloodWriteUser(Long designatedBloodWriteUserNumber, User userNumber, DesignatedBloodWrite designatedBloodWriteNumber, String bloodPersonNumber, int bloodNumber, String patientName, String patientAge, String hospitalRoomNumber, boolean bloodMatchType, boolean isReview) {
        DesignatedBloodWriteUserNumber = designatedBloodWriteUserNumber;
        this.userNumber = userNumber;
        DesignatedBloodWriteNumber = designatedBloodWriteNumber;
        this.bloodPersonNumber = bloodPersonNumber;
        this.bloodNumber = bloodNumber;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.hospitalRoomNumber = hospitalRoomNumber;
        this.bloodMatchType = bloodMatchType;
        this.isReview = isReview;
    }
}
