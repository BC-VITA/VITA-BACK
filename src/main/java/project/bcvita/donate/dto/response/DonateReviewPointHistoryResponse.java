package project.bcvita.donate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DonateReviewPointHistoryResponse {

    String userId;
    Long reviewId;
    LocalDateTime localDateTime;
}
