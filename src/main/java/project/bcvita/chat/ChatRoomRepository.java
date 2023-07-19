package project.bcvita.chat;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.bcvita.chat.entity.ChatRoom;
import project.bcvita.user.entity.DesignatedBloodWriteUser;
import project.bcvita.user.entity.User;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    List<ChatRoom> findAllByBoardWriter(User boardWriter);

    @Query("SELECT cr FROM ChatRoom cr WHERE cr.boardSeeUser = :boardWriter OR cr.boardWriter = :boardWriter")
    List<ChatRoom> findAllByBoardWriterOrBoardSeeUser(User boardWriter);
    List<ChatRoom> findAllByBoardSeeUser(User boardWriter);

    ChatRoom findByIdAndBoardSeeUser(Long id, User user);

    List<ChatRoom> findAllByBoardSeeUserAndIsAgreeIsTrue(User user);

    ChatRoom findByBoardWriterAndBoardSeeUserAndDesignatedBloodWriteUser(User sender, User receiver, DesignatedBloodWriteUser designatedBloodWriteUser);



}
