package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.bcvita.user.dto.request.ReviewCommentDto;
import project.bcvita.user.dto.request.ReviewRegisterRequestDto;
import project.bcvita.user.dto.response.ReviewCommentResponse;
import project.bcvita.user.dto.response.ReviewRegisterResponse;
import project.bcvita.user.entity.ReviewComment;
import project.bcvita.user.entity.ReviewRegister;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.VolunteerRegister;
import project.bcvita.user.repository.ReviewCommentRepository;
import project.bcvita.user.repository.ReviewRegisterRepository;
import project.bcvita.user.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRegisterRepository reviewRegisterRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public String reviewRegister(HttpSession session, ReviewRegisterRequestDto requestDto, MultipartFile file) {
        try{
            String path = "C:\\vita";
            File destination = new File(path + File.separator + file.getOriginalFilename());
            file.transferTo(destination);
            //String loginId = (String) session.getAttribute("loginId");
            User byUserID = userRepository.findByUserID(requestDto.getUserId());
            ReviewRegister reviewRegister = new ReviewRegister();
            reviewRegister.setImg(destination.getAbsolutePath());
            reviewRegister.setContent(requestDto.getContent());
            reviewRegister.setTitle(requestDto.getTitle());
            reviewRegister.setUser(byUserID);
            if (requestDto.getReviewType().equals("designatedBlood")) {
                reviewRegister.setReviewType(requestDto.getReviewType());
            } else if (requestDto.getReviewType().equals("Blood")) {
                reviewRegister.setReviewType(requestDto.getReviewType());
            }
            reviewRegisterRepository.save(reviewRegister);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "게시글 작성완료";
    }


    /*
    try {
            String path = "C:\\vita";
            File destination = new File(path + File.separator + file.getOriginalFilename());
            file.transferTo(destination);
            User user = userRepository.findByUserID(donateBoardRequest.getUserId());
            if (user.isAdmin() == false) {
                return "관리자가 아니므로 저장 안됨";
            }
            DonateBoard donateBoard = new DonateBoard();
            donateBoard.create(donateBoardRequest.getTitle(), donateBoardRequest.getContent(), destination.getAbsolutePath(), user);
            donateBoardRepository.save(donateBoard);
        }catch (Exception e) {
            e.printStackTrace();
        }

     */


    @Transactional
    public List<ReviewRegisterResponse> boardListResponseList(String reviewType) {
        List<ReviewRegister> reviewRegisters = reviewRegisterRepository.findAllByReviewType(reviewType);
        List<ReviewRegisterResponse> reviewRegisterResponses = new ArrayList<>();
        for(ReviewRegister reviewRegister : reviewRegisters) {
            reviewRegisterResponses.add(new ReviewRegisterResponse(reviewRegister.getReviewType(), reviewRegister.getImg(), reviewRegister.getContent(), reviewRegister.getTitle()));
        }
        return reviewRegisterResponses;
    }


    //댓글 작성하기
    @Transactional
    public ReviewCommentDto writeComment(HttpSession session, ReviewCommentDto reviewCommentDto){
        ReviewComment reviewComment = new ReviewComment();
        reviewComment.setComment(reviewCommentDto.getComment());

        //게시판 번호로 게시글 찾기
        User user = userRepository.findByUserID(reviewCommentDto.getUserId());
        reviewComment.setUser(user);
        ReviewComment save = reviewCommentRepository.save(reviewComment);
        ReviewRegister reviewRegister = reviewRegisterRepository.findById(reviewComment.getId())
                .orElseThrow(() ->
                     new IllegalArgumentException("게시판을 찾을 수 없습니다.")
                );

        reviewComment.setReviewRegister(reviewRegister);


        return new ReviewCommentDto(user.getUserID(),
                reviewRegister.getId(), save.getComment(), save.getLocalDateTime().now(),
                false);
    }


    //게시글에 해당하는 전체 댓글 불러오기
//    @Transactional
//    public List<ReviewCommentResponse> commentResponseList(ReviewCommentDto requestDto) {
//
//        ReviewRegister reviewRegister1 = reviewRegisterRepository.findById(requestDto.getReviewRegisterId()).get();
//        User user = userRepository.findByUserID(requestDto.getUserId());
//        List<ReviewCommentResponse> reviewCommentResponses = new ArrayList<>();
//        for (ReviewRegister reviewRegister : reviewRegister1) {
//            reviewCommentResponses.add(new ReviewCommentResponse(reviewRegister.getUser().getUserID(),reviewRegister.getId(), requestDto.getComment(), requestDto.getLocalDateTime(),requestDto.isReport()));
//        }
//        return reviewCommentResponses;
//
//    }
}
