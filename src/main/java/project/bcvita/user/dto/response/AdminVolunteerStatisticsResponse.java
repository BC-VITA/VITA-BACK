package project.bcvita.user.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AdminVolunteerStatisticsResponse {

    //봉사자 수
    private Integer volunteerPersonNumber;

    //월별
    private Date date;

    //봉사 분야별
    private String volunteerField;
}
