package project.bcvita.user.dto.response.mypage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MyPageDesignatedBloodBoardResponse {

    private String userName;

    //지정헌혈 횟수 user엔티티 추가
    private Integer designatedBloodNumber;

    private String boardTitle;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;



}
