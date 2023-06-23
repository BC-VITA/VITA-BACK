package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.VolunteerRegister;
import project.bcvita.user.entity.VolunteerReservation;

import java.util.List;

@Repository
public interface VolunteerReservationRepository extends JpaRepository<VolunteerReservation, Long> {

    List<VolunteerReservation> findAllByUser(User user);

    List<VolunteerReservation> findAllByVolunteerRegister(VolunteerRegister volunteerRegister);

    List<VolunteerReservation> findAllByUserAndBoardStatus(User user, String status);

}
