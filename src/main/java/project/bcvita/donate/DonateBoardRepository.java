package project.bcvita.donate;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.bcvita.donate.enttiy.DonateBoard;

public interface DonateBoardRepository extends JpaRepository<DonateBoard, Long> {

    Page<DonateBoard> findAll(Pageable pageable);
}
