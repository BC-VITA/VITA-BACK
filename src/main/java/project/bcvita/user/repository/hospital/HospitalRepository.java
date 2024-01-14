package project.bcvita.user.repository.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.hospital.Hospital;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    Hospital findByHospitalIdAndHospitalPw(String hospitalId, String hospitalPw);

    Optional<Hospital> findById(Long hospitalId);

}
