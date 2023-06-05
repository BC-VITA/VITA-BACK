package project.bcvita.user.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyPageDesignatedBloodWriteRequest {
    private String userName;

    //지정헌혈 횟수 user엔티티 추가
    private Integer designatedBloodNumber;

    private String boardTitle;

    private LocalDateTime localDateTime;
}
