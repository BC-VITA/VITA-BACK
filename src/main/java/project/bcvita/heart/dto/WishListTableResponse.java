package project.bcvita.heart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishListTableResponse {

    private Long id;

    private String type;

    private boolean isWishList;

    private Long designatedBoardId;

    private String userId;

    private Long volunteerBoardId;
}
