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
public class Hospital {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String hospitalName;

    private String hospitalId;

    private String hospitalPw;

    private String hospitalPhoneNumber;

    private String hospitalAddress;

    private boolean isAdmin;
}
