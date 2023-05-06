package project.bcvita.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.bcvita.user.entity.DesignatedBloodWrite;
import project.bcvita.user.entity.DesignatedBloodWriteUser;
import project.bcvita.user.entity.User;

import javax.persistence.ManyToOne;

@Getter
@AllArgsConstructor
public class WishListResponse {
    
    //지정 헌혈 게시글 가져오기
    @ManyToOne
    private DesignatedBloodWrite designatedBloodWrite;

    //지정 헌혈 게시글 user 가져오기 
    @ManyToOne
    private DesignatedBloodWriteUser designatedBloodWriteUser;

    //회원 외래키
    @ManyToOne
    private User user;

    private String hospitalName;

    private String title;

    private String content;

    private String patientBlood;

    private String bloodType;

    private String startDate;

    private Long DesignatedBloodWriteNumber;

    private Integer bloodNumber;




}
