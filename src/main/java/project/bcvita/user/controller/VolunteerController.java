package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.VolunteerJoinRequestDto;
import project.bcvita.user.dto.request.VolunteerRequestDto;
import project.bcvita.user.dto.request.VolunteerReservationRequestDto;
import project.bcvita.user.dto.response.VolunteerRegisterResponse;
import project.bcvita.user.dto.response.VolunteerReservationResponse;
import project.bcvita.user.repository.VolunteerRegisterRepository;
import project.bcvita.user.repository.VolunteerRepository;
import project.bcvita.user.service.VolunteerService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    @GetMapping("/board/list")
    public List<VolunteerRegisterResponse> boardListResponseList(HttpSession session, @RequestParam String volunteerType) {
        return volunteerService.boardListResponseList(session,volunteerType);
    }

    //봉사예약
    @PostMapping("/reservation")
    public String volunteerReservation(HttpSession session, @RequestBody VolunteerReservationRequestDto requestDto){
        return volunteerService.volunteerReservation(session, requestDto);
    }

    @GetMapping("/reservation/list")
    public List<VolunteerReservationResponse> reservationResponse() {
        return volunteerService.reservationResponse();
    }
}

