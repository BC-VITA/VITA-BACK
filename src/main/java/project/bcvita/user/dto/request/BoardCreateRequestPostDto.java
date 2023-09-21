package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardCreateRequestPostDto {

    private String userName;
    private String patientName;
    private String bloodPersonNumber;
    private int bloodNumber;
    private String patientAge;
    private String hospitalRoomNumber;
    private String hospitalName;
    private String hospitalPhoneNumber;
    private String requestHospitalAddress;
    private String userPhoneNumber;
    private String patientBlood;
    private String patientIsRH;
    private String bloodType;
    private String needBloodSystem;
    private boolean bloodMatchType;
    private boolean isReview;
    private String startDate;
    private String endDate;
    private String title;
    private String content;
    private LocalDateTime localDateTime;
}
