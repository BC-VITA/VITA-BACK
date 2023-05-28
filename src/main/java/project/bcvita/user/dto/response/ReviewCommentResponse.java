package project.bcvita.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ReviewCommentResponse {

    private String userId;
    private String userName;
    private Long reviewRegisterId;

    //댓글내용
    private String comment;

    //작성 날짜 및 시간
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;

    //신고여부
    private boolean isReport;


}
