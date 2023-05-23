package project.bcvita.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ReviewCommentResponse {

    private String userId;

    private Long reviewRegisterId;

    //댓글내용
    private String comment;

    //작성 날짜 및 시간
    private LocalDateTime localDateTime;

    //신고여부
    private boolean isReport;


}
