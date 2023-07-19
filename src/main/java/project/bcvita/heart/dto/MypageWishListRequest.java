package project.bcvita.heart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MypageWishListRequest {

    //회원번호
    private Long userId;

    //게시물 번호
    private Long boardId;

    //게시물 타입
    private String boardType;

    //좋아요 여부
    private boolean isWishList;

    //게시물 제목
    private String boardTitle;

    //게시물 내용
    private String boardContent;

    //게시물 작성 날짜
    private String boardDate;

    //필요한 혈액형
    private String needBlood;
    
    //혈액 종류
    private String bloodType;
    
    //장소
    private String place;

}
