package project.bcvita.user.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.user.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserID(String userID);

    //User findAllByUserID(String userID);

    User findByUserIDAndUserPW(String userID, String userPW);

    List<User> findAllBy();

    boolean existsByUserID(String userID);

    boolean existsByUserName(String userName);

    User findByUserName(String name);



}
