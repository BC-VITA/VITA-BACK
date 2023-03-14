package project.bcvita.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import project.bcvita.user.dto.ChatMessage;
//import project.bcvita.user.dto.ChatRoom;
import project.bcvita.user.dto.ChatRoomRespDTO;
import project.bcvita.user.dto.request.ChatMessageRequestDto;
import project.bcvita.user.dto.request.ChatRoomRequestDto;
import project.bcvita.user.dto.response.ChatRoomResponse;
import project.bcvita.user.entity.*;
import project.bcvita.user.repository.*;

import javax.annotation.PostConstruct;
import java.awt.print.Pageable;
import java.io.IOException;
import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.*;

public interface ChatService {
    String getChatRoomUUID(String req, String reps);
    ChatRoomRespDTO getChatRoomMessage(String req, String reps, Pageadle pageadle);


    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }




}
