package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.Hospital;
import project.bcvita.user.entity.User;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {

}
