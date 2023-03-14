package project.bcvita.user.repository;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageState;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import project.bcvita.user.entity.BloodChatMessage;
import project.bcvita.user.entity.BloodHouse;

import java.awt.print.Pageable;
import java.lang.reflect.Member;
import java.util.List;

public interface BloodChatMessageRepository extends JpaRepository<BloodChatMessage, Long> {
    Page<BloodChatMessage> findByBloodChatRoomRoomUUID(String uuid, Pageable pageable);
    List<BloodChatMessage> findByMessageStateAndReceiver(MessageState messageState, Member receiver);
}
