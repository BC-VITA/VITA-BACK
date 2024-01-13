package project.bcvita.user.dto.request.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminVolunteerStatisticsRequest {

    private Long volunteerReservationId;

    private String boardStatus;

}
