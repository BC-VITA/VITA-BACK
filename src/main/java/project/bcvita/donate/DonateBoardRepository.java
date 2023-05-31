package project.bcvita.donate;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.donate.enttiy.Donate;
import project.bcvita.donate.enttiy.DonateBoard;
import project.bcvita.user.entity.User;

import java.util.List;

@Repository
public interface DonateBoardRepository extends JpaRepository<DonateBoard, Long> {

    Page<DonateBoard> findAll(Pageable pageable);

    DonateBoard findById(String boardId);

}
