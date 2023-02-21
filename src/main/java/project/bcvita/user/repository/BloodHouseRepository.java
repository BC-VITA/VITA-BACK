package project.bcvita.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.bcvita.user.entity.BloodHouse;
import project.bcvita.user.entity.DesignatedBloodWriteUser;

import java.util.Optional;

public interface BloodHouseRepository extends JpaRepository<BloodHouse, Long> {
    Optional<BloodHouse> findBloodHouseById(Long id);
}
