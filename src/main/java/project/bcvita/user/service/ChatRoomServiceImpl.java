package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import project.bcvita.MessageState;
import project.bcvita.user.dto.ChatMessage;
import project.bcvita.user.dto.ChatRoomRespDTO;
import project.bcvita.user.entity.BloodChatMessage;
import project.bcvita.user.repository.BloodChatMessageRepository;
import project.bcvita.user.repository.BloodChatRoomRepository;
import project.bcvita.user.repository.UserRepository;

import java.awt.print.Pageable;
import java.lang.reflect.Member;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatService {
    private final BloodChatRoomRepository bloodChatRoomRepository;
    private final UserRepository userRepository;
    private final BloodChatMessageRepository bloodChatMessageRepository;

    public ChatRoomRespDTO getChatRoomMessage(String req, String reps, Pageable pageable) {
        String chatRoomUUID = getChatRoomUUID(req, reps);

        Page<BloodChatMessage> byChatRoomRoomUUID = bloodChatMessageRepository.findByChatRoomRoomUUID(chatRoomUUID, pageable);
        updateMessageState(req);
        return new ChatRoomRespDTO(chatRoomUUID, parseChatRoomEntityToDTO(byChatRoomRoomUUID));

    }
    public void updateMessageState(String req) {
        Member byUsername = userRepository.findByUsername(req);

        List<ChatMessage> entityList = bloodChatMessageRepository.findByMessageStateAndReceiver(MessageState.NOT_READ, byUsername);
        for (ChatMessage msg: entityList
        ) {
            if (msg.getReceiver().getUsername().equals(byUsername.getUsername())) {
                msg.updateMessageState(MessageState.READ);
                bloodChatMessageRepository.save(msg);
            }
        }
    }
}
