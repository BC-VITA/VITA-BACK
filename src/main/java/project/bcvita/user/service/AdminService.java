package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.bcvita.chat.dto.ChatApproveOrCancelRequest;
import project.bcvita.chat.entity.ChatRoom;
import project.bcvita.user.dto.request.AdminJoinAccpetRequest;
import project.bcvita.user.dto.request.AdminVolunteerStatisticsRequest;
import project.bcvita.user.dto.request.WarmCaseRegisterRequestDto;
import project.bcvita.user.dto.response.AdminVolunteerStatisticsInterface;
import project.bcvita.user.dto.response.AdminVolunteerStatisticsResponse;
import project.bcvita.user.entity.*;
import project.bcvita.user.repository.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
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
    public String warmCaseRegister(HttpSession session, WarmCaseRegisterRequestDto warmCaseRegisterRequestDto, MultipartFile file) {
        try {
            String path = "C:\\vita";
            File destination = new File(path + File.separator + file.getOriginalFilename());
            file.transferTo(destination);
            String loginId = (String)session.getAttribute("loginId");
            User user = userRepository.findByUserID(loginId);
            if (user.isAdmin() == false) {
                return "관리자가 아니므로 저장 안됨";
            }
            WarmCaseRegister warmCaseRegister = new WarmCaseRegister();
            warmCaseRegister.create(warmCaseRegisterRequestDto.getTitle(), warmCaseRegisterRequestDto.getContent(), destination.getAbsolutePath());
            warmCaseRegisterRepository.save(warmCaseRegister);
        }catch (Exception e){
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


    //관리자 봉사 통계 api
//    @Transactional
//    public List<AdminVolunteerStatisticsResponse> adminVolunteerStatisticsResponses(AdminVolunteerStatisticsRequest adminVolunteerStatisticsRequest) {
//
//        List<VolunteerReservation> volunteerReservations = volunteerReservationRepository.findAll();
//        List<VolunteerRegister> volunteerRegisters = volunteerRegisterRepository.findAll();
//        List<AdminVolunteerStatisticsInterface> volunteerStatistics = volunteerReservationRepository.getMonthlyVolunteerStatistics();
//        List<VolunteerReservation> volunteerStatisticsInterfaces = volunteerReservationRepository.findByBoardStatusOrderByVolunteerDateAsc(adminVolunteerStatisticsRequest.getBoardStatus());
//        List<AdminVolunteerStatisticsResponse> adminVolunteerStatisticsResponses = new ArrayList<>();
//
//        for (VolunteerReservation volunteerReservation : volunteerReservations) {
//            adminVolunteerStatisticsResponses.add(new AdminVolunteerStatisticsResponse(
//
//        }
//
//    }


    /*
    //관리자 기부 게시물 통계
    public List<DonateBoardStatisticsResponse> adminDonateStatistics(){
        List<DonateBoard> donateBoardList = donateBoardRepository.findAllBy();
        List<DonateBoardInterface> pointHistory = donateBoardRepository.findByPointHistory();
        List<DonateBoardStatisticsResponse> boardStatisticsResponseList = new ArrayList<>();
        for (DonateBoard donateBoard : donateBoardList) {
            boardStatisticsResponseList.add(new DonateBoardStatisticsResponse(
                donateBoard.getId(), donateBoard.getTitle(), donateBoard.getContent(), donateBoard.getLocalDateTime(), donateBoard.getPointHistory(), getSumByTitle(pointHistory, donateBoard.getTitle()
            )));
        }
        return boardStatisticsResponseList;
    }


    private DonateBoardInterface getSumByTitle(List<DonateBoardInterface> pointHistoryList, String title) {
        for (DonateBoardInterface pointHistory : pointHistoryList) {
            if (pointHistory.getTitle().equals(title)) {
                return pointHistory;
            }
        }
        return null; // 해당 제목을 찾지 못한 경우 null 반환
    }


    //지정헌혈 수락하기 버튼 누르면 나오는 팝업창 api
    @Transactional
    public DesignateBloodAcceptWindowResponse designatedBloodAcceptWindow(DesignateBloodAcceptWindowRequest designateBloodAcceptWindowRequest) {
        DesignatedBloodWriteUser designatedBloodWriteUsers = designatedBloodWriteUserRepository.findById(designateBloodAcceptWindowRequest.getDesignateBloodWriteUserId()).get();
        User userNumber = designatedBloodWriteUsers.getUserNumber();
        DesignatedBloodWrite designatedBloodWrite = designatedBloodWriteUsers.getDesignatedBloodWrite();

        return new DesignateBloodAcceptWindowResponse(designatedBloodWrite.getHospitalName(), designatedBloodWrite.getHospitalPhoneNumber(), designatedBloodWrite.getRequestHospitalAddress(), designatedBloodWrite.getPatientBlood(),
                designatedBloodWrite.getPatientIsRH(), designatedBloodWrite.getBloodType(), designatedBloodWrite.getNeedBloodSystem(), designatedBloodWriteUsers.getPatientName(), designatedBloodWriteUsers.getBloodPersonNumber(),
                designatedBloodWriteUsers.getPatientAge(), userNumber.getUserName());

    }
     */
}
