package project.bcvita.donate;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.bcvita.donate.dto.request.DonateBoardRequest;
import project.bcvita.donate.dto.request.DonatePointRequest;
import project.bcvita.donate.dto.response.*;
import project.bcvita.donate.enttiy.Donate;
import project.bcvita.donate.enttiy.DonateBoard;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonateService {
    private final DonateBoardRepository donateBoardRepository;
    private final UserRepository userRepository;

    private final DonatePointRepository donatePointRepository;

    public String writeDonateBoard(HttpSession session, DonateBoardRequest donateBoardRequest, MultipartFile file) {
        try {
            String path = "C:\\Users\\이민렬\\Desktop\\test\\vita\\public\\images";
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

        return "기부 게시글 저장";
    }

    //기부 게시판 list api
    public Page<DonateBoardResponse> boardList(Pageable pageable) {
        Page<DonateBoard> donateBoard = donateBoardRepository.findAll(pageable);
        return  donateBoard.map(board ->
                {
                    DonateBoardResponse donateBoardResponse = new DonateBoardResponse(board.getId(), board.getTitle(), board.getImageUrl(), board.getContent());
                    return donateBoardResponse;
                }
        );
    }

    public DonateBoardResponse boardDetail(Long id) {
        DonateBoard donateBoard = donateBoardRepository.findById(id).get();
        return new DonateBoardResponse(donateBoard.getId(),donateBoard.getTitle(),donateBoard.getImageUrl(),donateBoard.getContent());
    }

    public String uploadImage(MultipartFile imageFile) {
        String fileName = imageFile.getOriginalFilename();

        return fileName;
    }

    public void createDonationPost(String userId, String title, String content, String imageUrl){
        System.out.println("게시물 생성");
        System.out.println("유저 아이디: " + userId);
        System.out.println("제목: " + title);
        System.out.println("내용: " + content);
        System.out.println("이미지 URL = " + imageUrl);
    }

    public String imageUpload(MultipartFile file) {
        try {
            String path = "/Users/hoewoon/image";
            File uploadFile = new File(path);
            File destination = new File(path + File.separator + file.getOriginalFilename());
            file.transferTo(destination);
        }catch (Exception e) {

        }
        return "이미지 저장 완료";
    }

    //기부 포인트 구현 api
    public String donatePointAdd(HttpSession session, DonatePointRequest request) {
        User byUserID = userRepository.findByUserID(request.getUserId());
        DonateBoard donateBoard = donateBoardRepository.findById(request.getDonateId()).get();
        Donate donate = new Donate();
        donate.setDonateBoard(donateBoard);
        donate.setAnonymous(request.isAnonymous());
        donate.setLocalDateTime(request.getLocalDateTime().now());
        donate.setUser(byUserID);
        donate.setUsePoint(request.getUsePoint());
        donateBoard.setPointHistory(donateBoard.getPointHistory() + request.getUsePoint());

        byUserID.setUserPoint(request.getFinalPoint());
        donatePointRepository.save(donate);
        return "기부완료";
    }

    //기부할때 나의 포인트 getApi
    public Integer donateUserPoint(HttpSession session, String userId) {
        User user = userRepository.findByUserID(userId);
        return user.getUserPoint();
    }


    //개인 기부 영수증 -> 포인트 기부하면 다음페이지에 개인 기부 영수증 부분 나옴
//    public DonateDetail donateReceipt(String userId, Long donateId) {
//        DonateBoard donateBoard = donateBoardRepository.findById(donateId).get();
//        User user = userRepository.findByUserID(userId);
//        List<DonatePointResponse> donatePointResponses = new ArrayList<>();
//        List<Donate> donateList = donatePointRepository.findAllByUserAndDonateBoardOrderByLocalDateTimeAsc(user, donateBoard);
//        int total = 0;
//        for (Donate donate : donateList) {
//            int donatePoint = donate.getUsePoint() == null ? 0 : donate.getUsePoint().intValue();
//            total += donatePoint;
//            donatePointResponses.add(new DonatePointResponse(donate.getUser().getUserID(), donate.getDonateBoard().getId(), donate.getLocalDateTime(), donatePoint, donate.getDonateBoard().getImageUrl()));
//        }
//
//        return new DonateDetail(total,donatePointResponses);
//    }



    //하나의 게시글 기부 영수증 -> 개인x, 한 게시글에 기부한 사람 다 나옴
    public DonateDetail donateReceiptPerson(Long donateId) {
        DonateBoard donateBoard = donateBoardRepository.findById(donateId).get();
        List<Donate> donateList = donatePointRepository.findAllByDonateBoard(donateBoard);

        List<DonatePointResponse> donatePointResponses = new ArrayList<>();
        //List<Donate> donateList = donatePointRepository.findAllByUserAndDonateBoardOrderByLocalDateTimeAsc(user, donateBoard);
        int total = 0;
        for(Donate donate : donateList) {
            if(donate.getUser() == null) {
                continue;
            }
            int donatePoint = donate.getUsePoint() == null ? 0 : donate.getUsePoint().intValue();
            total += donatePoint;
            donatePointResponses.add(new DonatePointResponse(donate.getUser().getUserID(), donate.getDonateBoard().getTitle(), donate.getLocalDateTime(), donatePoint,
                    donate.getDonateBoard().getImageUrl()));
        }
        return new DonateDetail(total, donatePointResponses);
    }


    //기부 영수증 게시글 list
    @Transactional
    public List<DonateBoardResponse> donatePointResponses() {
        List<DonateBoard> donateList = donateBoardRepository.findAll();
        List<DonateBoardResponse> donateBoardResponses = new ArrayList<>();
        for (DonateBoard donateBoard : donateList) {
            donateBoardResponses.add(new DonateBoardResponse(donateBoard.getId(), donateBoard.getTitle(), donateBoard.getImageUrl(), donateBoard.getContent()));
        }
        return donateBoardResponses;
    }


    /*
    @Transactional
    public List<ReviewRegisterResponse> boardListResponseList(String reviewType) {
        List<ReviewRegister> reviewRegisters = reviewRegisterRepository.findAllByReviewType(reviewType);
        List<ReviewRegisterResponse> reviewRegisterResponses = new ArrayList<>();
        for(ReviewRegister reviewRegister : reviewRegisters) {
            reviewRegisterResponses.add(new ReviewRegisterResponse(reviewRegister.getReviewType(), reviewRegister.getImg(), reviewRegister.getContent(), reviewRegister.getTitle()));
        }
        return reviewRegisterResponses;
    }
     */

    /*
    public Page<DonateBoardResponse> boardList(Pageable pageable) {
        Page<DonateBoard> donateBoard = donateBoardRepository.findAll(pageable);
        return  donateBoard.map(board ->
                {
                    DonateBoardResponse donateBoardResponse = new DonateBoardResponse(board.getId(), board.getTitle(), board.getImageUrl(), board.getContent());
                    return donateBoardResponse;
                }
                );
    }
     */


    public List<DonateHistoryResponse> myPageDonateHistory(String  userId) {
        User user = userRepository.findByUserID(userId);
        List<Donate> donateHistoryList = donatePointRepository.findAllByUser(user);
        return donateHistoryList.stream().map(x ->
                new DonateHistoryResponse(x.getDonateBoard().getTitle(),x.getUsePoint(),x.getLocalDateTime())).collect(Collectors.toList());
    }

    public DonatePdfResponse pdfContent(String  userId, Long donateId) {
        User user = userRepository.findByUserID(userId);
        Donate donate = donatePointRepository.findById(donateId).get();
        return new DonatePdfResponse(user.getUserName(),donate.getUsePoint(),donate.getLocalDateTime().toLocalDate());
    }



}
