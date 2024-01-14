package project.bcvita.user.repository.bloodHouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.bloodHouse.BloodHouseRegister;
import project.bcvita.user.entity.bloodHouse.BloodHouseReservation;
import project.bcvita.user.entity.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodHouseReservationRepository extends JpaRepository<BloodHouseReservation, Long> {
    Optional<BloodHouseReservation> findByBloodHouseId(Long id);
    Optional<BloodHouseReservation> findByBloodHouse(BloodHouseRegister bloodHouseRegister);

    Optional<BloodHouseReservation> findById(Long id);

    BloodHouseReservation findById(String reservationId);

    List<BloodHouseReservation> findAllByUser(User user);



}
