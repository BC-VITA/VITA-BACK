package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.user.dto.request.ReviewRegisterRequestDto;
import project.bcvita.user.dto.response.ReviewRegisterResponse;
import project.bcvita.user.entity.ReviewRegister;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.VolunteerRegister;
import project.bcvita.user.repository.ReviewRegisterRepository;
import project.bcvita.user.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRegisterRepository reviewRegisterRepository;
    private final UserRepository userRepository;

    @Transactional
    public String reviewRegister(HttpSession session, ReviewRegisterRequestDto requestDto) {
        String loginId = (String) session.getAttribute("loginId");
        User byUserID = userRepository.findByUserID(loginId);
        ReviewRegister reviewRegister = new ReviewRegister();
        if (requestDto.getReviewType().equals("designatedBlood")) {
            reviewRegister.setImg(requestDto.getImg());
            reviewRegister.setContent(requestDto.getContent());
            reviewRegister.setTitle(requestDto.getTitle());
            reviewRegister.setReviewType(requestDto.getReviewType());
            reviewRegister.setUser(byUserID);
            reviewRegisterRepository.save(reviewRegister);

        } else if (requestDto.getReviewType().equals("Blood")) {
            reviewRegister.setImg(requestDto.getImg());
            reviewRegister.setContent(requestDto.getContent());
            reviewRegister.setTitle(requestDto.getTitle());
            reviewRegister.setReviewType(requestDto.getReviewType());
            reviewRegister.setUser(byUserID);
            reviewRegisterRepository.save(reviewRegister);
        }
        return "게시글 작성완료";
    }

    @Transactional
    public List<ReviewRegisterResponse> boardListResponseList(String reviewType) {
        List<ReviewRegister> reviewRegisters = reviewRegisterRepository.findAllByReviewType(reviewType);
        List<ReviewRegisterResponse> reviewRegisterResponses = new ArrayList<>();
        for(ReviewRegister reviewRegister : reviewRegisters) {
            reviewRegisterResponses.add(new ReviewRegisterResponse(reviewRegister.getReviewType(), reviewRegister.getImg(), reviewRegister.getContent(), reviewRegister.getTitle()));
        }
        return reviewRegisterResponses;
    }


}