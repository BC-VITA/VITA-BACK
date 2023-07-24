package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.bcvita.heart.WishListRepository;
import project.bcvita.point.PointHistoryRepository;
import project.bcvita.point.entity.PointHistory;
import project.bcvita.user.dto.request.ReviewCommentDto;
import project.bcvita.user.dto.request.ReviewRegisterRequestDto;
import project.bcvita.user.dto.response.ReviewCommentResponse;
import project.bcvita.user.dto.response.ReviewDetail;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRegisterRepository reviewRegisterRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PointHistoryRepository pointHistoryRepository;


    @Transactional
    public String reviewRegister(ReviewRegisterRequestDto requestDto, MultipartFile file) {
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
            reviewRegister.setLocalDateTime(requestDto.getLocalDateTime().now());
            if (requestDto.getReviewType().equals("designatedBlood")) {
                reviewRegister.setReviewType(requestDto.getReviewType());
            } else if (requestDto.getReviewType().equals("Blood")) {
                reviewRegister.setReviewType(requestDto.getReviewType());
            }

            reviewRegisterRepository.save(reviewRegister);
            byUserID.setUserPoint(byUserID.getUserPoint() + 500); // 사용자 포인트 누적
            PointHistory pointHistory = new PointHistory();
            pointHistory.reviewRegisterPoint(byUserID,500, reviewRegister); // 리뷰 작성시 pointHistory 테이블에 저장
            pointHistoryRepository.save(pointHistory);
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
            reviewRegisterResponses.add(new ReviewRegisterResponse(reviewRegister.getReviewType(), reviewRegister.getImg(), reviewRegister.getContent(), reviewRegister.getTitle(), reviewRegister.getId()));
        }
        return reviewRegisterResponses;
    }


    //댓글 작성하기
    @Transactional
    public ReviewCommentResponse writeComment(Long registerId,ReviewCommentDto reviewCommentDto){
        ReviewRegister reviewRegister = reviewRegisterRepository.findById(registerId)
                .orElseThrow(() ->
                        new IllegalArgumentException("게시판을 찾을 수 없습니다.")
                );

        ReviewComment reviewComment = new ReviewComment();
        reviewComment.setComment(reviewCommentDto.getComment());

        //게시판 번호로 게시글 찾기
        //String loginId = (String) session.getAttribute("loginId");
        User user = userRepository.findByUserID(reviewCommentDto.getUserId());
        reviewComment.setUser(user);
        reviewComment.setReviewRegister(reviewRegister);
        ReviewComment save = reviewCommentRepository.save(reviewComment);

        return new ReviewCommentResponse(user.getUserID(),user.getUserName(),registerId,
                 save.getComment(),save.getLocalDateTime().now(),
                false);
    }

    public ReviewDetail reviewDetail(Long registerId) {
        ReviewRegister reviewRegister = reviewRegisterRepository.findById(registerId).orElse(null);
        if(reviewRegister == null) {
            throw new IllegalArgumentException("게시판을 찾을 수 없습니다.");
        }
        List<ReviewCommentResponse> reviewCommentResponses = reviewCommentRepository.findAllByReviewRegister(reviewRegister).stream().map(x -> new ReviewCommentResponse(
                  x.getUser().getUserID(),x.getUser().getUserName(),x.getReviewRegister().getId(),x.getComment(),
                x.getLocalDateTime(),x.isReport()
        )).collect(Collectors.toList());
        return new ReviewDetail(reviewRegister.getTitle(),reviewRegister.getContent(),reviewCommentResponses);



    }
}
