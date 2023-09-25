package project.bcvita.donate;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.bcvita.donate.dto.response.DonateBoardInterface;
import project.bcvita.donate.enttiy.Donate;
import project.bcvita.donate.enttiy.DonateBoard;
import project.bcvita.user.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface DonateBoardRepository extends JpaRepository<DonateBoard, Long> {

    Page<DonateBoard> findAll(Pageable pageable);

    DonateBoard findById(String boardId);

    List<DonateBoard> findAllBy();

    @Query("SELECT d.title as title, SUM(d.pointHistory) as sum FROM DonateBoard d GROUP BY d.title")
    List<DonateBoardInterface> findByPointHistory();

    @Query("SELECT SUM(d.pointHistory) FROM DonateBoard d")
    Long findAllByPointHistory();
}
