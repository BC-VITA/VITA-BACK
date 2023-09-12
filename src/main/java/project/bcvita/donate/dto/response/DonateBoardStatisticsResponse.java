package project.bcvita.donate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DonateBoardStatisticsResponse {

    //게시물 번호
    private Long id;

    //게시물 제목
    private String title;

    //게시물 내용
    private String content;

    //게시물 등록 날짜
    private LocalDateTime localDateTime;

    //게시물별 기부 금액
    private Integer boardTotalPoint;

    private  DonateBoardInterface donateBoardInterface;
}
