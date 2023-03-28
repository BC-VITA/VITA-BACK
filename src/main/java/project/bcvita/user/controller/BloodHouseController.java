package project.bcvita.user.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.BloodHouseRegisterRequestDto;
import project.bcvita.user.dto.request.BloodHouseRequestDto;
import project.bcvita.user.dto.request.BloodHouseReservationRequestDto;
import project.bcvita.user.dto.request.BoardCreateRequestDto;
import project.bcvita.user.dto.response.BloodHouseRegisterResponse;
import project.bcvita.user.dto.response.BloodHouseReservationResponse;
import project.bcvita.user.dto.response.BloodHouseResponse;
import project.bcvita.user.dto.response.BoardListResponse;
import project.bcvita.user.service.BloodHouseService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("blood")
@RequiredArgsConstructor
@Valid
public class BloodHouseController {
    private final BloodHouseService bloodHouseService;


//    @GetMapping("board/list")
//    public List<BloodHouseResponse> bloodHouseResponseList() {
//        return bloodHouseService.bloodHouseResponseList();
//    }


    @PostMapping("/reservation")
    public String reservation(HttpSession session, @RequestBody BloodHouseReservationRequestDto bloodHouseReservationRequestDto) {
        return bloodHouseService.bloodHouseReservation(session,bloodHouseReservationRequestDto);
    }

    @GetMapping("/reservation/list")
    public List<BloodHouseReservationResponse> bloodHouseReservationResponseList() {
        return bloodHouseService.reservationResponses();
    }

    @GetMapping("/house/filter")
    public List<BloodHouseResponse> reservationFilter(@RequestParam(required = false) String area, @RequestParam(required = false) String centerName, @RequestParam(required = false) String bloodHouseAddress, @RequestParam(required = false) String bloodHousePhoneNumber, @RequestParam(required = false) String wholeBlood, @RequestParam(required = false) String plasma, @RequestParam(required = false) String platelet) {
        return bloodHouseService.reservationFilter(area,centerName, bloodHouseAddress, bloodHousePhoneNumber, wholeBlood, plasma, platelet);
    }


    //헌혈의 집 등록 api
    @PostMapping("/house/register")
    public String bloodHouseRegister(HttpSession session, @RequestBody BloodHouseRegisterRequestDto bloodHouseRegisterRequestDto) {
        return bloodHouseService.bloodHouseRegister(session, bloodHouseRegisterRequestDto);
    }

    @GetMapping("/house/register/list")
    public List<BloodHouseRegisterResponse> bloodHouseRegisterResponseList() {
        return bloodHouseService.registerResponseList();
    }


}
