package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.ReviewComment;
import project.bcvita.user.entity.ReviewRegister;

import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment,Long> {
    List<ReviewRegister> findByReviewRegister(String reviewRegisterId);
}