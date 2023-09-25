package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminVolunteerStatisticsRequest {

    private Long volunteerReservationId;

    private String boardStatus;

}
