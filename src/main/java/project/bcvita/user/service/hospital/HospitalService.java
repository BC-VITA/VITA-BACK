package project.bcvita.user.service.hospital;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.user.dto.request.hospital.HospitalLoginRequestDto;
import project.bcvita.user.dto.request.hospital.HospitalPasswordCheck;
import project.bcvita.user.dto.request.hospital.HospitalRequestDto;
import project.bcvita.user.dto.response.hospital.HospitalResponse;
import project.bcvita.user.entity.hospital.Hospital;
import project.bcvita.user.repository.hospital.HospitalRepository;

import javax.servlet.http.HttpSession;
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

    //병원 회원가입 list api-> 관리자 병원 회원가입 승인 api
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


    public String hospitalLogin(HospitalLoginRequestDto hospitalLoginRequestDto, HttpSession session) {
        Hospital hospital = hospitalRepository.findByHospitalIdAndHospitalPw(hospitalLoginRequestDto.getHospitalId(), hospitalLoginRequestDto.getHospitalPw());
        if (hospital == null) {
            throw new IllegalArgumentException("회원가입을 진행해주세요."); // 예외처리
        } else {
            session.setAttribute("hospital", hospital.getHospitalId());
        }
        System.out.println("hospital = " + hospital.getHospitalPw());
        return "로그인 성공";
    }

    public String hospitalLogout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "로그아웃 성공";
    }


}
