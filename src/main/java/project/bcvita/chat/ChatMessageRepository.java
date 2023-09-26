package project.bcvita.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.bcvita.chat.entity.ChatRoom;
import project.bcvita.chat.entity.ChattingMessage;
import project.bcvita.user.entity.User;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChattingMessage, Long> {

    List<ChattingMessage> findAllByChatRoom(ChatRoom chatRoom);

    @Query("SELECT cm.chatRoom.id FROM ChattingMessage cm WHERE cm.isRead = false AND cm.receiver = :user GROUP By cm.chatRoom")
    List<Long> alramList(@Param("user") User user);
}
