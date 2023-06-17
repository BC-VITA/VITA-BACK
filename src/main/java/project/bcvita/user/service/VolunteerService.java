package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.heart.WishListRepository;
import project.bcvita.heart.entity.WishList;
import project.bcvita.user.dto.request.UserRequest;
import project.bcvita.user.dto.request.VolunteerJoinRequestDto;
import project.bcvita.user.dto.request.VolunteerRequestDto;
import project.bcvita.user.dto.request.VolunteerReservationRequestDto;
import project.bcvita.user.dto.response.VolunteerRegisterResponse;
import project.bcvita.user.dto.response.VolunteerReservationResponse;
import project.bcvita.user.dto.response.VolunteerReservationSaveResponseDto;
import project.bcvita.user.dto.response.VolunteerReservationUserInfoResponse;
import project.bcvita.user.entity.*;
import project.bcvita.user.repository.UserRepository;
import project.bcvita.user.repository.VolunteerRegisterRepository;
import project.bcvita.user.repository.VolunteerRepository;
import project.bcvita.user.repository.VolunteerReservationRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;
    private final VolunteerRegisterRepository volunteerRegisterRepository;
    private final UserRepository userRepository;

    private final VolunteerReservationRepository volunteerReservationRepository;
    private final UserService userService;
    private final WishListRepository wishListRepository;

    //봉사 기업-단체 회원가입 api
    @Transactional
    public String volunteerJoin(VolunteerJoinRequestDto request) {
        if(!request.getVolunteerConfirmPw().equals(request.getVolunteerPw())) {
            throw new IllegalArgumentException("비밀번호 일치하지 않습니다.");
        }
        volunteerRepository.save(Volunteer.builder()
                .volunteerId(request.getVolunteerId())
                .volunteerPw(request.getVolunteerPw())
                .volunteerGroupName(request.getVolunteerGroupName())
                //.volunteerField(request.getVolunteerField())
                .volunteerPhoneNumber(request.getVolunteerPhoneNumber())
                .build());
        return "Success";
    }

    //봉사 기업-단체 봉사 게시글 등록 api (개인-시간/활동)
    @Transactional
    public String volunteerCreate(HttpSession session, VolunteerRequestDto requestDto) {
        String volunteerLoginId = (String) session.getAttribute("loginId");
        //Volunteer volunteer = volunteerRepository.findByVolunteerId(volunteerLoginId);


        VolunteerRegister volunteerRegister = new VolunteerRegister();
         if (requestDto.getVolunteerType().equals("otherGroup")) {
            volunteerRegister.setVolunteerArea(requestDto.getVolunteerArea());
            volunteerRegister.setRequireGroup(requestDto.getRequireGroup());
            volunteerRegister.setVolunteerTarget(requestDto.getVolunteerTarget());
            volunteerRegister.setVolunteerStartDate(requestDto.getVolunteerStartDate());
            volunteerRegister.setVolunteerEndDate(requestDto.getVolunteerEndDate());
            volunteerRegister.setVolunteerStartTime(requestDto.getVolunteerStartTime());
            volunteerRegister.setVolunteerEndTime(requestDto.getVolunteerEndTime());
            volunteerRegister.setTitle(requestDto.getTitle());
            volunteerRegister.setContent(requestDto.getContent());
            volunteerRegister.setUrl(requestDto.getUrl());
        }else  {
            volunteerRegister.setVolunteerArea(requestDto.getVolunteerArea());
            volunteerRegister.setVolunteerPlace(requestDto.getVolunteerPlace());
            volunteerRegister.setVolunteerAddress(requestDto.getVolunteerAddress());
            volunteerRegister.setLatitude(requestDto.getLatitude());
            volunteerRegister.setLongitude(requestDto.getLongitude());
            volunteerRegister.setVolunteerSeekStartDate(requestDto.getVolunteerSeekStartDate());
            volunteerRegister.setVolunteerSeekEndDate(requestDto.getVolunteerSeekEndDate());
            volunteerRegister.setVolunteerStartDate(requestDto.getVolunteerStartDate());
            volunteerRegister.setVolunteerEndDate(requestDto.getVolunteerEndDate());
            volunteerRegister.setNeedVolunteerNumber(requestDto.getNeedVolunteerNumber());
            volunteerRegister.setVolunteerStartTime(requestDto.getVolunteerStartTime());
            volunteerRegister.setVolunteerEndTime(requestDto.getVolunteerEndTime());
            volunteerRegister.setVolunteerActivityWeek(requestDto.getVolunteerActivityWeek());
             volunteerRegister.setVolunteerType(requestDto.getVolunteerType());
            volunteerRegister.setVolunteerField(requestDto.getVolunteerField());
            volunteerRegister.setActivitySection(requestDto.getActivitySection());
            volunteerRegister.setVolunteerTarget(requestDto.getVolunteerTarget());
            volunteerRegister.setVolunteerPersonType(requestDto.getVolunteerPersonType());
            volunteerRegister.setManagerName(requestDto.getManagerName());
            volunteerRegister.setManagerEmail(requestDto.getManagerEmail());
            volunteerRegister.setTitle(requestDto.getTitle());
            volunteerRegister.setContent(requestDto.getContent());
           // volunteerRegister.setVolunteer(volunteer);
        }
        //volunteerRegister.setVolunteer(volunteer);
        volunteerRegisterRepository.save(volunteerRegister);
        return "게시글 작성 완료";
    }


    /*
     @Transactional
    public List<ReviewRegisterResponse> boardListResponseList(String reviewType) {
        List<ReviewRegister> reviewRegisters = reviewRegisterRepository.findAllByReviewType(reviewType);
        List<ReviewRegisterResponse> reviewRegisterResponses = new ArrayList<>();
        for(ReviewRegister reviewRegister : reviewRegisters) {
            reviewRegisterResponses.add(new ReviewRegisterResponse(reviewRegister.getReviewType(), reviewRegister.getImg(), reviewRegister.getContent(), reviewRegister.getTitle()));
        }
        return reviewRegisterResponses;
    }
     */

    @Transactional
    public List<VolunteerRegisterResponse> boardListResponseList(HttpSession session,String volunteerType) {

        String loginId = userService.loginId(session);
        User user = userRepository.findByUserID(loginId);

        List<VolunteerRegister>  volunteerRegisters = volunteerRegisterRepository.findAllByVolunteerType(volunteerType);
        List<VolunteerRegisterResponse> volunteerRegisterResponses = new ArrayList<>();
        for(VolunteerRegister volunteerRegister : volunteerRegisters) {
            boolean isWishList = false;
            if(user != null) {
                WishList wishList = wishListRepository.findByUserAndVolunteerRegister(user, volunteerRegister).orElse(null);
                if(wishList != null) {
                    isWishList = true;
                }
            }
            volunteerRegisterResponses.add(new VolunteerRegisterResponse(volunteerRegister.getVolunteerType(), volunteerRegister.getContent(), volunteerRegister.getTitle(),
                    volunteerRegister.getVolunteerStartDate(), volunteerRegister.getVolunteerEndDate(), volunteerRegister.getVolunteerStartTime(), volunteerRegister.getVolunteerEndTime(), volunteerRegister.getNeedVolunteerNumber(), volunteerRegister.getVolunteerArea(),
                    volunteerRegister.getActivitySection(), volunteerRegister.getVolunteerField(), volunteerRegister.getVolunteerSeekStartDate(), volunteerRegister.getVolunteerSeekEndDate(), volunteerRegister.getVolunteerAddress(),
                    volunteerRegister.getVolunteerTarget(), volunteerRegister.getVolunteerPlace(), volunteerRegister.getLatitude(), volunteerRegister.getLongitude(), volunteerRegister.getVolunteerActivityWeek(), volunteerRegister.getQualification(), volunteerRegister.getVolunteerPersonType(),
                    volunteerRegister.getVolunteerActivityNumber(), volunteerRegister.getRequirements(), volunteerRegister.getManagerName(), volunteerRegister.getManagerEmail(), volunteerRegister.getRequireGroup(), volunteerRegister.getUrl(),wishListRepository.countByVolunteerRegister(volunteerRegister),isWishList));
        }
        return volunteerRegisterResponses;
    }

    //봉사 예약
    @Transactional
    public VolunteerReservationSaveResponseDto volunteerReservation(VolunteerReservationRequestDto volunteerReservationRequestDto){
        User byUserID = userRepository.findByUserID(volunteerReservationRequestDto.getUserId());
        VolunteerRegister volunteerRegister = volunteerRegisterRepository.findById(volunteerReservationRequestDto.getVolunteerBoardId()).get();
        VolunteerReservation volunteerReservation = new VolunteerReservation();
        volunteerReservation.setVolunteerDate(volunteerReservationRequestDto.getVolunteerDate());
        volunteerReservation.setVolunteerType(volunteerReservationRequestDto.getVolunteerKind());
        volunteerReservation.setUser(byUserID);
        volunteerReservation.setInformationAgree(volunteerReservationRequestDto.isInformationAgree());
        volunteerReservation.setBoardStatus(volunteerReservationRequestDto.getVolunteerStatus());
        volunteerReservation.setVolunteerRegister(volunteerRegister);
        volunteerReservation.setBoardStatus("대기중");
        volunteerReservationRepository.save(volunteerReservation);
        return new VolunteerReservationSaveResponseDto(
                volunteerReservation.getBoardStatus(),volunteerReservation.getVolunteerDate(),
                volunteerRegister.getVolunteerStartTime(),volunteerRegister.getVolunteerEndTime(),
                volunteerRegister.getVolunteerAddress(),volunteerRegister.getVolunteerPlace(),
                volunteerRegister.getVolunteerType(),byUserID.getUserName(),byUserID.getUserPhoneNumber()

        );
    }

    // 봉사 신청할때 신청자 정보 뿌려주는 기능
    public VolunteerReservationUserInfoResponse volunteerReservationUserInfo(String userId) {
        User byUserID = userRepository.findByUserID(userId);
        return new VolunteerReservationUserInfoResponse(byUserID.getUserName(),byUserID.getUserPhoneNumber());
    }

    //봉사 예약 내역
    public List<VolunteerReservationResponse> reservationResponse() {
        List<VolunteerReservation> volunteerReservationList = volunteerReservationRepository.findAll();
        List<VolunteerReservationResponse> volunteerReservationResponses = new ArrayList<>();
        for(VolunteerReservation  volunteerReservation : volunteerReservationList) {
            if (volunteerReservation.getVolunteerRegister() == null) {
                continue;
            }
            volunteerReservationResponses.add(new VolunteerReservationResponse(volunteerReservation.getVolunteerRegister().getTitle(), volunteerReservation.getVolunteerDate(), volunteerReservation.getVolunteerRegister().getVolunteerStartTime(), volunteerReservation.getVolunteerRegister().getVolunteerEndTime()
            , volunteerReservation.getVolunteerRegister().getVolunteerAddress(), volunteerReservation.getVolunteerRegister().getVolunteerType(), volunteerReservation.getVolunteerRegister().getManagerName(), volunteerReservation.getVolunteerRegister().getManagerPhoneNumber(), volunteerReservation.getVolunteerRegister().getVolunteerPlace()));
        }
        return volunteerReservationResponses;
    }


}


