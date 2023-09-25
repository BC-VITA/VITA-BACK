package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.bcvita.user.dto.response.AdminVolunteerStatisticsInterface;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.VolunteerRegister;
import project.bcvita.user.entity.VolunteerReservation;

import java.util.List;

@Repository
public interface VolunteerReservationRepository extends JpaRepository<VolunteerReservation, Long> {

    List<VolunteerReservation> findAllByUser(User user);

    List<VolunteerReservation> findAllByVolunteerRegister(VolunteerRegister volunteerRegister);

    List<VolunteerReservation> findAllByUserAndBoardStatus(User user, String status);

    List<VolunteerReservation> findById(String boardId);

//    @Query("SELECT FUNCTION('EXTRACT', YEAR, vr.volunteerDate) AS year, " +
//            "FUNCTION('EXTRACT', MONTH, vr.volunteerDate) AS month, " +
//            "COUNT(*) AS count " +
//            "FROM VolunteerReservation vr " +
//            "WHERE vr.boardStatus = '참여완료' " +
//            "GROUP BY FUNCTION('EXTRACT', YEAR, vr.volunteerDate), FUNCTION('EXTRACT', MONTH, vr.volunteerDate) " +
//            "ORDER BY year, month")
//    List<AdminVolunteerStatisticsInterface> getMonthlyVolunteerStatistics();
//
//    List<VolunteerReservation> findByBoardStatusOrderByVolunteerDateAsc(String boardStatus);

}
