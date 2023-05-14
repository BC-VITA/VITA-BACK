package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.ReviewRegister;

@Repository
public interface ReviewRegisterRepository extends JpaRepository<ReviewRegister,Long> {

}
