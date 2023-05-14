package project.bcvita.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.bcvita.user.entity.User;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter
public class ReviewRegisterResponse {

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
}
