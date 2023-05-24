package project.bcvita.donate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.bcvita.user.entity.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DonatePointRequest {

    //회원아이디
    private String userId;

    //기부 게시글 번호
    private Long donateId;

    //기부하고 남을 포인트
    private Integer finalPoint;

    //익명 기부 선택
    private boolean isAnonymous;

    //기부 날짜
    private LocalDateTime localDateTime;

    //기부할 포인트(사용할 포인트)
    private Integer usePoint;

     
}
