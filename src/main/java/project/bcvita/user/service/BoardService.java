package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.user.dto.request.BoardCreateRequestDto;
import project.bcvita.user.dto.response.BoardListResponse;
import project.bcvita.user.entity.DesignatedBloodWrite;
import project.bcvita.user.entity.DesignatedBloodWriteUser;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.DesignatedBloodWriteRepository;
import project.bcvita.user.repository.DesignatedBloodWriteUserRepository;
import project.bcvita.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final DesignatedBloodWriteRepository designatedBloodWriteRepository;
    private final UserRepository userRepository;
    private final DesignatedBloodWriteUserRepository designatedBloodWriteUserRepository;

    @Transactional
    public String create(Long id,BoardCreateRequestDto requestDto) {
        User user = userRepository.findById(id).orElse(null);

        DesignatedBloodWrite designatedBloodWrite = new DesignatedBloodWrite();
        designatedBloodWrite.setHospitalName(requestDto.getHospitalName());
        designatedBloodWrite.setRequestHospitalAddress(requestDto.getRequestHospitalAddress());
        designatedBloodWrite.setHospitalPhoneNumber(requestDto.getHospitalPhoneNumber());
        designatedBloodWrite.setPatientBlood(requestDto.getPatientBlood());
        designatedBloodWrite.setPatientIsRH(requestDto.getPatientIsRH());
        designatedBloodWrite.setBloodType(requestDto.getBloodType());
        designatedBloodWrite.setNeedBloodSystem(requestDto.getNeedBloodSystem());
        designatedBloodWrite.setStartDate(requestDto.getStartDate());
        designatedBloodWrite.setEndDate(requestDto.getEndDate());
        designatedBloodWrite.setTitle(requestDto.getTitle());
        designatedBloodWrite.setContent(requestDto.getContent());
        DesignatedBloodWrite bloodWrite = designatedBloodWriteRepository.save(designatedBloodWrite);

        DesignatedBloodWriteUser designatedBloodWriteUser = new DesignatedBloodWriteUser();
        designatedBloodWriteUser.setBloodPersonNumber(requestDto.getBloodPersonNumber());
        designatedBloodWriteUser.setBloodNumber(requestDto.getBloodNumber());
        designatedBloodWriteUser.setPatientName(requestDto.getPatientName());
        designatedBloodWriteUser.setPatientAge(requestDto.getPatientAge());
        designatedBloodWriteUser.setHospitalRoomNumber(requestDto.getHospitalRoomNumber());
        designatedBloodWriteUser.setBloodMatchType(requestDto.isBloodMatchType());
        designatedBloodWriteUser.setReview(requestDto.isReview());
        designatedBloodWriteUser.setDesignatedBloodWrite(bloodWrite);
        designatedBloodWriteUser.setUserNumber(user);
        designatedBloodWriteUserRepository.save(designatedBloodWriteUser);
        return "????????? ????????????";
    }


    @Transactional(readOnly = true)
    public List<BoardListResponse> boardListResponseList() {
        List<DesignatedBloodWrite> boardWriteList = designatedBloodWriteRepository.findAll();
        List<BoardListResponse> boardListResponse = new ArrayList<>();
        for (DesignatedBloodWrite designatedBloodWrite : boardWriteList) {
            DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWriteId(designatedBloodWrite.getId()).orElse(null);
            if(designatedBloodWriteUser == null) {
                throw new IllegalArgumentException("DesignatedBloodWriteUser ?????? null");
            }

            boardListResponse.add(new BoardListResponse(designatedBloodWrite.getHospitalName(), designatedBloodWrite.getTitle(),
                    designatedBloodWrite.getContent(), designatedBloodWrite.getPatientBlood(), designatedBloodWrite.getBloodType(), designatedBloodWrite.getStartDate(),
                    designatedBloodWrite.getId(),designatedBloodWriteUser.getBloodNumber()));
        }
        return boardListResponse;
    }



}
