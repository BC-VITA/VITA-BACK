package project.bcvita.user.dto.request.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.bcvita.user.entity.BloodHouse;

@Getter
@AllArgsConstructor
public class AdminJoinAccpetRequest {

    private Long hospitalId;

    private Boolean isAdminJoinAccept;
}
