package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.BloodHouseRegister;

@Repository
public interface BloodHouseRegisterRepository extends JpaRepository<BloodHouseRegister,Long> {

}
