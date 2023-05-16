package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.user.dto.request.BloodHouseRegisterRequestDto;
import project.bcvita.user.dto.request.BloodHouseReservationRequestDto;
import project.bcvita.user.dto.request.BloodHouseReservationSaveRequestDto;
import project.bcvita.user.dto.response.BloodHouseBusResponse;
import project.bcvita.user.dto.response.BloodHouseRegisterResponse;
import project.bcvita.user.dto.response.BloodHouseReservationResponse;
import project.bcvita.user.dto.response.BloodHouseResponse;
import project.bcvita.user.entity.*;
import project.bcvita.user.repository.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BloodHouseService {
    //헌혈의집 등록하는 로그인은 -> 관리자가 다 담당!
    private final BloodHouseRepository bloodHouseRepository;
    private final UserRepository userRepository;

    private final BloodHouseReservationRepository bloodHouseReservationRepository;

    private final BloodHouseBusRepository bloodHouseBusRepository;

    private final BloodHouseRegisterRepository bloodHouseRegisterRepository;

    //헌혈의집 list 출력
    public List<BloodHouseResponse> bloodHouseResponseList() {
        List<BloodHouse> bloodHouses = bloodHouseRepository.findAll();
        List<BloodHouseResponse> bloodHouseResponse = new ArrayList<>();
        for (BloodHouse bloodHouse : bloodHouses) {
            bloodHouseResponse.add(new BloodHouseResponse(bloodHouse.getId(), bloodHouse.getArea(), bloodHouse.getCenterName(), bloodHouse.getBloodHouseAddress(), bloodHouse.getBloodHousePhoneNumber(), bloodHouse.getLatitude(), bloodHouse.getLongitude(), bloodHouse.getWeekdayTime(), bloodHouse.getSaturdayTime(), bloodHouse.getSundayRestTime(), bloodHouse.getRestTime()));

        }
        return bloodHouseResponse;
    }


    //헌혈의집 예약 list 출력
//    public List<BloodHouseReservationResponse> reservationResponses() {
//        List<BloodHouseReservation> bloodHouseReservations = bloodHouseReservationRepository.findAll();
//        List<BloodHouseReservationResponse> bloodHouseReservationResponses = new ArrayList<>();
//        for (BloodHouseReservation bloodHouseReservation : bloodHouseReservations) {
//            BloodHouse bloodHouse = bloodHouseRepository.findByCenterName(bloodHouseReservation.getBloodHouse().getCenterName()).get();
//            if(bloodHouse == null) {
//                throw new IllegalArgumentException("BloodHouse 값이 null");
//            }
//            bloodHouseReservationResponses.add(new BloodHouseReservationResponse(bloodHouseReservation.getBloodHouse().getCenterName(), bloodHouseReservation.getBloodHouse().getDate(), bloodHouseReservation.getTime(), bloodHouseReservation.getWholeBlood(), bloodHouseReservation.getPlasma(), bloodHouseReservation.getPlatelet()));
//
//        }
//        return bloodHouseReservationResponses;
//    }


    //헌혈 예약 filter 구현 부분
//    public List<BloodHouseResponse>reservationFilter(String area, String centerName, String bloodHouseAddress, String bloodHousePhoneNumber, String wholeBlood, String plasma, String platelet) {
//        List<BloodHouse> bloodHouseList = null;
//        if(area != null) { //지역
//            bloodHouseList = bloodHouseRepository.bloodHouseArea(area);
//        } else if(bloodHouseAddress != null) { //주소
//            bloodHouseList = bloodHouseRepository.bloodHouseAddress(bloodHouseAddress);
//        } else if (wholeBlood != null) { //전혈
//            bloodHouseList = bloodHouseRepository.filterWholeBlood(wholeBlood);
//        } else if (plasma != null) { //혈장
//            bloodHouseList = bloodHouseRepository.filterPlasma(plasma);
//        } else if (platelet != null) { //혈소판
//            bloodHouseList = bloodHouseRepository.filterPlatelet(platelet);
//        } else if (centerName != null) { //센터명
//            bloodHouseList = bloodHouseRepository.bloodHouseCenterName(centerName);
//        } else if (bloodHousePhoneNumber != null) { //전화번호
//            bloodHouseList = bloodHouseRepository.bloodHousePhoneNumber(bloodHousePhoneNumber);
//        } else if (area != null && wholeBlood != null) { //지역 + 전혈
//            bloodHouseList = bloodHouseRepository.areaAndWholeBlood(area, wholeBlood);
//        } else if (area != null && plasma != null) { //지역 + 혈장
//            bloodHouseList = bloodHouseRepository.areaAndPlasma(area, plasma);
//        } else if (area != null && platelet != null) { //지역 + 혈소판
//            bloodHouseList = bloodHouseRepository.areaAndPlatelet(area, platelet);
//        } else if (area != null && bloodHouseAddress != null) { //지역 + 주소
//            bloodHouseList = bloodHouseRepository.areaAndAddress(area,String.valueOf(bloodHouseAddress));
//        } else if (area != null && centerName != null) { //지역 + 센터명
//            bloodHouseList = bloodHouseRepository.areaAndCenterName(area, centerName);
//        } else if (wholeBlood != null && plasma != null) { //전혈+혈장
//            bloodHouseList = bloodHouseRepository.wholeBloodAndPlasma(wholeBlood,plasma);
//        } else if (wholeBlood != null && platelet != null) {//전혈+혈소판
//            bloodHouseList = bloodHouseRepository.wholeBloodAndPlatelet(wholeBlood, platelet);
//        } else if (plasma != null && platelet != null) {//혈장+혈소판
//            bloodHouseList = bloodHouseRepository.plasmaAndPlatelet(plasma, platelet);
//        } else if (bloodHousePhoneNumber != null && area != null) {//전화번호+지역
//            bloodHouseList = bloodHouseRepository.PhoneNumberAndArea(bloodHousePhoneNumber,area);
//        } else if (bloodHousePhoneNumber != null && centerName != null) {//전화번호+센터명
//            bloodHouseList = bloodHouseRepository.PhoneNumberAndCenterName(bloodHousePhoneNumber, centerName);
//        } else if (bloodHousePhoneNumber != null && bloodHouseAddress != null) {//전화번호+주소
//            bloodHouseList = bloodHouseRepository.PhoneNumberAndAddress(bloodHousePhoneNumber, bloodHouseAddress);
//        } else if (wholeBlood != null && plasma != null && platelet != null) { //전혈+혈장+혈소판
//            bloodHouseList = bloodHouseRepository.wholeBloodAndPlasmaAndPlatelet(wholeBlood, plasma, platelet);
//        } else if (area != null && wholeBlood != null && bloodHouseAddress != null) {//지역+전혈+주소
//            bloodHouseList = bloodHouseRepository.wholeBloodAreaAddress(area,wholeBlood,bloodHouseAddress);
//        } else if (area != null && plasma != null && bloodHouseAddress != null) {//지역+혈장+주소
//            bloodHouseList = bloodHouseRepository.plasmaAreaAddress(area,plasma,bloodHouseAddress);
//        } else if (area != null && platelet != null && bloodHouseAddress != null) {//지역+혈소판+주소
//            bloodHouseList = bloodHouseRepository.plateletAreaAddress(area,platelet,bloodHouseAddress);
//        } else if (area != null && centerName != null && bloodHouseAddress != null) {//지역+센터명+주소
//            bloodHouseList = bloodHouseRepository.areaCenterNameAddress(area,centerName,bloodHouseAddress);
//        } else if (area != null || wholeBlood != null) {
//            bloodHouseList = bloodHouseRepository.areaAndWholeBlood(area, wholeBlood);
//        } else if (area != null || plasma != null) {
//            bloodHouseList = bloodHouseRepository.areaAndPlasma(area, plasma);
//        } else if (area != null && platelet != null) {
//            bloodHouseList = bloodHouseRepository.areaAndPlatelet(area, platelet);
//        } else {
//            bloodHouseList = bloodHouseRepository.findAll();
//        }
//        List<BloodHouseResponse> bloodResultList = new ArrayList<>();
//        for (BloodHouse post : bloodHouseList) {
//            //DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWrite(post).orElse(null);
////            if(designatedBloodWriteUser == null) {
////                continue;
////            }
//            BloodHouseResponse bloodHouseResponse = new BloodHouseResponse(post.getId(), post.getArea(), post.getCenterName(), post.getBloodHouseAddress(), post.getBloodHousePhoneNumber(),post.getLatitude(),post.getLongitude(),post.getWeekdayTime(),post.getSaturdayTime(),post.getSundayRestTime(), post.getRestTime(), post.getWholeBlood(), post.getPlasma(), post.getPlatelet());
//            bloodResultList.add(bloodHouseResponse);
//        }
//
//        return bloodResultList;
//    }


    //헌혈 버스 찾기 게시물 list 출력 api
    @Transactional(readOnly = true)
    public List<BloodHouseBusResponse> busSearchResponseList() {
        List<BloodHouseBus> bloodHouseBusList = bloodHouseBusRepository.findAll();
        List<BloodHouseBusResponse> bloodHouseBusResponses = new ArrayList<>();
        for (BloodHouseBus bloodHouseBus : bloodHouseBusList) {
            bloodHouseBusResponses.add(new BloodHouseBusResponse(bloodHouseBus.getId(), bloodHouseBus.getDate(), bloodHouseBus.getLatitude(), bloodHouseBus.getLongitude(), bloodHouseBus.getBusAddress(), bloodHouseBus.getPlace(), bloodHouseBus.getBusTime(), bloodHouseBus.getBusPhoneNumber(), bloodHouseBus.getBusPersonNumber()));
        }
        return bloodHouseBusResponses;
    }


    //헌혈의 집 등록 api구현
    @Transactional
    public String bloodHouseRegister(HttpSession session, BloodHouseRegisterRequestDto bloodHouseRegisterRequestDto) {

        //헌혈의집 관리자 로그인이 구현 되지 않아 임시 user 로그인으로 대체
        User user = (User) session.getAttribute("user");

        BloodHouseRegister bloodHouseRegister = new BloodHouseRegister();
        BloodHouse bloodHouse = bloodHouseRepository.findByCenterName(bloodHouseRegisterRequestDto.getCenterName());
        bloodHouseRegister.setDate(bloodHouseRegisterRequestDto.getDate());
        bloodHouseRegister.setTime(bloodHouseRegisterRequestDto.getTime());
        bloodHouseRegister.setWholeBlood(bloodHouseRegisterRequestDto.getWholeBlood());
        bloodHouseRegister.setPlasma(bloodHouseRegisterRequestDto.getPlasma());
        bloodHouseRegister.setPlatelet(bloodHouseRegisterRequestDto.getPlatelet());
        bloodHouseRegister.setBloodHouse(bloodHouse);

        bloodHouseRegisterRepository.save(bloodHouseRegister);
        return "헌혈의 집 등록 완료";
    }

    //헌혈의 집 등록 list 출력 api -> 그냥 등록되는 list 보기 위한 것(프론트 상관 x)
    @Transactional(readOnly = true)
    public List<BloodHouseRegisterResponse> registerResponseList() {
        List<BloodHouseRegister> bloodHouseRegisterList = bloodHouseRegisterRepository.findAll();
        List<BloodHouseRegisterResponse> bloodHouseRegisterResponses = new ArrayList<>();
        for (BloodHouseRegister bloodHouseRegister : bloodHouseRegisterList) {
            bloodHouseRegisterResponses.add(new BloodHouseRegisterResponse(bloodHouseRegister.getDate(), bloodHouseRegister.getWholeBlood(), bloodHouseRegister.getPlasma(), bloodHouseRegister.getPlatelet(), bloodHouseRegister.getTime()));
        }
        return bloodHouseRegisterResponses;
    }

    //헌혈의 집 예약 api 구현
    @Transactional
    public String bloodHouseReservation(HttpSession session, BloodHouseReservationSaveRequestDto bloodHouseReservationSaveRequestDto) {
        String userLoginId = (String) session.getAttribute("user");
        User byUserID = userRepository.findByUserID(userLoginId);
        BloodHouseReservation bloodHouseReservation = new BloodHouseReservation();
        //BloodHouseReservationRequestDto bloodHouseReservation = new BloodHouseReservationRequestDto();
        BloodHouse bloodHouse = bloodHouseRepository.findByCenterName(bloodHouseReservationSaveRequestDto.getBloodHouseName());

        bloodHouseReservation.setBloodHouse(bloodHouse);
        //bloodHouseReservation.setWholeBlood(bloodHouseReservationSaveRequestDto.getWholeBlood());
        //bloodHouseReservation.setPlasma(bloodHouseReservationSaveRequestDto.getPlasma());
        //bloodHouseReservation.setPlatelet(bloodHouseReservationSaveRequestDto.getPlatelet());
        bloodHouseReservation.setIsBloodType(bloodHouseReservationSaveRequestDto.getIsBloodType());
        bloodHouseReservation.setTime(bloodHouseReservationSaveRequestDto.getTime());
        bloodHouseReservation.setLocalDateTime(bloodHouseReservationSaveRequestDto.getLocalDateTime());
        bloodHouseReservation.setLocalDateTime(LocalDateTime.now());
        bloodHouseReservation.setDate(bloodHouseReservationSaveRequestDto.getDate());
        bloodHouseReservation.setUser(byUserID);

        //System.out.println(bloodHouseReservation.getWholeBlood());
        //System.out.println(bloodHouseReservation.getPlasma());
        //System.out.println(bloodHouseReservation.getPlatelet());

        System.out.println(bloodHouseReservation.getIsBloodType());
        System.out.println(bloodHouseReservation.getDate());
        System.out.println(bloodHouseReservation.getTime());
        System.out.println(bloodHouseReservation.getBloodHouse());
        System.out.println(bloodHouseReservation.getUser());
        System.out.println(bloodHouseReservation.getLocalDateTime());
        bloodHouseReservationRepository.save(bloodHouseReservation);
        return "예약완료";
    }






    //헌혈의집 등록 list(헌혈 종류 리스트 형식 -> 사용자에게 보여지는 예약 ui) 출력
    /*public List<BloodHouseReservationResponse> registerReservationResponse(BloodHouseReservationRequestDto bloodHouseReservationRequestDto) {
        BloodHouse blood = bloodHouseRepository.findByCenterName(centerName.getCenterName());
        System.out.println("centerName = " + centerName.getCenterName());
        if (blood == null) {
            throw new IllegalArgumentException("BloodHouse 값이 null");
        }
        List<BloodHouseRegister> bloodHouseRegisterList = bloodHouseRegisterRepository.findAllByBloodHouse(blood);
        List<BloodHouseReservationResponse> bloodHouseReservationResponses = new ArrayList<>();
        for (BloodHouseRegister bloodHouseRegister : bloodHouseRegisterList) {
            bloodHouseReservationResponses.add(new BloodHouseReservationResponse((bloodHouseRegister.getBloodHouse().getCenterName()), bloodHouseRegister.getDate(),bloodHouseRegister.getTime(),bloodHouseRegister.getWholeBlood(), bloodHouseRegister.getPlasma(), bloodHouseRegister.getPlatelet()));
        }
        return bloodHouseReservationResponses;
    }*/


    public List<BloodHouseReservationResponse> registerReservationResponse(BloodHouseReservationRequestDto bloodHouseReservationRequestDto) {
        List<BloodHouseRegister> bloodHouseRegisterList = bloodHouseRegisterRepository.findAll();
        List<BloodHouseReservationResponse> bloodHouseReservationResponses = new ArrayList<>();
        for (BloodHouseRegister bloodHouseRegister : bloodHouseRegisterList) {
            if (bloodHouseRegister.getBloodHouse() == null) {
                continue;
            }
            bloodHouseReservationResponses.add(new BloodHouseReservationResponse((bloodHouseRegister.getBloodHouse().getCenterName()),
                    bloodHouseRegister.getDate(), bloodHouseRegister.getTime(), bloodHouseRegister.getWholeBlood(), bloodHouseRegister.getPlasma(), bloodHouseRegister.getPlatelet(),bloodHouseRegister.getLocalDateTime().now()));
        }
        return bloodHouseReservationResponses;
    }
}
//헌혈 후기 게시물 작성 api


