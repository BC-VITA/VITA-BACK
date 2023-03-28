package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.user.dto.request.BloodHouseRegisterRequestDto;
import project.bcvita.user.dto.request.BloodHouseRequestDto;
import project.bcvita.user.dto.request.BloodHouseReservationRequestDto;
import project.bcvita.user.dto.request.BoardCreateRequestDto;
import project.bcvita.user.dto.response.BloodHouseRegisterResponse;
import project.bcvita.user.dto.response.BloodHouseReservationResponse;
import project.bcvita.user.dto.response.BloodHouseResponse;
import project.bcvita.user.dto.response.BoardListResponse;
import project.bcvita.user.entity.*;
import project.bcvita.user.repository.BloodHouseRepository;
import project.bcvita.user.repository.BloodHouseReservationRepository;
import project.bcvita.user.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BloodHouseService {
    private final BloodHouseRepository bloodHouseRepository;
    private final UserRepository userRepository;

    private final BloodHouseReservationRepository bloodHouseReservationRepository;

    public List<BloodHouseResponse> bloodHouseResponseList() {
        List<BloodHouse> bloodHouses = bloodHouseRepository.findAll();
        List<BloodHouseResponse>  bloodHouseResponse= new ArrayList<>();
        for (BloodHouse bloodHouse : bloodHouses) {
            bloodHouseResponse.add(new BloodHouseResponse(bloodHouse.getId(), bloodHouse.getArea(), bloodHouse.getCenterName(), bloodHouse.getBloodHouseAddress(), bloodHouse.getBloodHousePhoneNumber(), bloodHouse.getLatitude(), bloodHouse.getLongitude(), bloodHouse.getWeekdayTime(), bloodHouse.getSaturdayTime(),bloodHouse.getSundayRestTime(),bloodHouse.getRestTime(), bloodHouse.getWholeBlood(), bloodHouse.getPlasma(), bloodHouse.getPlatelet()));

        }
        return bloodHouseResponse;
    }


    //헌혈의 집 예약 부분 구현
    @Transactional
    public String bloodHouseReservation(HttpSession session, BloodHouseReservationRequestDto bloodHouseReservationRequestDto) {
        String userLoginId = (String) session.getAttribute("user");
        User byUserID = userRepository.findByUserID(userLoginId);
        BloodHouseReservation bloodHouseReservation = new BloodHouseReservation();
        BloodHouse bloodHouse = bloodHouseRepository.findByCenterName(bloodHouseReservationRequestDto.getBloodHouseName()).get();
        bloodHouseReservation.setBloodHouse(bloodHouse);
        bloodHouseReservation.setReservationDate(bloodHouseReservationRequestDto.getDate());
        bloodHouseReservation.setTime(bloodHouseReservationRequestDto.getTime());
        bloodHouseReservation.setWholeBlood(bloodHouseReservationRequestDto.getWholeBlood());
        bloodHouseReservation.setPlasma(bloodHouseReservationRequestDto.getPlasma());
        bloodHouseReservation.setPlatelet(bloodHouseReservationRequestDto.getPlatelet());
        bloodHouseReservation.setUser(byUserID);
        BloodHouseReservation reservation = bloodHouseReservationRepository.save(bloodHouseReservation);
        return "예약완료";
    }


    public List<BloodHouseReservationResponse> reservationResponses() {
        List<BloodHouseReservation> bloodHouseReservations = bloodHouseReservationRepository.findAll();
        List<BloodHouseReservationResponse> bloodHouseReservationResponses = new ArrayList<>();
        for (BloodHouseReservation bloodHouseReservation : bloodHouseReservations) {
            BloodHouse bloodHouse = bloodHouseRepository.findByCenterName(bloodHouseReservation.getBloodHouse().getCenterName()).get();
            if(bloodHouse == null) {
                throw new IllegalArgumentException("BloodHouse 값이 null");
            }
            bloodHouseReservationResponses.add(new BloodHouseReservationResponse(bloodHouseReservation.getBloodHouse().getCenterName(), bloodHouseReservation.getBloodHouse().getDate(), bloodHouseReservation.getTime(), bloodHouseReservation.getWholeBlood(), bloodHouseReservation.getPlasma(), bloodHouseReservation.getPlatelet()));

        }
        return bloodHouseReservationResponses;
    }


    //헌혈 예약 filter 구현 부분
    public List<BloodHouseResponse>reservationFilter(String area, String centerName, String bloodHouseAddress, String bloodHousePhoneNumber, String wholeBlood, String plasma, String platelet) {
        List<BloodHouse> bloodHouseList = null;
        if(area != null) { //지역
            bloodHouseList = bloodHouseRepository.bloodHouseArea(area);
        } else if(bloodHouseAddress != null) { //주소
            bloodHouseList = bloodHouseRepository.bloodHouseAddress(bloodHouseAddress);
        } else if (wholeBlood != null) { //전혈
            bloodHouseList = bloodHouseRepository.filterWholeBlood(wholeBlood);
        } else if (plasma != null) { //혈장
            bloodHouseList = bloodHouseRepository.filterPlasma(plasma);
        } else if (platelet != null) { //혈소판
            bloodHouseList = bloodHouseRepository.filterPlatelet(platelet);
        } else if (centerName != null) { //센터명
            bloodHouseList = bloodHouseRepository.bloodHouseCenterName(centerName);
        } else if (bloodHousePhoneNumber != null) { //전화번호
            bloodHouseList = bloodHouseRepository.bloodHousePhoneNumber(bloodHousePhoneNumber);
        } else if (area != null && wholeBlood != null) { //지역 + 전혈
            bloodHouseList = bloodHouseRepository.areaAndWholeBlood(area, wholeBlood);
        } else if (area != null && plasma != null) { //지역 + 혈장
            bloodHouseList = bloodHouseRepository.areaAndPlasma(area, plasma);
        } else if (area != null && platelet != null) { //지역 + 혈소판
            bloodHouseList = bloodHouseRepository.areaAndPlatelet(area, platelet);
        } else if (area != null && bloodHouseAddress != null) { //지역 + 주소
            bloodHouseList = bloodHouseRepository.areaAndAddress(area,String.valueOf(bloodHouseAddress));
        } else if (area != null && centerName != null) { //지역 + 센터명
            bloodHouseList = bloodHouseRepository.areaAndCenterName(area, centerName);
        } else if (wholeBlood != null && plasma != null) { //전혈+혈장
            bloodHouseList = bloodHouseRepository.wholeBloodAndPlasma(wholeBlood,plasma);
        } else if (wholeBlood != null && platelet != null) {//전혈+혈소판
            bloodHouseList = bloodHouseRepository.wholeBloodAndPlatelet(wholeBlood, platelet);
        } else if (plasma != null && platelet != null) {//혈장+혈소판
            bloodHouseList = bloodHouseRepository.plasmaAndPlatelet(plasma, platelet);
        } else if (bloodHousePhoneNumber != null && area != null) {//전화번호+지역
            bloodHouseList = bloodHouseRepository.PhoneNumberAndArea(bloodHousePhoneNumber,area);
        } else if (bloodHousePhoneNumber != null && centerName != null) {//전화번호+센터명
            bloodHouseList = bloodHouseRepository.PhoneNumberAndCenterName(bloodHousePhoneNumber, centerName);
        } else if (bloodHousePhoneNumber != null && bloodHouseAddress != null) {//전화번호+주소
            bloodHouseList = bloodHouseRepository.PhoneNumberAndAddress(bloodHousePhoneNumber, bloodHouseAddress);
        } else if (wholeBlood != null && plasma != null && platelet != null) { //전혈+혈장+혈소판
            bloodHouseList = bloodHouseRepository.wholeBloodAndPlasmaAndPlatelet(wholeBlood, plasma, platelet);
        } else if (area != null && wholeBlood != null && bloodHouseAddress != null) {//지역+전혈+주소
            bloodHouseList = bloodHouseRepository.wholeBloodAreaAddress(area,wholeBlood,bloodHouseAddress);
        } else if (area != null && plasma != null && bloodHouseAddress != null) {//지역+혈장+주소
            bloodHouseList = bloodHouseRepository.plasmaAreaAddress(area,plasma,bloodHouseAddress);
        } else if (area != null && platelet != null && bloodHouseAddress != null) {//지역+혈소판+주소
            bloodHouseList = bloodHouseRepository.plateletAreaAddress(area,platelet,bloodHouseAddress);
        } else if (area != null && centerName != null && bloodHouseAddress != null) {//지역+센터명+주소
            bloodHouseList = bloodHouseRepository.areaCenterNameAddress(area,centerName,bloodHouseAddress);
        } else if (area != null || wholeBlood != null) {
            bloodHouseList = bloodHouseRepository.areaAndWholeBlood(area, wholeBlood);
        } else if (area != null || plasma != null) {
            bloodHouseList = bloodHouseRepository.areaAndPlasma(area, plasma);
        } else if (area != null && platelet != null) {
            bloodHouseList = bloodHouseRepository.areaAndPlatelet(area, platelet);
        } else {
            bloodHouseList = bloodHouseRepository.findAll();
        }
        List<BloodHouseResponse> bloodResultList = new ArrayList<>();
        for (BloodHouse post : bloodHouseList) {
            //DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWrite(post).orElse(null);
//            if(designatedBloodWriteUser == null) {
//                continue;
//            }
            BloodHouseResponse bloodHouseResponse = new BloodHouseResponse(post.getId(), post.getArea(), post.getCenterName(), post.getBloodHouseAddress(), post.getBloodHousePhoneNumber(),post.getLatitude(),post.getLongitude(),post.getWeekdayTime(),post.getSaturdayTime(),post.getSundayRestTime(), post.getRestTime(), post.getWholeBlood(), post.getPlasma(), post.getPlatelet());
            bloodResultList.add(bloodHouseResponse);
        }

        return bloodResultList;
    }


    //헌혈의 집 등록 api
    public String bloodHouseRegister(HttpSession session, BloodHouseRegisterRequestDto bloodHouseRegisterRequestDto) {

        //헌혈의집 관리자 로그인이 구현 되지 않아 임시 user 로그인으로 대체
        User user = (User)session.getAttribute("user");
        BloodHouse bloodHouse = new BloodHouse();
        bloodHouse.setArea(bloodHouseRegisterRequestDto.getArea());
        bloodHouse.setCenterName(bloodHouseRegisterRequestDto.getCenterName());
        bloodHouse.setBloodHouseAddress(bloodHouseRegisterRequestDto.getBloodHouseAddress());
        bloodHouse.setBloodHousePhoneNumber(bloodHouseRegisterRequestDto.getBloodHousePhoneNumber());
        bloodHouse.setLatitude(bloodHouseRegisterRequestDto.getLatitude());
        bloodHouse.setLongitude(bloodHouseRegisterRequestDto.getLongitude());
        bloodHouse.setWeekdayTime(bloodHouseRegisterRequestDto.getWeekdayTime());
        bloodHouse.setSaturdayTime(bloodHouseRegisterRequestDto.getSaturdayTime());
        bloodHouse.setRestTime(bloodHouseRegisterRequestDto.getRestTime());
        bloodHouse.setDate(bloodHouseRegisterRequestDto.getDate());
        bloodHouse.setWholeBlood(bloodHouseRegisterRequestDto.getWholeBlood());
        bloodHouse.setPlasma(bloodHouseRegisterRequestDto.getPlasma());
        bloodHouse.setPlatelet(bloodHouseRegisterRequestDto.getPlatelet());
        BloodHouse bloodHouse1 = bloodHouseRepository.save(bloodHouse);
        return "헌혈의 집 등록 완료";
    }


    //헌혈의 집 등록 list 출력 api
    @Transactional(readOnly = true)
    public List<BloodHouseRegisterResponse> registerResponseList() {
        List<BloodHouse> bloodHousesList = bloodHouseRepository.findAll();
        List<BloodHouseRegisterResponse> bloodHouseRegisterResponses = new ArrayList<>();
        for(BloodHouse bloodHouse : bloodHousesList) {
            bloodHouseRegisterResponses.add(new BloodHouseRegisterResponse(bloodHouse.getId(), bloodHouse.getArea(), bloodHouse.getCenterName(), bloodHouse.getBloodHouseAddress(), bloodHouse.getBloodHousePhoneNumber(), bloodHouse.getLatitude(),bloodHouse.getLongitude(),  bloodHouse.getWeekdayTime(), bloodHouse.getSaturdayTime(), bloodHouse.getSundayRestTime(), bloodHouse.getRestTime(), bloodHouse.getWholeBlood(), bloodHouse.getPlasma(), bloodHouse.getPlatelet()));
            }
        return bloodHouseRegisterResponses;
        }
}
