package project.bcvita.user.dto.request.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.bcvita.user.entity.User;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class ReviewRegisterRequestDto {

    //작성한 후기 타입
    private String reviewType;

    //작성자 회원번호
    private String userId;

    //사진주소 -> blob 타입이 안먹힘
    private String img;

    //내용
    private String content;

    //제목
    private String title;

    //후기 시간
    private LocalDateTime localDateTime;
}
