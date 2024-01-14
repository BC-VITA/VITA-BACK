package project.bcvita.user.entity.volunteer;

import lombok.*;

import javax.persistence.*;

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

    //봉사 단체 외래키
    @ManyToOne
    private VolunteerGroup volunteerGroup;


}
