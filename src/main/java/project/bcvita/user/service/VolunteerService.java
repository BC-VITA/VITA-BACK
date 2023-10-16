package project.bcvita.user.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.heart.WishListRepository;
import project.bcvita.heart.entity.WishList;
import project.bcvita.user.dto.request.VolunteerJoinRequestDto;
import project.bcvita.user.dto.request.VolunteerRequestDto;
import project.bcvita.user.dto.request.VolunteerReservationRequestDto;
import project.bcvita.user.dto.response.*;
import project.bcvita.user.entity.*;
import project.bcvita.user.repository.UserRepository;
import project.bcvita.user.repository.VolunteerRegisterRepository;
import project.bcvita.user.repository.VolunteerRepository;
import project.bcvita.user.repository.VolunteerReservationRepository;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;
    private final VolunteerRegisterRepository volunteerRegisterRepository;
    private final UserRepository userRepository;

    private final VolunteerReservationRepository volunteerReservationRepository;
    private final UserService userService;
    private final WishListRepository wishListRepository;

    //봉사 기업-단체 회원가입 api
    @Transactional
    public String volunteerJoin(VolunteerJoinRequestDto request) {
        if(!request.getVolunteerConfirmPw().equals(request.getVolunteerPw())) {
            throw new IllegalArgumentException("비밀번호 일치하지 않습니다.");
        }
        volunteerRepository.save(Volunteer.builder()
                .volunteerId(request.getVolunteerId())
                .volunteerPw(request.getVolunteerPw())
                .volunteerGroupName(request.getVolunteerGroupName())
                //.volunteerField(request.getVolunteerField())
                .volunteerPhoneNumber(request.getVolunteerPhoneNumber())
                .build());
        return "Success";
    }

    //봉사 기업-단체 봉사 게시글 등록 api (개인-시간/활동)
    @Transactional
    public String volunteerCreate(HttpSession session, VolunteerRequestDto requestDto) {
        String volunteerLoginId = (String) session.getAttribute("loginId");
        //Volunteer volunteer = volunteerRepository.findByVolunteerId(volunteerLoginId);


        VolunteerRegister volunteerRegister = new VolunteerRegister();
         if (requestDto.getVolunteerType().equals("otherGroup")) {
            volunteerRegister.setVolunteerArea(requestDto.getVolunteerArea());
            volunteerRegister.setRequireGroup(requestDto.getRequireGroup());
            volunteerRegister.setVolunteerTarget(requestDto.getVolunteerTarget());
            volunteerRegister.setVolunteerStartDate(requestDto.getVolunteerStartDate());
            volunteerRegister.setVolunteerEndDate(requestDto.getVolunteerEndDate());
            volunteerRegister.setVolunteerStartTime(requestDto.getVolunteerStartTime());
            volunteerRegister.setVolunteerEndTime(requestDto.getVolunteerEndTime());
            volunteerRegister.setTitle(requestDto.getTitle());
            volunteerRegister.setContent(requestDto.getContent());
            volunteerRegister.setUrl(requestDto.getUrl());
        }else  {
            volunteerRegister.setVolunteerArea(requestDto.getVolunteerArea());
            volunteerRegister.setVolunteerPlace(requestDto.getVolunteerPlace());
            volunteerRegister.setVolunteerAddress(requestDto.getVolunteerAddress());
            volunteerRegister.setLatitude(requestDto.getLatitude());
            volunteerRegister.setLongitude(requestDto.getLongitude());
            volunteerRegister.setVolunteerSeekStartDate(requestDto.getVolunteerSeekStartDate());
            volunteerRegister.setVolunteerSeekEndDate(requestDto.getVolunteerSeekEndDate());
            volunteerRegister.setVolunteerStartDate(requestDto.getVolunteerStartDate());
            volunteerRegister.setVolunteerEndDate(requestDto.getVolunteerEndDate());
            volunteerRegister.setNeedVolunteerNumber(requestDto.getNeedVolunteerNumber());
            volunteerRegister.setVolunteerStartTime(requestDto.getVolunteerStartTime());
            volunteerRegister.setVolunteerEndTime(requestDto.getVolunteerEndTime());
            volunteerRegister.setVolunteerActivityWeek(requestDto.getVolunteerActivityWeek());
             volunteerRegister.setVolunteerType(requestDto.getVolunteerType());
            volunteerRegister.setVolunteerField(requestDto.getVolunteerField());
            volunteerRegister.setActivitySection(requestDto.getActivitySection());
            volunteerRegister.setVolunteerTarget(requestDto.getVolunteerTarget());
            volunteerRegister.setVolunteerPersonType(requestDto.getVolunteerPersonType());
            volunteerRegister.setManagerName(requestDto.getManagerName());
            volunteerRegister.setManagerEmail(requestDto.getManagerEmail());
            volunteerRegister.setTitle(requestDto.getTitle());
            volunteerRegister.setContent(requestDto.getContent());
            //volunteerRegister.setVolunteer(volunteer);
        }
        //volunteerRegister.setVolunteer(volunteer);
        volunteerRegisterRepository.save(volunteerRegister);
        return "게시글 작성 완료";
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

    @Transactional
    public List<VolunteerRegisterResponse> boardListResponseList(String userId,String volunteerType) {

        //String loginId = userService.loginId(session);
        User user = userRepository.findByUserID(userId);

        List<VolunteerRegister>  volunteerRegisters = volunteerRegisterRepository.findAllByVolunteerType(volunteerType);


        List<VolunteerRegisterResponse> volunteerRegisterResponses = new ArrayList<>();
        for(VolunteerRegister volunteerRegister : volunteerRegisters) {

            VolunteerRegister volunteerRegister1 = volunteerRegisterRepository.findById(volunteerRegister.getId()).orElse(null);
            boolean isWishList = false;

            if (volunteerRegister1 == null) {
                continue;
            }
            if(user != null) {
                WishList wishList = wishListRepository.findByUserAndVolunteerRegister(user, volunteerRegister).orElse(null);
                if(wishList != null) {
                    isWishList = true;
                }
            }
            volunteerRegisterResponses.add(new VolunteerRegisterResponse(volunteerRegister1.getId(),volunteerRegister.getVolunteerType(), volunteerRegister.getContent(), volunteerRegister.getTitle(),
                    volunteerRegister.getVolunteerStartDate(), volunteerRegister.getVolunteerEndDate(), volunteerRegister.getVolunteerStartTime(), volunteerRegister.getVolunteerEndTime(), volunteerRegister.getNeedVolunteerNumber(), volunteerRegister.getVolunteerArea(),
                    volunteerRegister.getActivitySection(), volunteerRegister.getVolunteerField(), volunteerRegister.getVolunteerSeekStartDate(), volunteerRegister.getVolunteerSeekEndDate(), volunteerRegister.getVolunteerAddress(),
                    volunteerRegister.getVolunteerTarget(), volunteerRegister.getVolunteerPlace(), volunteerRegister.getLatitude(), volunteerRegister.getLongitude(), volunteerRegister.getVolunteerActivityWeek(), volunteerRegister.getQualification(), volunteerRegister.getVolunteerPersonType(),
                    volunteerRegister.getVolunteerActivityNumber(), volunteerRegister.getRequirements(), volunteerRegister.getManagerName(), volunteerRegister.getManagerEmail(), volunteerRegister.getRequireGroup(), volunteerRegister.getUrl(),wishListRepository.countByVolunteerRegister(volunteerRegister),isWishList));
        }
        return volunteerRegisterResponses;
    }

    //봉사 예약
    @Transactional
    public VolunteerReservationSaveResponseDto volunteerReservation(VolunteerReservationRequestDto volunteerReservationRequestDto){
        User byUserID = userRepository.findByUserID(volunteerReservationRequestDto.getUserId());
        VolunteerRegister volunteerRegister = volunteerRegisterRepository.findById(volunteerReservationRequestDto.getVolunteerBoardId()).get();
        VolunteerReservation volunteerReservation = new VolunteerReservation();
        volunteerReservation.setVolunteerDate(volunteerReservationRequestDto.getVolunteerDate());
        volunteerReservation.setVolunteerType(volunteerReservationRequestDto.getVolunteerKind());
        volunteerReservation.setUser(byUserID);
        //volunteerReservation.setInformationAgree(volunteerReservationRequestDto.isInformationAgree());
        volunteerReservation.setBoardStatus(volunteerReservationRequestDto.getVolunteerStatus());
        volunteerReservation.setVolunteerRegister(volunteerRegister);
        volunteerReservation.setBoardStatus("접수");
        volunteerReservationRepository.save(volunteerReservation);
        return new VolunteerReservationSaveResponseDto(
                "대기중",volunteerReservation.getVolunteerDate(),
                volunteerRegister.getVolunteerStartTime(),volunteerRegister.getVolunteerEndTime(),
                volunteerRegister.getVolunteerAddress(),volunteerRegister.getVolunteerPlace(),
                volunteerRegister.getVolunteerType(),byUserID.getUserName(),byUserID.getUserPhoneNumber(), volunteerRegister.getId()

        );
    }

    // 봉사 신청할때 신청자 정보 뿌려주는 기능
    public VolunteerReservationUserInfoResponse volunteerReservationUserInfo(String userId) {
        User byUserID = userRepository.findByUserID(userId);
        return new VolunteerReservationUserInfoResponse(byUserID.getUserPhoneNumber(), byUserID.getUserEmail());
    }

    //봉사 예약 내역
    // 해당부분은 마이페이지 부분에서 사용할 경우 프론트가 필요한 내용만 보여주면 됨
    public List<VolunteerReservationResponse> reservationResponse() {
        List<VolunteerReservation> volunteerReservationList = volunteerReservationRepository.findAll();
        List<VolunteerReservationResponse> volunteerReservationResponses = new ArrayList<>();
        for(VolunteerReservation  volunteerReservation : volunteerReservationList) {
            if (volunteerReservation.getVolunteerRegister() == null) {
                continue;
            }
            volunteerReservationResponses.add(new VolunteerReservationResponse(volunteerReservation.getVolunteerRegister().getTitle(), volunteerReservation.getVolunteerDate(), volunteerReservation.getVolunteerRegister().getVolunteerStartTime(), volunteerReservation.getVolunteerRegister().getVolunteerEndTime()
            , volunteerReservation.getVolunteerRegister().getVolunteerAddress(), volunteerReservation.getVolunteerRegister().getVolunteerType(), volunteerReservation.getVolunteerRegister().getManagerName(), volunteerReservation.getVolunteerRegister().getManagerPhoneNumber(), volunteerReservation.getVolunteerRegister().getVolunteerPlace()));
        }
        return volunteerReservationResponses;
    }


    public List<VolunteerRequestUserResponse> volunteerRequestUser(Long volunteerBoardId) {
        VolunteerRegister volunteerRegister = volunteerRegisterRepository.findById(volunteerBoardId).get();
        List<VolunteerReservation> volunteerReservation = volunteerReservationRepository.findAllByVolunteerRegister(volunteerRegister);

        return volunteerReservation.stream().map(x -> new VolunteerRequestUserResponse(x.getId(),x.getUser().getUserName()))
                .toList();

    }

    @Transactional
    public String volunteerStatus(Long reservationId ,String status) {
        VolunteerReservation volunteerReservation = volunteerReservationRepository.findById(reservationId).get();
        volunteerReservation.setBoardStatus(status);
        return status;
    }


    public void pdfDownload(Long registerId) throws Exception {
        VolunteerReservation volunteerReservation = volunteerReservationRepository.findById(
                registerId).get();
        User user = volunteerReservation.getUser();
        VolunteerRegister volunteerRegister = volunteerReservation.getVolunteerRegister();
        String title = volunteerRegister.getTitle();
        Document document = new Document();
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\pdf\\" +title+"_확인서.pdf" ));
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("\\Users\\minji\\pdf\\" +title+"_확인서.pdf" ));

        document.open(); // 웹페이지에 접근하는 객체를 연다

        // 6) 준비한 설정값들을 활용해 Font 객체를 생성해줍니다. 생성자에 들어가는 인자는 BaseFont 와 사이즈 입니다.
        BaseFont baseFont = BaseFont.createFont("NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font donateTitleFont = new Font(baseFont, 30);

        Paragraph donateTitle = new Paragraph("자원봉사활동 확인서", donateTitleFont);
        donateTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(donateTitle);
        document.add(Chunk.NEWLINE);

        Font donateUserFont = new Font(baseFont, 15);
        Paragraph donateUser = new Paragraph("기부자 : " + user.getUserName(), donateUserFont);
        donateUser.setAlignment(Element.ALIGN_LEFT);
        document.add(donateUser);
        document.add(Chunk.NEWLINE);
        String year = user.getUserBirth().substring(0,4);
        String month = user.getUserBirth().substring(5,7);
        String day = user.getUserBirth().substring(8);
        Paragraph birthInfo = new Paragraph("생년월일 : " + year +"년 " + month +"월 " + day +"일"   , donateUserFont);
        birthInfo.setAlignment(Element.ALIGN_LEFT);
        document.add(birthInfo);
        document.add(Chunk.NEWLINE);

        Paragraph phoneInfo = new Paragraph("전화번호 : " + user.getUserPhoneNumber(), donateUserFont);
        phoneInfo.setAlignment(Element.ALIGN_LEFT);
        document.add(phoneInfo);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);


        int totalTime = Integer.parseInt(volunteerRegister.getVolunteerEndTime().split(":")[0]) - Integer.parseInt(volunteerRegister.getVolunteerStartTime().split(":")[0]);

        Paragraph volunteerActiveTimeInfo = new Paragraph("활동시간 : 총 " + totalTime +"시간"  , donateUserFont);
        volunteerActiveTimeInfo.setAlignment(Element.ALIGN_CENTER);
        document.add(volunteerActiveTimeInfo);
        document.add(Chunk.NEWLINE);


        Paragraph volunteerTitleInfo = new Paragraph("봉사활동 내용 : " + title, donateUserFont);
        volunteerTitleInfo.setAlignment(Element.ALIGN_CENTER);
        document.add(volunteerTitleInfo);
        document.add(Chunk.NEWLINE);

        Paragraph volunteerActiveDateInfo = new Paragraph("자원봉사 활동기간 : " + volunteerRegister.getVolunteerStartDate() + " ~ " +volunteerRegister.getVolunteerEndDate() , donateUserFont);
        volunteerActiveDateInfo.setAlignment(Element.ALIGN_CENTER);
        document.add(volunteerActiveDateInfo);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);



        Font contentFont = new Font(baseFont, 20);
        Paragraph content = new Paragraph("위와 같은 자원봉사 활동에 참여하였음을 확인함.", contentFont);
        content.setAlignment(Element.ALIGN_CENTER);
        document.add(content);
        document.add(Chunk.NEWLINE);
        LocalDate currentDate = LocalDate.now();
        Paragraph date = new Paragraph(currentDate.getYear() + "년 " + currentDate.getMonthValue() + "월" + currentDate.getDayOfMonth() +"일", contentFont);
        date.setAlignment(Element.ALIGN_CENTER);
        document.add(date);
        document.add(Chunk.NEWLINE);

        Font infoFont = new Font(baseFont, 12);

        Paragraph info = new Paragraph("본 증명서는 인터넷으로 발급되었으며, 자원봉사 포탈시스템(www.1365.go.kr) 의" +
                "확인서 조회 메뉴를 통해 문서발급 번호 입력으로 내용의 위변조 여부를 확인해 주십시오. " +
                "다만 문서 확인 번호를 통한 확인은 발급일로부터 90일까지 가능합니다.\n " +
                "* 발급확인서에 표시된 유관기관의 자원봉사 실적은 자원봉사 포탈 시스템과 연계를 통해 " +
                "취합된 실적 정보 입니다", infoFont);
        document.add(info);
        document.add(Chunk.NEWLINE);

        document.close(); // 저장이 끝났으면 document객체를 닫는다.
    }



}


