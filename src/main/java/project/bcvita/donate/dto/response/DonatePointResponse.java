package project.bcvita.donate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DonatePointResponse {
    
    //회원 포인트
    private Integer userPoint;

    //회원아이디
    private String userId;

    //기부 게시글 번호
    private Long volunteerBoardId;

    //기부하고 남을 포인트
    private Integer finalPoint;

    //익명 기부 선택
    private boolean isAnonymous;
}
