package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.user.dto.response.BloodHouseResponse;
import project.bcvita.user.dto.response.BoardListResponse;
import project.bcvita.user.dto.response.UserListResponse;
import project.bcvita.user.entity.BloodHouse;
import project.bcvita.user.entity.DesignatedBloodWrite;
import project.bcvita.user.entity.DesignatedBloodWriteUser;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.BloodHouseRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BloodHouseService {
    private final BloodHouseRepository bloodHouseRepository;


//    @Transactional(readOnly = true)
//    public List<BloodHouseResponse> bloodHouseResponseList() {
//        List<BloodHouse> bloodHouses = bloodHouseRepository.findAll();
//        List<BloodHouseResponse> bloodHouseResponse = new ArrayList<>();
//        for (BloodHouse bloodHouse : bloodHouses) {
////            BloodHouse bloodHouse1 = bloodHouseRepository.findBloodHouseById(bloodHouse.getId()).orElse(null);
////            if(bloodHouse1 == null) {
////                throw new IllegalArgumentException("bloodHouse1 값이 null");
////            }
//            bloodHouseResponse.add(new BloodHouseResponse(bloodHouse.getId(), bloodHouse.getArea(), bloodHouse.getCenterName(), bloodHouse.getBloodHouseAddress(), bloodHouse.getBloodHousePhoneNumber(), bloodHouse.getLatitude(), bloodHouse.getLongitude(), bloodHouse.getWeekdayTime(), bloodHouse.getSaturdayTime(),bloodHouse.getSundayRestTime(),bloodHouse.getRestTime()));
//        }
//        return bloodHouseResponse;
//    }

    public List<BloodHouseResponse> bloodHouseResponseList() {
        List<BloodHouse> bloodHouses = bloodHouseRepository.findAll();
        List<BloodHouseResponse>  bloodHouseResponse= new ArrayList<>();
        for (BloodHouse bloodHouse : bloodHouses) {
            bloodHouseResponse.add(new BloodHouseResponse(bloodHouse.getId(), bloodHouse.getArea(), bloodHouse.getCenterName(), bloodHouse.getBloodHouseAddress(), bloodHouse.getBloodHousePhoneNumber(), bloodHouse.getLatitude(), bloodHouse.getLongitude(), bloodHouse.getWeekdayTime(), bloodHouse.getSaturdayTime(),bloodHouse.getSundayRestTime(),bloodHouse.getRestTime()));

        }
        return bloodHouseResponse;
    }


}
