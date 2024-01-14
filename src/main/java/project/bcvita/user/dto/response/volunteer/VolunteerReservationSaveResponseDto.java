package project.bcvita.user.dto.response.volunteer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class VolunteerReservationSaveResponseDto {
    private String status;
    private String volunteerDate;

    private String startTime;

    private String endTime;

    private String address;
    private String volunteerPlace;
    private String volunteerType;
    private String name;
    private String phone;

    private Long boardId;
}

