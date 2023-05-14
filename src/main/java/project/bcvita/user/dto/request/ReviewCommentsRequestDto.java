package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ReviewCommentsRequestDto {

    //댓글 작성한 회원 이름
    private String userName;

    //댓글 내용
    private String content;

    //댓글 id
    private Long commentId;

    //댓글 작성한 시간 및 날짜
    private LocalDateTime localDateTime;

    //신고여부
    private boolean isReport;
    
    //후기 게시글 번호
    private String reviewId;
}
