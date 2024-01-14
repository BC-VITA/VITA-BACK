package project.bcvita.user.dto.response.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserListResponse {
    private Long userNumber;
    private String userID;
    private String userName;
}
