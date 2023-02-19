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

    private String hospitalName;  //DesignatedBloodWrite 이런식으로 그 내가 api를 두개로 해서 나눠놓은거야 이게 저 엔티티고 아까 내가 하나 있던건 저기서 user붙인 엔티티 이해했어? 그럼
    //DesignatedBloodWriteUser 에 필요한거 여기다가 다 적고
    //DesignatedBloodWrite 이것도 여기다가 다 적어봐

    private String title; //DesignatedBloodWrite 이런식으로 붙여줘 어떤거 어디 엔티티인

    private String content;

    private String patientBlood;

    private String bloodType;

    private String startDate;

    private Long DesignatedBloodWriteNumber;

    private Integer bloodNumber; //DesignatedBloodWriteUser 이것만 이 엔티티고 나머지는 user안붙인 엔티티얌 오켕

}
