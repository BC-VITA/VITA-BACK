package project.bcvita.user.repository.volunteer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.volunteer.Volunteer;
import project.bcvita.user.entity.volunteer.VolunteerRegister;

import java.util.List;

@Repository
public interface VolunteerRegisterRepository extends JpaRepository<VolunteerRegister, Long> {
    Volunteer findByVolunteerId(String volunteerId);

    VolunteerRegister findById(String volunteerBoardId);

    List<VolunteerRegister> findAllByVolunteerType(String volunteerType);


}

