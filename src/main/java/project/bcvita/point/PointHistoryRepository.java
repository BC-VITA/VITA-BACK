package project.bcvita.point;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bcvita.point.entity.PointHistory;
import project.bcvita.user.entity.User;

import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

    List<PointHistory> findAllByUser(User user);
}
