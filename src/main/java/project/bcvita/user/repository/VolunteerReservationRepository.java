package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.VolunteerRegister;
import project.bcvita.user.entity.VolunteerReservation;
import project.bcvita.user.service.DateStatistics;

import java.util.List;

@Repository
public interface VolunteerReservationRepository extends JpaRepository<VolunteerReservation, Long> {

    List<VolunteerReservation> findAllByUser(User user);

    List<VolunteerReservation> findAllByVolunteerRegister(VolunteerRegister volunteerRegister);

    List<VolunteerReservation> findAllByUserAndBoardStatus(User user, String status);

    List<VolunteerReservation> findById(String boardId);

    @Query(value = "SELECT EXTRACT(YEAR FROM volunteer_date) AS year, " +
            "EXTRACT(MONTH FROM volunteer_date) AS month, " +
    " COUNT(*) AS count " +
    "FROM volunteer_reservation " +
    "WHERE board_status = '참여완료' AND EXTRACT(YEAR FROM volunteer_date) = :year " +
    "GROUP BY EXTRACT(YEAR FROM volunteer_date), EXTRACT(MONTH FROM volunteer_date) " +
    "ORDER BY EXTRACT(YEAR FROM volunteer_date), EXTRACT(MONTH FROM volunteer_date) ", nativeQuery = true)
    List<DateStatistics> getMonthlyVolunteerStatistics(@Param("year") int year);

    List<VolunteerReservation> findByBoardStatusOrderByVolunteerDateAsc(String boardStatus);

    @Query(value =  "SELECT EXTRACT(YEAR FROM vr.volunteer_date) AS year, register.volunteer_field as volunteerField,  " +
            " COUNT(*) AS count " +
            "FROM volunteer_reservation vr " +
            "INNER JOIN volunteer_register register ON vr.volunteer_register_id = register.id " +
            "WHERE vr.board_status = '참여완료' AND EXTRACT(YEAR FROM vr.volunteer_date) = :year AND register.volunteer_field IS NOT NULL " +
            "GROUP BY EXTRACT(YEAR FROM vr.volunteer_date), register.volunteer_field " +
            "ORDER BY EXTRACT(YEAR FROM vr.volunteer_date) ", nativeQuery = true)
    List<DateStatistics> getMonthlyVolunteerStatisticsV2(@Param("year") int year);
}
