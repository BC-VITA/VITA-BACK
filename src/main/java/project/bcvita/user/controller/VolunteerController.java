package project.bcvita.user.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.VolunteerJoinRequestDto;
import project.bcvita.user.dto.request.VolunteerRequestDto;
import project.bcvita.user.dto.request.VolunteerReservationRequestDto;
import project.bcvita.user.dto.response.*;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.VolunteerRegister;
import project.bcvita.user.entity.VolunteerReservation;
import project.bcvita.user.repository.VolunteerRegisterRepository;
import project.bcvita.user.repository.VolunteerRepository;
import project.bcvita.user.service.VolunteerService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("volunteer")
@RequiredArgsConstructor
@Valid
public class VolunteerController {
    private final VolunteerService volunteerService;

    private final VolunteerRegisterRepository volunteerRegisterRepository;

    private final VolunteerRepository volunteerRepository;


    //봉사 기업-단체 회원가입
    @PostMapping("/join")
    public ResponseEntity volunteerJoin(@Valid @RequestBody VolunteerJoinRequestDto request){
        if(volunteerService.volunteerJoin(request).equals("Success")){
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    

    //봉사 기업-단체 게시글 등록
    @PostMapping("/board")
    public String volunteerCreate(HttpSession session, @RequestBody VolunteerRequestDto requestDto){
        return volunteerService.volunteerCreate(session, requestDto);
    }

    //봉사 작성된 게시물 list api
    @GetMapping("/board/list")
    public List<VolunteerRegisterResponse> boardListResponseList(String userId, @RequestParam String volunteerType) {
        return volunteerService.boardListResponseList(userId,volunteerType);
    }
    // 봉사 신청할때 신청자 정보 뿌려주는 api
    @GetMapping("/reservation")
    @Transactional
    public VolunteerReservationUserInfoResponse volunteerReservationUserInfo(String userId) {
        return volunteerService.volunteerReservationUserInfo(userId);
    }

    //봉사예약 -> 봉사 예약하고 봉사 내용을 담는 ui에 해당 response로 프론트가 처리하면 돼서  "봉사 내용을 담는 ui"에 대한 api는 필요없
    @PostMapping("/reservation")
    public VolunteerReservationSaveResponseDto volunteerReservation(@RequestBody VolunteerReservationRequestDto requestDto){
        return volunteerService.volunteerReservation( requestDto);
    }

    @GetMapping("/reservation/list")
    public List<VolunteerReservationResponse> reservationResponse() {
        return volunteerService.reservationResponse();
    }

    //봉사 게시글을 보고 신청한 사람들 list
    // 승인 반려 참여완료에 대한게 없는 이유는 해당 내용은 프론트에서 화면에 뿌려주고 아래 api호출할때 승인이면 승인이라고 값을 보내주면 됨
    @GetMapping("/reservation/{volunteerBoardId}/list")
    public List<VolunteerRequestUserResponse> volunteerRequestUser(@PathVariable("volunteerBoardId") Long volunteerBoardId) {
        return volunteerService.volunteerRequestUser(volunteerBoardId);
    }

    //봉사 신청한 사람들 승인, 반려, 참여완료 결정하는곳
    @PostMapping("/reservation/list/{reservationId}")
    public String volunteerStatus(@PathVariable Long reservationId, String status) {
        return volunteerService.volunteerStatus(reservationId,status);
    }

    @PostMapping("/pdf")
    public void donatePdfDownload(Long  registerId) throws Exception {
        volunteerService.pdfDownload(registerId);

    }


}

