package project.bcvita.user.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bcvita.user.entity.admin.Test;

@Repository
    public interface TestRepository extends JpaRepository<Test,Long> {

    }


