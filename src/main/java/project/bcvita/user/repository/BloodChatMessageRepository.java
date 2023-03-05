package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bcvita.user.entity.BloodChatMessage;
import project.bcvita.user.entity.BloodHouse;

public interface BloodChatMessageRepository extends JpaRepository<BloodChatMessage, Long> {
}
