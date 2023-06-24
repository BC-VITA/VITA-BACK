package project.bcvita.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VolunteerReservationUserInfoResponse {
    private String userName;
    private String phone;
    private String email;
}
