package project.bcvita.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MyPageResponse {
    private MyPageUserInfoResponse myPageUserInfo;

    private List<MyPageDesignatedBloodReviewResponse> myPageDesignatedBloodReviewList;
    private List<MyPageBloodReservationHistoryResponse> myPageBloodReservationHistoryList;
    private  List<MyPageDesignatedBloodBoardResponse> myPageDesignatedBloodBoardList;

}
