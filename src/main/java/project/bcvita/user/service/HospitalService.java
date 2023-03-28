package project.bcvita.user.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.user.dto.request.HospitalPasswordCheck;
import project.bcvita.user.dto.request.HospitalRequestDto;
import project.bcvita.user.dto.request.UserPasswordCheck;
import project.bcvita.user.dto.request.UserRequest;
import project.bcvita.user.dto.response.HospitalResponse;
import project.bcvita.user.dto.response.UserListResponse;
import project.bcvita.user.entity.Hospital;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.HospitalRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    //병원 회원가입
    @Transactional
    public String hospitalJoin(HospitalRequestDto hospitalRequestDto) {
        hospitalRepository.save(Hospital.builder()
                .hospitalId(hospitalRequestDto.getHospitalId())
                .hospitalPw(hospitalRequestDto.getHospitalPw())
                .hospitalAddress(hospitalRequestDto.getHospitalAddress())
                .hospitalName(hospitalRequestDto.getHospitalName())
                .hospitalPhoneNumber(hospitalRequestDto.getHospitalPhoneNumber())
                .build());
        return "Success";
    }

    //병원 회원가입 list api
    public List<HospitalResponse> hospitalResponseList() {
        List<Hospital> hospitalList = hospitalRepository.findAll();
        List<HospitalResponse> hospitalResponseList = new ArrayList<>();
        for (Hospital hospital : hospitalList) {
            hospitalResponseList.add(new HospitalResponse(hospital.getHospitalId(), hospital.getHospitalPw(), hospital.getHospitalName(), hospital.getHospitalPhoneNumber(), hospital.getHospitalAddress()));

        }
        return hospitalResponseList;
    }


    public String hospitalPasswordCheck(HospitalPasswordCheck hospitalPasswordCheck) {
        if (!hospitalPasswordCheck.getPassword().equals(hospitalPasswordCheck.getConfirmPassword())) {
            return "비밀번호가 일치하지 않습니다";
        }
        return "비밀번호가 일치합니다.";
    }
}
