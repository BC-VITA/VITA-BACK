package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.bcvita.user.dto.request.AdminJoinAccpetRequest;
import project.bcvita.user.dto.request.WarmCaseRegisterRequestDto;
import project.bcvita.user.dto.response.AdminVolunteerFieldStatisticsResponse;
import project.bcvita.user.dto.response.AdminVolunteerStatisticsResponse;
import project.bcvita.user.dto.response.AdminWarmCaseResponse;
import project.bcvita.user.entity.*;
import project.bcvita.user.repository.*;

import java.io.File;
import java.util.*;

@RequiredArgsConstructor
@Service

public class AdminService {

    private final UserRepository userRepository;

    private final WarmCaseRegisterRepository warmCaseRegisterRepository;

    private final HospitalRepository hospitalRepository;

    private final VolunteerRegisterRepository volunteerRegisterRepository;

    private final VolunteerReservationRepository volunteerReservationRepository;


    //따뜻한 사례 글 등록 api구현
    @Transactional
    public String warmCaseRegister(WarmCaseRegisterRequestDto warmCaseRegisterRequestDto, MultipartFile file) {
        try {
            String path = "/Users/minji/GitHub/vita";
            File destination = new File(path + File.separator + file.getOriginalFilename());
            file.transferTo(destination);
            User user = userRepository.findByUserID(warmCaseRegisterRequestDto.getUserId());
            if (user.isAdmin() == false) {
                return "관리자가 아니므로 저장 안됨";
            }
            WarmCaseRegister warmCaseRegister = new WarmCaseRegister();
            warmCaseRegister.create(warmCaseRegisterRequestDto.getTitle(), warmCaseRegisterRequestDto.getContent(), destination.getAbsolutePath());
            warmCaseRegisterRepository.save(warmCaseRegister);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "따뜻한 게시글 작성완료";
    }

    //관리자 회원가입 승인 수락/거절 api
    @Transactional
    public String adminJoinAcceptOrCancel(AdminJoinAccpetRequest adminJoinAccpetRequest) {
        Long hospitalId = adminJoinAccpetRequest.getHospitalId();
        Hospital hospital = hospitalRepository.findById(hospitalId).get();
        hospital.updateIsAccept(adminJoinAccpetRequest.getIsAdminJoinAccept());
        return hospital.getIsAdminJoinAccept() == true ? "수락됨" : "취소됨";
    }


    //관리자 따뜻한 사례 글 list api 구현
    @Transactional
    public List<AdminWarmCaseResponse> adminWarmCaseResponses() {
        List<WarmCaseRegister> warmCaseRegister1 = warmCaseRegisterRepository.findAll();
        List<AdminWarmCaseResponse> warmCaseResponses = new ArrayList<>();
        for (WarmCaseRegister warmCaseRegister : warmCaseRegister1) {
            warmCaseResponses.add(new AdminWarmCaseResponse(warmCaseRegister.getTitle(), warmCaseRegister.getContent(), warmCaseRegister.getImageUrl()));
        }
        return warmCaseResponses;
    }


    //관리자 월별 봉사 통계 api
    @Transactional
    public List<AdminVolunteerStatisticsResponse> adminVolunteerStatisticsResponses(int year) {
        List<AdminVolunteerStatisticsResponse> statisticsResponses = new ArrayList<>();

        List<DateStatistics> statisticsData = volunteerReservationRepository.getMonthlyVolunteerStatistics(year);
        for (DateStatistics statisticsDatum : statisticsData) {
            AdminVolunteerStatisticsResponse adminVolunteerStatisticsResponse = new AdminVolunteerStatisticsResponse(statisticsDatum.getYear(), statisticsDatum.getMonth(), statisticsDatum.getCount());
            statisticsResponses.add(adminVolunteerStatisticsResponse);
        }
        return statisticsResponses;
    }


    //관리자 분야별 봉사 통계 api
    @Transactional
    public List<AdminVolunteerFieldStatisticsResponse> adminVolunteerFieldStatisticsResponses(int year) {
        List<AdminVolunteerFieldStatisticsResponse> statisticsResponseList = new ArrayList<>();

        List<DateStatistics> statistics = volunteerReservationRepository.getMonthlyVolunteerStatisticsV2(year);
        for (DateStatistics statisticsDatum : statistics) {
            AdminVolunteerFieldStatisticsResponse adminVolunteerStatisticsResponse = new AdminVolunteerFieldStatisticsResponse(statisticsDatum.getYear(),  statisticsDatum.getCount(), statisticsDatum.getVolunteerField());
            statisticsResponseList.add(adminVolunteerStatisticsResponse);
        }
        return statisticsResponseList;
    }
}




