package project.bcvita.user.entity.designatedBlood;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.bcvita.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DesignatedBloodWishList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private DesignatedBloodWriteUser designatedBloodWriteUser;

}
