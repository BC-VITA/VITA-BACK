package project.bcvita.chat;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.bcvita.chat.entity.ChatRoom;
import project.bcvita.user.entity.User;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    List<ChatRoom> findAllByBoardWriter(User boardWriter);
    List<ChatRoom> findAllByBoardSeeUser(User boardWriter);

}
