package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.user.dto.request.BloodHouseReservationRequestDto;
import project.bcvita.user.dto.response.BloodHouseReservationResponse;
import project.bcvita.user.dto.response.BloodHouseResponse;
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
            bloodHouseResponse.add(new BloodHouseResponse(bloodHouse.getId(), bloodHouse.getArea(), bloodHouse.getCenterName(), bloodHouse.getBloodHouseAddress(), bloodHouse.getBloodHousePhoneNumber(), bloodHouse.getLatitude(), bloodHouse.getLongitude(), bloodHouse.getWeekdayTime(), bloodHouse.getSaturdayTime(),bloodHouse.getSundayRestTime(),bloodHouse.getRestTime()));

        }
        return bloodHouseResponse;
    }


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



}
