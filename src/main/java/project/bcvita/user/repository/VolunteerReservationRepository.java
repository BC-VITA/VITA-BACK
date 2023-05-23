package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.VolunteerReservation;

@Repository
public interface VolunteerReservationRepository extends JpaRepository<VolunteerReservation, Long> {
}
