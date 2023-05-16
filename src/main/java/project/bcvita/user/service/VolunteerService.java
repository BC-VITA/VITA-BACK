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

    //봉사 기업-단체 봉사 게시글 등록 api (개인-시간/활동)
    /*@Transactional
    public String volunteerCreate(HttpSession session, VolunteerRequestDto requestDto){
        //Volunteer volunteer = (Volunteer) session.getAttribute("volunteer");
        Volunteer volunteer = volunteerRegisterRepository.findByUserID(requestDto.getVolunteer().getVolunteerId());

        if(requestDto.getVolunteerType().equals("time")){
            VolunteerRegister volunteerRegister = new VolunteerRegister();
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
            volunteerRegister.setVolunteer(volunteer);
            volunteerRegisterRepository.save(volunteerRegister);
            return "게시글 작성완료";
        } else if (requestDto.getVolunteerType().equals("Activity")) {
            
        }

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

     */
}
