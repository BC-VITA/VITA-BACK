package project.bcvita.user.dto.response.volunteer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VolunteerRequestUserResponse {
    private Long reservationId;
    private String userName;

}
