package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.DesignatedBloodWrite;
import project.bcvita.user.entity.DesignatedBloodWriteUser;
import project.bcvita.user.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface DesignatedBloodWriteUserRepository extends JpaRepository<DesignatedBloodWriteUser, Long> {
    Optional<DesignatedBloodWriteUser> findByDesignatedBloodWriteId(Long id);



//    DesignatedBloodWriteUser findByBloodNumber(Integer BloodNumber);

    Optional<DesignatedBloodWriteUser> findByDesignatedBloodWrite(DesignatedBloodWrite designatedBloodWrite);

    DesignatedBloodWriteUser findById(String designatedUserId);

    List<DesignatedBloodWriteUser> findAllByUserNumber(User user);



}
