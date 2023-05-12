package project.bcvita.user.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Volunteer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //봉사 기업,단체 이름
    private String volunteerGroupName;

    //봉사 아이디
    private String volunteerId;

    //봉사 비밀번호
    private String volunteerPw;

    //봉사 전화번호
    private String volunteerPhoneNumber;

    //봉사 종류 ??
    private String volunteerKind;

    //봉사 분야(카테고리) ??
    private String volunteerField;

}
