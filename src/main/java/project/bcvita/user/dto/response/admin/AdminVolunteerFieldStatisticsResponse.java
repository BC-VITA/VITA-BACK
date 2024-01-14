package project.bcvita.user.dto.response.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminVolunteerFieldStatisticsResponse {
    private int year;
    private int count;
    private String volunteerField;
}
