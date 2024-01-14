package project.bcvita.user.repository.bloodHouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.bloodHouse.BloodHouse;
import project.bcvita.user.entity.bloodHouse.BloodHouseRegister;

import java.util.List;

@Repository
public interface BloodHouseRegisterRepository extends JpaRepository<BloodHouseRegister,Long> {
    List<BloodHouseRegister> findAllByBloodHouse(BloodHouse bloodHouse); //리스트로 찾아와짐


}
