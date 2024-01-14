package project.bcvita.user.dto.response.mypage;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MyPageDesignatedBloodReviewResponse {

    private String userName;

    private Integer designatedBloodNumber;

    private String reviewTitle;

    private LocalDateTime localDateTime;
}
