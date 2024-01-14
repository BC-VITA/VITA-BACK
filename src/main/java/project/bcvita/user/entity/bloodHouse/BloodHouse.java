package project.bcvita.user.entity.bloodHouse;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodHouse {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String area;

    private String centerName;

    private String bloodHouseAddress;

    private String bloodHousePhoneNumber;

    private double latitude;

    private double longitude;

    private String weekdayTime;

    private String saturdayTime;

    private String sundayRestTime;

    private String restTime;

    private String date;

//
//    //회의 때문에 임시로 만듦 -> 하고나서 외래키로 수정
//    private String wholeBlood;
//    private String plasma;
//    private String platelet;
}
