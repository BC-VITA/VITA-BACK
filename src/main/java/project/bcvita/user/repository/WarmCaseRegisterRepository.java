package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.WarmCaseRegister;

import java.util.List;

@Repository
public interface WarmCaseRegisterRepository extends JpaRepository<WarmCaseRegister, Long> {

    List<WarmCaseRegister> findById(String warmCaseRegister);
}
