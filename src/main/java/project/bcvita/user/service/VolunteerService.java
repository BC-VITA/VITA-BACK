package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.user.dto.request.UserRequest;
import project.bcvita.user.dto.request.VolunteerJoinRequestDto;
import project.bcvita.user.dto.request.VolunteerRequestDto;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.Volunteer;
import project.bcvita.user.entity.VolunteerRegister;
import project.bcvita.user.repository.VolunteerRegisterRepository;
import project.bcvita.user.repository.VolunteerRepository;

import javax.servlet.http.HttpSession;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;
    private final VolunteerRegisterRepository volunteerRegisterRepository;

    //봉사 기업-단체 회원가입 api
    @Transactional
    public String volunteerJoin(VolunteerJoinRequestDto request) {
        volunteerRepository.save(Volunteer.builder()
                .volunteerId(request.getVolunteerId())
                .volunteerPw(request.getVolunteerPw())
                .volunteerGroupName(request.getVolunteerGroupName())
                .volunteerField(request.getVolunteerField())
                .volunteerPhoneNumber(request.getVolunteerPhoneNumber())
                .build());
        return "Success";
    }

    //봉사 기업-단체 봉사 게시글 등록 api
    @Transactional
    public String volunteerCreate(HttpSession session, VolunteerRequestDto requestDto){
        Volunteer volunteer = (Volunteer) session.getAttribute("volunteer");

        if(requestDto.getVolunteerType().equals("time")){

        }

            /*
            if (wishListRequestDto.getBoardType().equals("user")) {
            DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWriteId(wishListRequestDto.getBoardId()).get();
            designatedBloodWriteUser.setUserNumber(user);
            designatedBloodWriteUser.setWishListCount(wishListRequestDto.getWishListCount() + 1);
            designatedBloodWriteUserRepository.save(designatedBloodWriteUser);
            return "찜하기 성공";
        }
             */

        VolunteerRegister volunteerRegister = new VolunteerRegister();
        volunteerRegister.setVolunteerStartDate(requestDto.getVolunteerStartDate());
        volunteerRegister.setVolunteerEndDate(requestDto.getVolunteerEndDate());
        volunteerRegister.setVolunteerSeekStartDate(requestDto.getVolunteerSeekStartDate());
        volunteerRegister.setVolunteerSeekEndDate(requestDto.getVolunteerSeekEndDate());
        volunteerRegister.setVolunteerArea(requestDto.getVolunteerArea());
        volunteerRegister.setVolunteerAddress(requestDto.getVolunteerAddress());
        volunteerRegister.setVolunteerStartTime(requestDto.getVolunteerStartTime());
        volunteerRegister.setVolunteerEndTime(requestDto.getVolunteerEndTime());
        volunteerRegister.setNeedVolunteerNumber(requestDto.getNeedVolunteerNumber());
        volunteerRegister.setVolunteerType(requestDto.getVolunteerType());
        volunteerRegister.setVolunteerField(requestDto.getVolunteerField());
        volunteerRegister.setActivitySection(requestDto.getActivitySection());
        volunteerRegister.setVolunteerTarget(requestDto.getVolunteerTarget());
        volunteerRegister.setTitle(requestDto.getTitle());
        volunteerRegister.setContent(requestDto.getContent());
        volunteerRegisterRepository.save(volunteerRegister);
        return "게시글 작성완료";
    }
}
