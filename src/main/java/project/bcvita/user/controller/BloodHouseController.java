package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.*;
import project.bcvita.user.dto.response.*;
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


//    @PostMapping("/reservation")
//    public String reservation(HttpSession session, @RequestBody BloodHouseReservationRequestDto bloodHouseReservationRequestDto) {
//        return bloodHouseService.bloodHouseReservation(session,bloodHouseReservationRequestDto);
//    }
//
//    @GetMapping("/reservation/list")
//    public List<BloodHouseReservationResponse> bloodHouseReservationResponseList() {
//        return bloodHouseService.reservationResponses();
//    }

//    @GetMapping("/house/filter")
//    public List<BloodHouseResponse> reservationFilter(@RequestParam(required = false) String area, @RequestParam(required = false) String centerName, @RequestParam(required = false) String bloodHouseAddress, @RequestParam(required = false) String bloodHousePhoneNumber, @RequestParam(required = false) String wholeBlood, @RequestParam(required = false) String plasma, @RequestParam(required = false) String platelet) {
//        return bloodHouseService.reservationFilter(area,centerName, bloodHouseAddress, bloodHousePhoneNumber, wholeBlood, plasma, platelet);
//    }


    //헌혈 버스 찾기 list 출력 api
    @GetMapping("/bus/list")
    public List<BloodHouseBusResponse> bloodHouseBusResponseList() {
        return bloodHouseService.busSearchResponseList();
    }


    //헌혈의 집 등록 구현
    @PostMapping("house/register")
    public String bloodHouseRegister(HttpSession session, @RequestBody BloodHouseRegisterRequestDto bloodHouseRegisterRequestDto) {
        return bloodHouseService.bloodHouseRegister(session, bloodHouseRegisterRequestDto);
    }

    //헌혈의 집 등록 list api 구현
    @GetMapping("/house/register/list")
    public List<BloodHouseRegisterResponse> bloodHouseRegisterResponses() {
        return bloodHouseService.registerResponseList();
    }


    //헌혈의집 사용자에게 보여지는 헌혈 예약 페이지 ui 리스트
    @GetMapping("/house/registerReservation/list")
        public List<BloodHouseReservationResponse> bloodHouseReservationResponses(@RequestBody RequestCenterNameDto centerName) {
            return bloodHouseService.registerReservationResponse(centerName);
    }
}
