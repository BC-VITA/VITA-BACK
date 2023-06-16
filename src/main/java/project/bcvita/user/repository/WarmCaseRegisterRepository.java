package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.WarmCaseRegister;

@Repository
public interface WarmCaseRegisterRepository extends JpaRepository<WarmCaseRegister, Long> {
}
