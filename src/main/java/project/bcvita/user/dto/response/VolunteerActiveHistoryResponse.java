package project.bcvita.user.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VolunteerActiveHistoryResponse {

    private Long reservationId;
    private String title;
    private LocalDateTime boardCreateTime;

}
