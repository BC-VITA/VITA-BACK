package project.bcvita.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BloodHouseBus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String date;

    private double latitude;

    private double longitude;

    private String busAddress;

    private String place;

    private String busTime;

    private String busPhoneNumber;

    private int busPersonNumber;
}
