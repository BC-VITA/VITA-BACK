package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.ReviewComments;

@Repository
public interface ReviewCommentsRepository extends JpaRepository<ReviewComments,Long> {
}
