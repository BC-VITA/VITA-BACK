package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.BloodHouseReservation;
import project.bcvita.user.entity.DesignatedBloodWriteUser;

@Repository
public interface BloodHouseReservationRepository extends JpaRepository<BloodHouseReservation, Long> {
}
