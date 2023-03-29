package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.dto.request.HospitalLoginRequestDto;
import project.bcvita.user.entity.Hospital;
import project.bcvita.user.entity.User;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    Hospital findByHospitalIdAndHospitalPw(String hospitalId, String hospitalPw);

}
