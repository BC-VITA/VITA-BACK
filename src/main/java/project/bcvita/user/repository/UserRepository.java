package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserID(String userID);

    User findAllByUserID(String userID);

    User findByUserIDAndUserPW(String userID, String userPW);

    List<User> findByUserName(String userName);
    List<User> findAllBy();

    boolean existsByUserID(String userID);

    boolean existsByUserName(String userName);


}
