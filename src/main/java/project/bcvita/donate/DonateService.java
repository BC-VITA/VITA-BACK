package project.bcvita.donate;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.bcvita.donate.dto.request.DonateBoardRequest;
import project.bcvita.donate.dto.response.DonateBoardResponse;
import project.bcvita.donate.enttiy.DonateBoard;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.UserRepository;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class DonateService {
    private final DonateBoardRepository donateBoardRepository;
    private final UserRepository userRepository;


    public String writeDonateBoard(HttpSession session, DonateBoardRequest donateBoardRequest) {
        //String loginId = (String) session.getAttribute("loginId");
        User user = userRepository.findByUserID(donateBoardRequest.getUserId());
        System.out.println("user = " + user);
        if(user.isAdmin() == false) {
            return "관리자가 아니므로 저장 안됨";
        }
        DonateBoard donateBoard = new DonateBoard();
        donateBoard.create(donateBoardRequest.getTitle(), donateBoardRequest.getContent(), donateBoardRequest.getImageUrl(),user);
        donateBoardRepository.save(donateBoard);
        return "기부 게시글 저장";
    }
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

}
