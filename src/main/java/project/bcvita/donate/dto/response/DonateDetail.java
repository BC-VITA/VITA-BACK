package project.bcvita.donate.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class DonateDetail {
    private Integer total;
    private List<DonatePointResponse> donatePointResponseList;

}
