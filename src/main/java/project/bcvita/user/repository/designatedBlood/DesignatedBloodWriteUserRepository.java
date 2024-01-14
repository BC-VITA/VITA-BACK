package project.bcvita.user.repository.designatedBlood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.designatedBlood.DesignatedBloodWrite;
import project.bcvita.user.entity.designatedBlood.DesignatedBloodWriteUser;
import project.bcvita.user.entity.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface DesignatedBloodWriteUserRepository extends JpaRepository<DesignatedBloodWriteUser, Long> {
    Optional<DesignatedBloodWriteUser> findByDesignatedBloodWriteId(Long id);



//    DesignatedBloodWriteUser findByBloodNumber(Integer BloodNumber);

    Optional<DesignatedBloodWriteUser> findByDesignatedBloodWrite(DesignatedBloodWrite designatedBloodWrite);

    //DesignatedBloodWriteUser findById(String designatedUserId);

    List<DesignatedBloodWriteUser> findAllByUserNumber(User user);

    Optional<DesignatedBloodWriteUser> findAllBy();


    Optional<DesignatedBloodWriteUser> findById(Long id);

    List<DesignatedBloodWriteUser> findById(String id);



}
