package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.user.dto.request.BoardCreateRequestDto;
import project.bcvita.user.dto.request.testDto;
import project.bcvita.user.dto.response.BoardTestListResponse;
import project.bcvita.user.dto.response.UserListResponse;
import project.bcvita.user.entity.DesignatedBloodWrite;
import project.bcvita.user.entity.DesignatedBloodWriteUser;
import project.bcvita.user.entity.Test;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.DesignatedBloodWriteRepository;
import project.bcvita.user.repository.DesignatedBloodWriteUserRepository;
import project.bcvita.user.repository.TestRepository;
import project.bcvita.user.repository.UserRepository;

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
