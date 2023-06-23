package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;
import project.bcvita.user.dto.request.*;
import project.bcvita.user.dto.response.*;
import project.bcvita.user.entity.*;
import project.bcvita.user.repository.*;

import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    private final DesignatedBloodWriteUserRepository designatedBloodWriteUserRepository;

    private final DesignatedBloodWriteRepository designatedBloodWriteRepository;

    private final BloodHouseReservationRepository bloodHouseReservationRepository;

    private final ReviewRegisterRepository reviewRegisterRepository;
    private final VolunteerRepository volunteerRepository;

    private final VolunteerReservationRepository volunteerReservationRepository;

    @Transactional
    public String join(UserRequest request) {
        userRepository.save(User.builder()
                .userID(request.getUserID())
                .userPW(request.getPassword())
                .userName(request.getUserName())
                .userEmail(request.getUserEmail())
                .userBirth(request.getUserBirth())
                .userBlood(request.getUserBlood())
                //.userPoint(request.getUserPoint())
                .sex(request.getSex())
                .isRH(String.valueOf(request.getIsRH()))
                .bloodHistory(String.valueOf(request.getBloodHistory()))
                .userPhoneNumber(request.getUserPhoneNumber())
                .build());
        return "Success";
    }


    public List<UserListResponse> userList() {
        List<User> userList = userRepository.findAll();
        List<UserListResponse> userListResponse = new ArrayList<>();
        for (User user : userList) {
            userListResponse.add(new UserListResponse(user.getUserNumber(), user.getUserID(), user.getUserName()));

        }
        return userListResponse;
    }


    public String passwordCheck(UserPasswordCheck userPasswordCheck) {
        if (!userPasswordCheck.getPassword().equals(userPasswordCheck.getConfirmPassword())) {
            return "비밀번호가 일치하지 않습니다";
        }
        return "비밀번호가 일치합니다.";
    }


    public String login(UserLoginRequestDto userLoginRequestDto, HttpSession session) {
            User user = userRepository.findByUserIDAndUserPW(userLoginRequestDto.getUserId(), userLoginRequestDto.getUserPw());
            if (user == null) {
                throw new IllegalArgumentException("회원가입을 진행해주세요."); // 예외처리
            }
        /*else if(userLoginRequestDto.getType().equals("volunteer")) {
            Volunteer volunteer= volunteerRepository.findByVolunteerIdAndVolunteerPw(userLoginRequestDto.getUserId(), userLoginRequestDto.getUserPw());
            loginId = volunteer.getVolunteerId();
        }
        //나중에 기업도 추가하셈
        if(loginId.equals("")) {
            throw new IllegalArgumentException("로그인 안됨");
        }*/
        session.setAttribute("loginId", user.getUserID());
        return "로그인 성공";
    }

    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "로그아웃 성공";
    }



    /*public UserInfo userInfo(HttpSession session)  {
        System.out.println("httpSession.getId() = " + session.getId());
        String userId = (String)session.getAttribute("loginId");
        System.out.println("userId = " + userId);
            if (userId == null) {
                throw new IllegalArgumentException("로그인한 사용자가 없음");
            }
        User user = userRepository.findByUserID(userId);
        return new UserInfo(user.getUserID(),user.getUserName());
    }*/

    //***마이페이지 api 하나로 합친것***
    public MyPageResponse myPage(HttpSession session,String reviewType) {
        String loginId = (String) session.getAttribute("loginId");
        if (loginId == null) {
           throw new IllegalArgumentException("로그인안됨");
        }
        User user = userRepository.findByUserID(loginId);
        if(reviewType == null) {
            reviewType = "designatedBlood";
        }

        MyPageUserInfoResponse myPageUserInfoResponse = new MyPageUserInfoResponse(user.getUserID(), user.getUserName(), user.getUserPhoneNumber(), user.getUserEmail(),
                user.getUserBirth(), user.getUserBlood(), user.getSex(), user.getIsRH(), user.getBloodHistory(), user.getUserPoint());

        List<BloodHouseReservation> reservations = bloodHouseReservationRepository.findAllByUser(user);
        List<MyPageBloodReservationHistoryResponse> myPageBloodReservationHistoryList = new ArrayList<>();
        for (BloodHouseReservation bloodHouseReservation : reservations) {
            myPageBloodReservationHistoryList.add(new MyPageBloodReservationHistoryResponse(user.getUserName(), user.getBloodHistory(), bloodHouseReservation.getIsBloodType(), bloodHouseReservation.getBloodHouse().getCenterName(), bloodHouseReservation.getDate()));
        }

        List<ReviewRegister> reviewRegisters = reviewRegisterRepository.findAllByUserAndReviewType(user,reviewType);
        List<MyPageDesignatedBloodReviewResponse> myPageDesignatedBloodReviewList = new ArrayList<>();
        for (ReviewRegister reviewRegister1 : reviewRegisters) {
            myPageDesignatedBloodReviewList.add(new MyPageDesignatedBloodReviewResponse(user.getUserName(), user.getDesignatedNumber(), reviewRegister1.getTitle(), reviewRegister1.getLocalDateTime()));
        }

        List<DesignatedBloodWriteUser> designatedBloodWriteUsers = designatedBloodWriteUserRepository.findAllByUserNumber(user);
        List<MyPageDesignatedBloodBoardResponse> myPageDesignatedBloodBoardList = new ArrayList<>();
        for (DesignatedBloodWriteUser designatedBloodWriteUser : designatedBloodWriteUsers) {
            myPageDesignatedBloodBoardList.add(new MyPageDesignatedBloodBoardResponse(user.getUserName(), user.getDesignatedNumber(), designatedBloodWriteUser.getDesignatedBloodWrite().getTitle(),
                    designatedBloodWriteUser.getDesignatedBloodWrite().getLocalDateTime()));
        }
        MyPageResponse myPageResponse = new MyPageResponse();
        myPageResponse.setMyPageUserInfo(myPageUserInfoResponse);
        myPageResponse.setMyPageDesignatedBloodBoardList(myPageDesignatedBloodBoardList);
        myPageResponse.setMyPageBloodReservationHistoryList(myPageBloodReservationHistoryList);
        myPageResponse.setMyPageDesignatedBloodReviewList(myPageDesignatedBloodReviewList);

        return myPageResponse;
    }


    @Transactional
    public MyPageUserInfoResponse updateMyPage(HttpSession session, MyPageRequest request) {
        /*String loginId = (String) session.getAttribute("loginId");

        if(loginId == null) {
            return null;
        }*/
        User user = userRepository.findByUserID(request.getUserId());
        String password = user.getUserPW();
        if ((request.getPassword() != null && request.getConfirmPassword() == null) || (request.getPassword() == null && request.getConfirmPassword() != null)) {
            throw new RuntimeException("비밀번호와 비밀번호 재확인이 일치하지 않습니다.");
        }
        if (request.getPassword() != null && request.getConfirmPassword() != null) {
            if (!request.getPassword().equals(request.getConfirmPassword())) {
                throw new RuntimeException("비밀번호와 비밀번호 재확인이 일치하지 않습니다.");
            } else {
                password = request.getPassword();
            }
        }

        user.setUserID(request.getUserId());
        user.setUserName(request.getUserName());
        user.setUserBirth(request.getUserBirth());
        user.setUserPW(password);
        user.setUserEmail(request.getUserEmail());
        user.setUserBlood(request.getUserBlood());
        user.setSex(request.getSex());
        user.setIsRH(request.getIsRH());
        user.setBloodHistory(request.getBloodHistory());
        user.setUserPhoneNumber(request.getUserPhoneNumber());
        return new MyPageUserInfoResponse(user.getUserID(), user.getUserName(), user.getUserPhoneNumber(), user.getUserEmail(),
                user.getUserBirth(), user.getUserBlood(), user.getSex(), user.getIsRH(), user.getBloodHistory(), user.getUserPoint());

    }

    public String loginId(HttpSession httpSession) {
        return (String) httpSession.getAttribute("loginId");
    }


    //마이페이지 지정헌혈 작성한 게시물 api
    public List<MyPageDesignatedBloodBoardResponse> myPage(HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");
        User user = userRepository.findByUserID(loginId);
        List<DesignatedBloodWriteUser> designatedBloodWriteUsers = designatedBloodWriteUserRepository.findAllByUserNumber(user);
        // System.out.println("designatedBloodWrite = " + designatedBloodWrite.getLocalDateTime());
        List<MyPageDesignatedBloodBoardResponse> list = new ArrayList<>();
        for (DesignatedBloodWriteUser designatedBloodWriteUser : designatedBloodWriteUsers) {
            list.add(new MyPageDesignatedBloodBoardResponse(user.getUserName(), user.getDesignatedNumber(), designatedBloodWriteUser.getDesignatedBloodWrite().getTitle(),
                    designatedBloodWriteUser.getDesignatedBloodWrite().getLocalDateTime()));
        }
        return list;
    }

    //마이페이지 지정헌혈 작성한 게시물 수정 api
    @Transactional
    public MyPageDesignatedBloodHistoryResponse updateDesignatedBoardMyPage(HttpSession session, BoardCreateRequestDto requestDto) {
        String loginId = (String) session.getAttribute("loginId");
        User user = userRepository.findByUserID(loginId);
        //List<DesignatedBloodWriteUser> designatedBloodWriteUser = designatedBloodWriteUserRepository.findAllByUserNumber(user);
        DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWriteId(requestDto.getUserId()).get();
        DesignatedBloodWrite designatedBloodWrite = designatedBloodWriteRepository.findDesignatedBloodWriteById(requestDto.getUserId()).get();
        designatedBloodWriteUser.setId(requestDto.getUserId());
        designatedBloodWriteUser.setPatientName(requestDto.getPatientName());
        designatedBloodWriteUser.setBloodPersonNumber(requestDto.getBloodPersonNumber());
        designatedBloodWriteUser.setBloodNumber(requestDto.getBloodNumber());
        designatedBloodWriteUser.setPatientAge(requestDto.getPatientAge());
        designatedBloodWriteUser.setHospitalRoomNumber(requestDto.getHospitalRoomNumber());
        //designatedBloodWriteUser.setDesignatedBloodWrite(requestDto.getHospitalName());
        designatedBloodWrite.setHospitalName(requestDto.getHospitalName());
        designatedBloodWrite.setHospitalPhoneNumber(requestDto.getHospitalPhoneNumber());
        designatedBloodWrite.setRequestHospitalAddress(requestDto.getRequestHospitalAddress());
        //designatedBloodWriteUser.getUserNumber(requestDto.getUserId());
        //designatedBloodWriteUser.setUserNumber(user.getUserPhoneNumber());
        designatedBloodWrite.setPatientBlood(requestDto.getPatientBlood());
        designatedBloodWrite.setPatientIsRH(requestDto.getPatientIsRH());
        designatedBloodWrite.setBloodType(requestDto.getBloodType());
        designatedBloodWrite.setNeedBloodSystem(requestDto.getNeedBloodSystem());
        designatedBloodWriteUser.setBloodMatchType(requestDto.isBloodMatchType());
        designatedBloodWriteUser.setReport(requestDto.isReview());
        designatedBloodWrite.setStartDate(requestDto.getStartDate());
        designatedBloodWrite.setEndDate(requestDto.getEndDate());
        designatedBloodWrite.setTitle(requestDto.getTitle());
        designatedBloodWrite.setContent(requestDto.getContent());

        return new MyPageDesignatedBloodHistoryResponse(user.getUserID(), user.getUserName(), designatedBloodWriteUser.getPatientName(), designatedBloodWriteUser.getBloodPersonNumber(),
                designatedBloodWriteUser.getBloodNumber(), designatedBloodWriteUser.getPatientAge(), designatedBloodWriteUser.getHospitalRoomNumber(), designatedBloodWrite.getHospitalName(), designatedBloodWrite.getHospitalPhoneNumber(),
                designatedBloodWrite.getRequestHospitalAddress(), user.getUserPhoneNumber(), designatedBloodWrite.getPatientBlood(), designatedBloodWrite.getPatientIsRH(), designatedBloodWrite.getBloodType(),
                designatedBloodWrite.getNeedBloodSystem(), designatedBloodWriteUser.isBloodMatchType(), designatedBloodWriteUser.isReview(), designatedBloodWrite.getStartDate(), designatedBloodWrite.getEndDate(),
                designatedBloodWrite.getTitle(), designatedBloodWrite.getContent(), designatedBloodWrite.getLocalDateTime());
    }


    //마이페이지 지정헌혈 작성한 게시물 삭제 api
    @Transactional
    public String deleteMypageDesignatedBoard(HttpSession session, Long designatedId, Long designatedUserId) {
        String loginId = (String) session.getAttribute("loginId");
        User user = userRepository.findByUserID(loginId);

        Optional<DesignatedBloodWriteUser> designatedBloodWriteUserOptional = designatedBloodWriteUserRepository.findById(designatedUserId);
        Optional<DesignatedBloodWrite> designatedBloodWriteOptional = designatedBloodWriteRepository.findById(designatedId);

        //해당 객체 존재하는지 !
        if (designatedBloodWriteUserOptional.isPresent() && designatedBloodWriteOptional.isPresent()) {
            DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserOptional.get();
            DesignatedBloodWrite designatedBloodWrite = designatedBloodWriteOptional.get();
            designatedBloodWriteUserRepository.delete(designatedBloodWriteUser);
            designatedBloodWriteRepository.delete(designatedBloodWrite);
        } else {
            throw new RuntimeException("게시물을 찾을 수 없습니다.");
        }
        return "삭제완료";
    }

    //마이페이지 내가 작성한 후기 api
    public List<MyPageDesignatedBloodReviewResponse> myPageBloodReviewResponses(HttpSession session, String reviewType) {
        String loginId = (String) session.getAttribute("loginId");
        User user = userRepository.findByUserID(loginId);
        List<ReviewRegister> reviewRegisters = reviewRegisterRepository.findAllByUserAndReviewType(user,reviewType);
        List<MyPageDesignatedBloodReviewResponse> list = new ArrayList<>();
        for (ReviewRegister reviewRegister1 : reviewRegisters) {
                    list.add(new MyPageDesignatedBloodReviewResponse(user.getUserName(), user.getDesignatedNumber(), reviewRegister1.getTitle(), reviewRegister1.getLocalDateTime()));
        }
        return list;
    }



    //마이페이지 헌혈 예약 내역 api
    @Transactional
    public List<MyPageBloodReservationHistoryResponse> mypageBloodReservationHistory(HttpSession session){
        String loginId = (String)session.getAttribute("loginId");
        User user = userRepository.findByUserID(loginId);
        //BloodHouseReservation reservation = bloodHouseReservationRepository.findById(request.getBloodReservationId()).get();
        List<BloodHouseReservation> reservations = bloodHouseReservationRepository.findAllByUser(user);
        List<MyPageBloodReservationHistoryResponse> list = new ArrayList<>();
        for (BloodHouseReservation bloodHouseReservation : reservations) {
            list.add(new MyPageBloodReservationHistoryResponse(user.getUserName(), user.getBloodHistory(), bloodHouseReservation.getIsBloodType(), bloodHouseReservation.getBloodHouse().getCenterName(), bloodHouseReservation.getDate()));
        }
        return list;
    }


    //마이페이지 헌혈 예약 내역 취소 api
    @Transactional
    public String CancelMyPageBloodReservation(Long reservationId){
        Optional<BloodHouseReservation> bloodHouseReservationOptional = bloodHouseReservationRepository.findById(reservationId);
        if (bloodHouseReservationOptional.isPresent()) {
            BloodHouseReservation bloodHouseReservation = bloodHouseReservationOptional.get();
            bloodHouseReservationRepository.delete(bloodHouseReservation);
        }else {
            throw new RuntimeException("게시물을 찾을 수 없습니다.");
        }
        return "취소완료";
    }


    //마이페이지 봉사 내역 api
    @Transactional
    public MyPageVolunteerInfo myPageVolunteerReservationResponses(String userId) {
        User user = userRepository.findByUserID(userId);
        List<VolunteerReservation> reservations = volunteerReservationRepository.findAllByUser(user);
        List<MyPageVolunteerReservationResponse> list = new ArrayList<>();
        int totalVolunteerTime = 0;
        for(VolunteerReservation volunteerReservation : reservations) {
            String startTime[] = volunteerReservation.getVolunteerRegister().getVolunteerStartTime().split(":");
            String endTime[] = volunteerReservation.getVolunteerRegister().getVolunteerEndTime().split(":");
             totalVolunteerTime += (Integer.parseInt(endTime[0]) - Integer.parseInt(startTime[0]));
             int volunteerTime = (Integer.parseInt(endTime[0]) - Integer.parseInt(startTime[0]));
            list.add(new MyPageVolunteerReservationResponse(user.getUserName(),  volunteerReservation.getVolunteerRegister().getVolunteer().getVolunteerGroupName()
                    ,volunteerTime
                  ,volunteerReservation.getVolunteerRegister().getTitle(),
                    volunteerReservation.getVolunteerRegister().getVolunteerType(),volunteerReservation.getVolunteerRegister().getLocalDateTime()));
        }

        return new MyPageVolunteerInfo(totalVolunteerTime, list);
    }


    /*
    //마이페이지 헌혈 예약 내역 api
    @Transactional
    public List<MyPageBloodReservationHistoryResponse> mypageBloodReservationHistory(HttpSession session){
        String loginId = (String)session.getAttribute("loginId");
        User user = userRepository.findByUserID(loginId);
        //BloodHouseReservation reservation = bloodHouseReservationRepository.findById(request.getBloodReservationId()).get();
        List<BloodHouseReservation> reservations = bloodHouseReservationRepository.findAllByUser(user);
        List<MyPageBloodReservationHistoryResponse> list = new ArrayList<>();
        for (BloodHouseReservation bloodHouseReservation : reservations) {
            list.add(new MyPageBloodReservationHistoryResponse(user.getUserName(), user.getBloodHistory(), bloodHouseReservation.getIsBloodType(), bloodHouseReservation.getBloodHouse().getCenterName(), bloodHouseReservation.getDate()));
        }
        return list;
    }
     */

    public List<VolunteerActiveHistoryResponse> volunteerActiveHistory(String userId) {
        User user = userRepository.findByUserID(userId);
        List<VolunteerReservation> resultList = volunteerReservationRepository.findAllByUserAndBoardStatus(
            user, "참여완료");
        return resultList.stream()
            .map(x -> new VolunteerActiveHistoryResponse(x.getId(),x.getVolunteerRegister().getTitle(),x.getVolunteerRegister().getLocalDateTime()))
            .toList();
    }
}
