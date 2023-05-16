package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.Volunteer;
import project.bcvita.user.entity.VolunteerRegister;

@Repository
public interface VolunteerRegisterRepository extends JpaRepository<VolunteerRegister, Long> {
    //Volunteer findByUserID(String volunteerId);
}
