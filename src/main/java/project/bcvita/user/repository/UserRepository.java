package project.bcvita.user.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.User;

import java.lang.reflect.Member;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserID(String userID);
    Optional<User> findByUserPW(String userPW);

}
