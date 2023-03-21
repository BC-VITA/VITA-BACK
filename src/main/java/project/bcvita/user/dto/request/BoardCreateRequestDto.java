package project.bcvita.user.dto.request;

        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import project.bcvita.user.entity.DesignatedBloodWrite;
        import project.bcvita.user.entity.User;

        import java.lang.reflect.Member;

@Getter
@AllArgsConstructor
public class BoardCreateRequestDto {
    private Long userId;
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


//    public BoardCreateRequestDto(String patientIsRH, String requestHospitalAddress) {
//        this.patientIsRH = patientIsRH;
//        this.requestHospitalAddress = requestHospitalAddress;
//    }


}


