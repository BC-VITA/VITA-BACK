package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.BloodHouse;
import project.bcvita.user.entity.BloodHouseRegister;
import project.bcvita.user.entity.DesignatedBloodWrite;
import project.bcvita.user.entity.DesignatedBloodWriteUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodHouseRegisterRepository extends JpaRepository<BloodHouseRegister,Long> {
    List<BloodHouseRegister> findAllByBloodHouse(BloodHouse bloodHouse); //리스트로 찾아와짐


}
