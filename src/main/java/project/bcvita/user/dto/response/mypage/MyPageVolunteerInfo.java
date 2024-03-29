package project.bcvita.user.dto.response.mypage;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MyPageVolunteerInfo {

    private int timeTypeVolunteerHistoryNumber;
    private List<MyPageVolunteerReservationResponse> myPageVolunteerReservationList;
}
