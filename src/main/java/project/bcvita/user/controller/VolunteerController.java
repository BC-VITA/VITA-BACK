package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.bcvita.user.dto.request.VolunteerJoinRequestDto;
import project.bcvita.user.dto.request.VolunteerRequestDto;
import project.bcvita.user.repository.VolunteerRegisterRepository;
import project.bcvita.user.repository.VolunteerRepository;
import project.bcvita.user.service.VolunteerService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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


}
