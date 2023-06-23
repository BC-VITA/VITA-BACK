package project.bcvita.donate.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class DonatePdfResponse {
    private String donateUserName;
    private Integer donatePoint;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate donateDate;
}
