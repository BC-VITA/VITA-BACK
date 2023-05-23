package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bcvita.user.entity.BloodChatRoom;

import java.util.UUID;

public interface BloodChatRoomRepository extends JpaRepository<BloodChatRoom, UUID> {
    //Optional<BloodChatRoom> findByReqUserAndRepsUser(Member req, Member reps);
    //BloodChatRoom findByRoomID(String id);
}
