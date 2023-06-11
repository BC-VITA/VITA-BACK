package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyPageBloodReservationHistoryRequest {
    private Long bloodHouseId;
    private Long bloodReservationId;

    private String centerName;

    private String bloodType;

    private String date;
    
    //분류 -> 헌혈의집 / 헌혈버스
    private String historyType;
}
