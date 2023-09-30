package project.bcvita.user.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AdminVolunteerStatisticsResponse {
    private int year;
    private int month;
    private long count;
}
