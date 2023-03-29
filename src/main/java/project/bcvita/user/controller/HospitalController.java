package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.*;
import project.bcvita.user.dto.response.HospitalResponse;
import project.bcvita.user.dto.response.UserListResponse;
import project.bcvita.user.entity.Hospital;
import project.bcvita.user.service.HospitalService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("hospital")
@RequiredArgsConstructor
@Valid
public class HospitalController {
    private final HospitalService hospitalService;

    @PostMapping("/hospitalJoin")
        public ResponseEntity hospitalJoin(@Valid @RequestBody HospitalRequestDto hospitalRequestDto) {
            if (hospitalService.hospitalJoin(hospitalRequestDto).equals("Success")) {
                return new ResponseEntity(HttpStatus.CREATED);
            }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }


    @GetMapping("/hospitalJoinList")
    public List<HospitalResponse> joinList() {
        return hospitalService.hospitalResponseList();
    }


    @PostMapping("/password-check")
    public String passwordCheck(@Valid @RequestBody HospitalPasswordCheck hospitalPasswordCheck){
        return hospitalService.hospitalPasswordCheck(hospitalPasswordCheck);
    }

    @PostMapping("/login")
    public String loginPost(@RequestBody HospitalLoginRequestDto hospitalLoginRequestDto, HttpSession session) {
        return hospitalService.hospitalLogin(hospitalLoginRequestDto,session);
    }

    @GetMapping("/logout")
    public String logoutGet(HttpSession session) {
        return hospitalService.hospitalLogout(session);
    }
}

