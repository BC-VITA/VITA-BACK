package project.bcvita.point.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PointHistoryResponse {
    private String saveContent; //적립 내용
    private int savePoint; // 적립된 포인트
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saveDateTime; // 적립 시간
}
