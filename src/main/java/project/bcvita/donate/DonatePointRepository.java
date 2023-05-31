package project.bcvita.donate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.donate.enttiy.Donate;
import project.bcvita.donate.enttiy.DonateBoard;
import project.bcvita.user.entity.User;

import java.util.List;

@Repository
public interface DonatePointRepository extends JpaRepository<Donate, Long> {

    List<Donate> findAllByUserAndDonateBoardOrderByLocalDateTimeAsc(User user, DonateBoard donateBoard);

    List<Donate> findAllByDonateBoard(DonateBoard donateBoard);

}
