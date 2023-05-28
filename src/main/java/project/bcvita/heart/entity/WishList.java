package project.bcvita.heart.entity;

import lombok.*;
import project.bcvita.user.entity.DesignatedBloodWriteUser;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.VolunteerRegister;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String boardType;

    private boolean isWishList;

    @OneToOne
    private DesignatedBloodWriteUser designatedBloodWriteUser;

    @OneToOne
    private VolunteerRegister volunteerRegister;


    public void createDesignateBloodHeart(User user, String boardType, boolean isWishList,DesignatedBloodWriteUser designatedBloodWriteUser){
        this.user = user;
        this.boardType = boardType;
        this.isWishList = isWishList;
        this.designatedBloodWriteUser = designatedBloodWriteUser;
    }

    public void createVolunteerHeart(User user, String boardType, boolean isWishList,VolunteerRegister volunteerRegister){
        this.user = user;
        this.boardType = boardType;
        this.isWishList = isWishList;
        this.volunteerRegister = volunteerRegister;
    }
}
