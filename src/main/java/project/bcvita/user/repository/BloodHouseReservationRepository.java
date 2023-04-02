package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.BloodHouseReservation;
import project.bcvita.user.entity.DesignatedBloodWrite;
import project.bcvita.user.entity.DesignatedBloodWriteUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodHouseReservationRepository extends JpaRepository<BloodHouseReservation, Long> {
//    Optional<BloodHouseReservation> findByWholeBlood(String wholeBlood);
//
//    Optional<BloodHouseReservation> findByPlasma(String plasma);
//
//    Optional<BloodHouseReservation> findByPlatelet(String platelet);
}
