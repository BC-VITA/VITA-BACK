package project.bcvita.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.bcvita.user.entity.BloodChatMessage;
import project.bcvita.user.entity.BloodChatRoom;
import project.bcvita.user.entity.BloodHouse;

public interface BloodChatMessageRepository extends JpaRepository<BloodChatMessage, Long> {
    //BloodChatRoom findByRoomID(String id);
    //Page<BloodChatMessage> findByBloodChatRoomAndId(String id, Pageable pageable);
}
