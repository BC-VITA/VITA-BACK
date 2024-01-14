package project.bcvita.user.repository.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.review.ReviewComment;
import project.bcvita.user.entity.review.ReviewRegister;

import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment,Long> {
    List<ReviewComment> findAllByReviewRegister(ReviewRegister reviewRegister);
}