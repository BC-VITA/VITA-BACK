package project.bcvita.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import project.bcvita.user.dto.ChatRoom;
import project.bcvita.user.entity.BloodChatRoom;

import java.lang.reflect.Member;
import java.util.Optional;
import java.util.UUID;

public interface BloodChatRoomRepository extends JpaRepository<BloodChatRoom, UUID> {
    Optional<BloodChatRoom> findByReqUserAndRepsUser(Member req, Member reps);
    BloodChatRoom findByRoomUUID(String UUID);
}
