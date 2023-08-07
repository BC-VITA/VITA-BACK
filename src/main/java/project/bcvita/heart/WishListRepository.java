package project.bcvita.heart;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bcvita.heart.entity.WishList;
import project.bcvita.user.entity.DesignatedBloodWriteUser;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.Volunteer;
import project.bcvita.user.entity.VolunteerRegister;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    Optional<WishList> findByUserAndDesignatedBloodWriteUser(User user, DesignatedBloodWriteUser designatedBloodWriteUser);
    Optional<WishList> findByUserAndVolunteerRegister(User user, VolunteerRegister volunteerRegister);

    int countByVolunteerRegister(VolunteerRegister volunteerRegister);

    int countByDesignatedBloodWriteUser(DesignatedBloodWriteUser designatedBloodWriteUser);

    List<WishList> findByUser(User user);
}
