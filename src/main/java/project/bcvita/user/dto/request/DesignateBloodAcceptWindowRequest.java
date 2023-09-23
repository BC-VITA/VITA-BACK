package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DesignateBloodAcceptWindowRequest {


    private Long designateBloodWriteUserId;

    private String userId;
}
