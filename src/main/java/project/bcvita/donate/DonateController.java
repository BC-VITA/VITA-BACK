package project.bcvita.donate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.bcvita.donate.dto.request.DonateBoardRequest;
import project.bcvita.donate.dto.response.DonateBoardResponse;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donate")
public class DonateController {

    private final DonateService donateService;

    /*@Autowired
    public DonateController(DonateService donateService) {
        this.donateService = donateService;
    }

    @PostMapping("/board")
    public ResponseEntity<String> createDonationPost(
        @RequestParam("userId") String userId,
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam("imageFile") MultipartFile imageFile
    ) {
        try {
            // 이미지 파일 업로드 처리
            String imageUrl = donateService.uploadImage(imageFile);

            // 게시물 생성 로직 수행
            donateService.createDonationPost(userId, title, content, imageUrl);
            return new ResponseEntity<>("게시물이 성공적으로 생성되었습니다.", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("게시물 생성에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     */



    @PostMapping("/board")
    public String writeDonateBoard(HttpSession session, @RequestBody DonateBoardRequest donateBoardRequest) {
        return donateService.writeDonateBoard(session, donateBoardRequest);
    }

    @GetMapping("/board")
    public Page<DonateBoardResponse> boardList(@PageableDefault(size = 6)Pageable pageable) {
        return donateService.boardList(pageable);
    }

    @GetMapping("/board/{id}")
    public DonateBoardResponse boardDetail(@PathVariable Long id) {
        return donateService.boardDetail(id);
    }


}
