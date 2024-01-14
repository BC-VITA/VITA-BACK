package project.bcvita.user.dto.response.review;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReviewDetail {
    private String title;
    private String content;

    private List<ReviewCommentResponse> reviewComments;


}
