package project.bcvita.donate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DonatePointResponse {

    //회원아이디
    private String userId;

    //기부 게시글 번호
    private Long donateId;

    //기부하고 남을 포인트
    //private Integer finalPoint;

    //기부한 날짜
    private LocalDateTime donateDate;

    //기부할 포인트(사용할 포인트)
    private Integer usePoint;

    //기부 게시글 사진 url
    private String img;

}
