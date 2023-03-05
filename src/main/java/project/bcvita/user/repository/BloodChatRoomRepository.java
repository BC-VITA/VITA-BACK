package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bcvita.user.entity.BloodChatRoom;

import java.util.UUID;

public interface BloodChatRoomRepository extends JpaRepository<BloodChatRoom, UUID> {
}
