package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.Test;
import java.util.Optional;

    @Repository
    public interface TestRepository extends JpaRepository<Test,Long> {

    }


