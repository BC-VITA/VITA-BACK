package project.bcvita.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MyPageBloodReservationHistoryResponse {

    private String userName;
    private String bloodHistory;

    //분류 -> 헌혈의집 / 버스
    //private String historyType;

    private String bloodType;

    private String centerName;

    private String date;

    private String time;
}
