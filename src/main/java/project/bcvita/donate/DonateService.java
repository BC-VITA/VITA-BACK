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
import java.io.File;

@Service
@RequiredArgsConstructor
public class DonateService {
    private final DonateBoardRepository donateBoardRepository;
    private final UserRepository userRepository;


    public String writeDonateBoard(HttpSession session, DonateBoardRequest donateBoardRequest, MultipartFile file) {
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
}
