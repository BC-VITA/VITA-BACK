package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.ReviewRegister;
import project.bcvita.user.entity.User;

import java.util.List;

@Repository
public interface ReviewRegisterRepository extends JpaRepository<ReviewRegister,Long> {

    List<ReviewRegister> findAllByReviewType(String reviewType);


    ReviewRegister findById(String reviewId);

    List<ReviewRegister> findAllByUser(User user);

    List<ReviewRegister> findAllByUserAndReviewType(User user, String reviewType );



}
