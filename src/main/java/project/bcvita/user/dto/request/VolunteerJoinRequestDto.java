package project.bcvita.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VolunteerJoinRequestDto {
    //봉사 아이디
    private String volunteerId;

    //봉사 비밀번호
    private String volunteerPw;

    //기업-단체 이름
    private String volunteerGroupName;

    //봉사 전화번호
    private String volunteerPhoneNumber;

    //봉사 분야(카테고리) ??
    private String volunteerField;
    
}
