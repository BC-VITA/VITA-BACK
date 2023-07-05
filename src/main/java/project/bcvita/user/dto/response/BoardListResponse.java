package project.bcvita.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.bcvita.user.entity.DesignatedBloodWrite;
import project.bcvita.user.entity.User;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@AllArgsConstructor
@Getter
public class BoardListResponse {

    private String hospitalName;

    private String title;

    private String content;

    private String patientBlood;

    private String bloodType;

    private String startDate;

    private Long DesignatedBloodWriteNumber;

    private Integer bloodNumber;

    private int wishListCount;

    private boolean isWishList;

    private String registerName;

    private Long registerId;

}
