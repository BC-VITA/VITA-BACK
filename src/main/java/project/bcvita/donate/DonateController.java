package project.bcvita.donate;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.Utf8Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.bcvita.donate.dto.request.DonateBoardRequest;
import project.bcvita.donate.dto.request.DonatePointRequest;
import project.bcvita.donate.dto.response.DonateBoardResponse;
import project.bcvita.donate.dto.response.DonateDetail;
import project.bcvita.donate.dto.response.DonateHistoryResponse;
import project.bcvita.donate.dto.response.DonatePdfResponse;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.UserRepository;
import project.bcvita.user.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donate")
public class DonateController {

    private final DonateService donateService;
    private final UserService userService;
    private final UserRepository userRepository;

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
    public String writeDonateBoard(HttpSession session, @ModelAttribute DonateBoardRequest donateBoardRequest,@RequestPart(value = "file") MultipartFile file) {
        return donateService.writeDonateBoard(session, donateBoardRequest, file);
    }
    @GetMapping("/board")
    public Page<DonateBoardResponse> boardList(@PageableDefault(size = 6)Pageable pageable) {
        return donateService.boardList(pageable);
    }

    @GetMapping("/board/{id}")
    public DonateBoardResponse boardDetail(@PathVariable Long id) {
        return donateService.boardDetail(id);
    }

    @PostMapping("/donation")
    public String donatePointAdd(HttpSession session, @RequestBody DonatePointRequest request){
        return donateService.donatePointAdd(session, request);
    }

    @GetMapping("/user-point")
    public Integer donateUserPoint(HttpSession session, String userId){
        return donateService.donateUserPoint(session, userId);
    }

    //기부 내역
    @GetMapping("/mypage/history")
    public List<DonateHistoryResponse> myPageDonateHistory(HttpSession session) {
        return donateService.myPageDonateHistory(session);
    }
    //pdf에 들어가 내용
    @GetMapping("/pdf")
    public DonatePdfResponse pdfContent(HttpSession session, Long donateId) {
        return donateService.pdfContent(session,donateId);
    }


/* pdf다운받는건데 혹시 몰라서 남겨놓음
    @PostMapping("/donate-pdf")
    public void donatePdfDownload(HttpSession session) throws Exception {
        User user = userRepository.findByUserID(userService.loginId(session));
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("기부.pdf"));
        document.open(); // 웹페이지에 접근하는 객체를 연다

        // 6) 준비한 설정값들을 활용해 Font 객체를 생성해줍니다. 생성자에 들어가는 인자는 BaseFont 와 사이즈 입니다.
        BaseFont baseFont = BaseFont.createFont("NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font donateTitleFont = new Font(baseFont, 30);

        Paragraph donateTitle = new Paragraph("기부증서", donateTitleFont);
        donateTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(donateTitle);
        document.add(Chunk.NEWLINE);

        Font donateUserFont = new Font(baseFont, 15);
        Paragraph donateUser = new Paragraph("기부자 : " + user.getUserName(), donateUserFont);
        donateUser.setAlignment(Element.ALIGN_RIGHT);
        document.add(donateUser);
        document.add(Chunk.NEWLINE);

        Font contentFont = new Font(baseFont, 20);
        Paragraph content = new Paragraph("이 기부는 어려운 이웃을 위해 따뜻한 사랑과 \n" +
                "마음으로 헌혈과 봉사로 모은 포인트로 기부를 \n" +
                "해주셨기에 감사드리며 이 증서를 드립니다.", contentFont);
        content.setAlignment(Element.ALIGN_CENTER);
        document.add(content);



        document.close(); // 저장이 끝났으면 document객체를 닫는다.


    }
*/


//    @GetMapping("/donate-receipt")
//    public DonateDetail donateReceipt(String userId, Long donateId) {
//        return donateService.donateReceipt(userId,donateId);
//    }

    @GetMapping("/donate-receipt")
    public DonateDetail donateReceiptPerson(Long donateId) {
        return donateService.donateReceiptPerson(donateId);
    }

    @GetMapping("/donate-receipt-list")
    public List<DonateBoardResponse> donateBoardResponse() {
        return donateService.donatePointResponses();
    }
}

