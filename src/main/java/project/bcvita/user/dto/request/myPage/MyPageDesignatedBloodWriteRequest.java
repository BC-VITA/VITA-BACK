package project.bcvita.user.dto.request.myPage;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyPageDesignatedBloodWriteRequest {
    private String userName;

    //지정헌혈 횟수 user엔티티 추가
    private Integer designatedBloodNumber;

    private String boardTitle;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime localDateTime;

    private Long userId;
}
