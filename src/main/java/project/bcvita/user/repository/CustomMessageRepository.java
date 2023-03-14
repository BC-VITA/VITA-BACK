package project.bcvita.user.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Member;
import java.util.List;

@Repository
public interface CustomMessageRepository {
    List<NotReadMessageDTO> getNotReadMessage(Member req);
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<NotReadMessageDTO> getNotReadMessage(Member req) {
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Query nativeQuery = em.createNativeQuery(
                "select writer_id, m.username,  COUNT(writer_id) " +
                        " from chat_message " +
                        " inner join member m " +
                        " on writer_id = m.id " +
                        " where message_state = 'NOT_READ'AND receiver_id = (select id " +
                        " from member " +
                        " where username = '"+req.getUsername()+"') " +
                        "group by writer_id"
        );
        return jpaResultMapper.list(nativeQuery, NotReadMessageDTO.class);
    }
}
