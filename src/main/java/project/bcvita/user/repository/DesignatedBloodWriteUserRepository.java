package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bcvita.user.entity.DesignatedBloodWrite;
import project.bcvita.user.entity.DesignatedBloodWriteUser;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface DesignatedBloodWriteUserRepository extends JpaRepository<DesignatedBloodWriteUser, Long> {
    Optional<DesignatedBloodWriteUser> findByDesignatedBloodWriteId(Long id);

//    DesignatedBloodWriteUser findByBloodNumber(Integer BloodNumber);

    Optional<DesignatedBloodWriteUser> findByDesignatedBloodWrite(DesignatedBloodWrite designatedBloodWrite);
}
