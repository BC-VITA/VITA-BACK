package project.bcvita.donate;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
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
import project.bcvita.donate.dto.request.DonatePointRequest;
import project.bcvita.donate.dto.response.*;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.VolunteerRegister;
import project.bcvita.user.entity.VolunteerReservation;
import project.bcvita.user.repository.VolunteerRegisterRepository;
import project.bcvita.user.repository.VolunteerReservationRepository;
import project.bcvita.user.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donate")
public class DonateController {

    private final DonateService donateService;
    private final UserService userService;
    private final VolunteerReservationRepository volunteerReservationRepository;
    private final VolunteerRegisterRepository volunteerRegisterRepository;

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
        System.out.println(userService.loginId(session));
        return donateService.donateUserPoint(session, userId);
    }

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


    @GetMapping("/mypage/history")
    public List<DonateHistoryResponse> myPageDonateHistory(String  userId) {
        return donateService.myPageDonateHistory(userId);
    }
    //pdf에 들어가 내용
    @GetMapping("/pdf")
    public DonatePdfResponse pdfContent(String  userId, Long donateId) {
        return donateService.pdfContent(userId,donateId);
    }


//    @PostMapping("/pdf")
//    public void donatePdfDownload(Long  registerId) throws Exception {
//        // 1. 다운로드받은날짜_자원봉사한타이틀_확인서
//        // 2. 자원봉사한타이틀_확인서
//        VolunteerReservation volunteerReservation = volunteerReservationRepository.findById(
//                registerId).get();
//        User user = volunteerReservation.getUser();
//        VolunteerRegister volunteerRegister = volunteerReservation.getVolunteerRegister();
//        String title = volunteerRegister.getTitle();
//        Document document = new Document();
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\pdf\\" +title+"_확인서.pdf" ));
//
//        document.open(); // 웹페이지에 접근하는 객체를 연다
//
//        // 6) 준비한 설정값들을 활용해 Font 객체를 생성해줍니다. 생성자에 들어가는 인자는 BaseFont 와 사이즈 입니다.
//        BaseFont baseFont = BaseFont.createFont("NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//        Font donateTitleFont = new Font(baseFont, 30);
//
//        Paragraph donateTitle = new Paragraph("자원봉사활동 확인서", donateTitleFont);
//        donateTitle.setAlignment(Element.ALIGN_CENTER);
//        document.add(donateTitle);
//        document.add(Chunk.NEWLINE);
//
//        Font donateUserFont = new Font(baseFont, 15);
//        Paragraph donateUser = new Paragraph("기부자 : " + user.getUserName(), donateUserFont);
//        donateUser.setAlignment(Element.ALIGN_LEFT);
//        document.add(donateUser);
//        document.add(Chunk.NEWLINE);
//        String year = user.getUserBirth().substring(0,4);
//        String month = user.getUserBirth().substring(4,6);
//        String day = user.getUserBirth().substring(6);
//        Paragraph birthInfo = new Paragraph("생년월일 : " + year +"년 " + month +"월 " + day +"일"   , donateUserFont);
//        birthInfo.setAlignment(Element.ALIGN_LEFT);
//        document.add(birthInfo);
//        document.add(Chunk.NEWLINE);
//
//        Paragraph phoneInfo = new Paragraph("전화번호 : " + user.getUserPhoneNumber(), donateUserFont);
//        phoneInfo.setAlignment(Element.ALIGN_LEFT);
//        document.add(phoneInfo);
//        document.add(Chunk.NEWLINE);
//        document.add(Chunk.NEWLINE);
//
//
//        int totalTime = Integer.parseInt(volunteerRegister.getVolunteerEndTime().split(":")[0]) - Integer.parseInt(volunteerRegister.getVolunteerStartTime().split(":")[0]);
//
//        Paragraph volunteerActiveTimeInfo = new Paragraph("활동시간 : 총 " + totalTime +"시간"  , donateUserFont);
//        volunteerActiveTimeInfo.setAlignment(Element.ALIGN_CENTER);
//        document.add(volunteerActiveTimeInfo);
//        document.add(Chunk.NEWLINE);
//
//
//        Paragraph volunteerTitleInfo = new Paragraph("봉사활동 내용 : " + title, donateUserFont);
//        volunteerTitleInfo.setAlignment(Element.ALIGN_CENTER);
//        document.add(volunteerTitleInfo);
//        document.add(Chunk.NEWLINE);
//
//        Paragraph volunteerActiveDateInfo = new Paragraph("자원봉사 활동기간 : " + volunteerRegister.getVolunteerStartDate() + " ~ " +volunteerRegister.getVolunteerEndDate() , donateUserFont);
//        volunteerActiveDateInfo.setAlignment(Element.ALIGN_CENTER);
//        document.add(volunteerActiveDateInfo);
//        document.add(Chunk.NEWLINE);
//        document.add(Chunk.NEWLINE);
//        document.add(Chunk.NEWLINE);
//
//
//
//        Font contentFont = new Font(baseFont, 20);
//        Paragraph content = new Paragraph("위와 같은 자원봉사 활동에 참여하였음을 확인함.", contentFont);
//        content.setAlignment(Element.ALIGN_CENTER);
//        document.add(content);
//        document.add(Chunk.NEWLINE);
//        LocalDate currentDate = LocalDate.now();
//        Paragraph date = new Paragraph(currentDate.getYear() + "년 " + currentDate.getMonthValue() + "월" + currentDate.getDayOfMonth() +"일", contentFont);
//        date.setAlignment(Element.ALIGN_CENTER);
//        document.add(date);
//        document.add(Chunk.NEWLINE);
//
//        Font infoFont = new Font(baseFont, 12);
//
//        Paragraph info = new Paragraph("본 증명서는 인터넷으로 발급되었으며, 자원봉사 포탈시스템(www.1365.go.kr) 의" +
//                "확인서 조회 메뉴를 통해 문서발급 번호 입력으로 내용의 위변조 여부를 확인해 주십시오. " +
//                "다만 문서 확인 번호를 통한 확인은 발급일로부터 90일까지 가능합니다.\n " +
//                "* 발급확인서에 표시된 유관기관의 자원봉사 실적은 자원봉사 포탈 시스템과 연계를 통해 " +
//                "취합된 실적 정보 입니다", infoFont);
//        document.add(info);
//        document.add(Chunk.NEWLINE);
//
//        document.close(); // 저장이 끝났으면 document객체를 닫는다.
//
//    }

    //관리자 기부 게시물 통계 api
    @GetMapping("/donate-board-statistics")
    public List<DonateBoardStatisticsResponse> adminDonateStatistics() {
        return donateService.adminDonateStatistics();
    }




}

