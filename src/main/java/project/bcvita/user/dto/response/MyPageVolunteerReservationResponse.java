package project.bcvita.user.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MyPageVolunteerReservationResponse {

    private String userName;

    //봉사단체 리스트
   private String volunteerName;

    //시간인증 봉사시간
    private int volunteerTime;

    //봉사 게시글 제목
    private String volunteerTitle;

    //봉사 종류
    private String volunteerType;

    //봉사 게시글 일시
    private LocalDateTime localDateTime;
}
