package project.bcvita.donate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.donate.enttiy.Donate;

@Repository
public interface DonatePointRepository extends JpaRepository<Donate, Long> {

}
