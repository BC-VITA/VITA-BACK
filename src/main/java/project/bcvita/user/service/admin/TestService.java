package project.bcvita.user.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.user.dto.request.info.testDto;
import project.bcvita.user.dto.response.bloodHouse.BoardTestListResponse;
import project.bcvita.user.entity.admin.Test;
import project.bcvita.user.entity.user.User;
import project.bcvita.user.repository.designatedBlood.DesignatedBloodWriteRepository;
import project.bcvita.user.repository.designatedBlood.DesignatedBloodWriteUserRepository;
import project.bcvita.user.repository.admin.TestRepository;
import project.bcvita.user.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TestService {
    private final DesignatedBloodWriteRepository designatedBloodWriteRepository;
    private final UserRepository userRepository;
    private final DesignatedBloodWriteUserRepository designatedBloodWriteUserRepository;
    private final TestRepository testRepository;

    @Transactional
    public String createTest(Long id, testDto requestDto) {
        User user = userRepository.findById(id).orElse(null);
        Test test = new Test();
        test.setTestName(requestDto.getTestName());
        testRepository.save(test);
        return "게시글 작성완료";
    }

    public List<BoardTestListResponse> boardTestListResponseList() {
        List<Test> boardTestListResponseList = testRepository.findAll();
        List<BoardTestListResponse> boardTestListResponse = new ArrayList<>();
        for (Test test : boardTestListResponseList) {
            boardTestListResponse.add(new BoardTestListResponse(test.getTestNumber(), test.getTestName()));

        }
        return boardTestListResponse;
    }

}
