package project.bcvita.user.entity.bloodHouse;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodHouseRegister {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //헌혈의집 엔티티 외래키
    @ManyToOne
    private BloodHouse bloodHouse;

    //날짜
    private String date;

    //전혈
    private String wholeBlood;

    //혈장
    private String plasma;
    
    //혈소판
    private String platelet;

    //시간대
    private String time;

    private LocalDateTime localDateTime;
}
