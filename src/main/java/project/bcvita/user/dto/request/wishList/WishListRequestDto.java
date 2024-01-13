package project.bcvita.user.dto.request.wishList;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishListRequestDto {

    private Long boardId;

    //일반 사용자에 대한 글, 병원에 대한 글인지 프론트가 타입을 보내주기
    private String boardType;

    private boolean isHeart;

    private String userId;
}
