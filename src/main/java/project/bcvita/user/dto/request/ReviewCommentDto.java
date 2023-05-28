package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.bcvita.user.entity.ReviewRegister;
import project.bcvita.user.entity.User;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewCommentDto {

    private String userId;

    //댓글내용
    private String comment;

    //작성 날짜 및 시간
    private LocalDateTime localDateTime;

    //신고여부
    private boolean isReport;
}