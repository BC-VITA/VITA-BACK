package project.bcvita.user.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DesignatedBloodWriteUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    private User userNumber;

    @OneToOne
    private DesignatedBloodWrite designatedBloodWrite;

    private String bloodPersonNumber;

    private Integer bloodNumber;

    private String patientName;

    private String patientAge;

    private String hospitalRoomNumber;

    private boolean bloodMatchType;
    private boolean isReview;

    private boolean isReport;

    private Integer wishListCount;


    public DesignatedBloodWriteUser(Long id, User userNumber, DesignatedBloodWrite designatedBloodWrite, String bloodPersonNumber, int bloodNumber, String patientName, String patientAge, String hospitalRoomNumber, boolean bloodMatchType, boolean isReview) {
        this.id = id;
        this.userNumber = userNumber;
        this.designatedBloodWrite = designatedBloodWrite;
        this.bloodPersonNumber = bloodPersonNumber;
        this.bloodNumber = bloodNumber;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.hospitalRoomNumber = hospitalRoomNumber;
        this.bloodMatchType = bloodMatchType;
        this.isReview = isReview;
    }
}
