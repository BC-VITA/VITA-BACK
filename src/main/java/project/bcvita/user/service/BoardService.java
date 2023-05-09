package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.user.dto.request.BoardCreateRequestDto;
import project.bcvita.user.dto.request.RequestCenterNameDto;
import project.bcvita.user.dto.request.UserLoginRequestDto;
import project.bcvita.user.dto.request.WishListRequestDto;
import project.bcvita.user.dto.response.BloodHouseReservationResponse;
import project.bcvita.user.dto.response.BoardListResponse;
import project.bcvita.user.dto.response.WishListResponse;
import project.bcvita.user.entity.*;
import project.bcvita.user.repository.DesignateBloodWishListRepository;
import project.bcvita.user.repository.DesignatedBloodWriteRepository;
import project.bcvita.user.repository.DesignatedBloodWriteUserRepository;
import project.bcvita.user.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardService {
    private final DesignatedBloodWriteRepository designatedBloodWriteRepository;
    private final UserRepository userRepository;
    private final DesignatedBloodWriteUserRepository designatedBloodWriteUserRepository;

    private final DesignateBloodWishListRepository designateBloodWishListRepository;


    @Transactional
    public String create(HttpSession session, BoardCreateRequestDto requestDto) {
        //User user = userRepository.findById(id).orElse(null);
        User user = (User) session.getAttribute("user");

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
        //log.info("user.getUserNAme() = {}", user.getUserName());
        //System.out.println("user.getUserName() = " + user.getUserName());
        return "게시글 작성완료";
    }


    public List<BoardListResponse> filter(String patientIsRH, String requestHospitalAddress, String title, String content, String hospitalName, String patientBlood, String bloodType) {
        List<DesignatedBloodWrite> postList = null;
        if (patientIsRH != null) { //rh여부
            postList = designatedBloodWriteRepository.filterIsRH(patientIsRH);
        } else if (requestHospitalAddress != null) { //병원주소
            postList = designatedBloodWriteRepository.filterArea(requestHospitalAddress);
        } else if (patientIsRH != null && requestHospitalAddress != null) { // rh여부 + 병원주소
            postList = designatedBloodWriteRepository.IsRHAndArea(patientIsRH, requestHospitalAddress);
        } else if (title != null) { //제목
            postList = designatedBloodWriteRepository.filterTitle(title);
        } else if (patientIsRH == null && requestHospitalAddress != null) { //rh여부 + 병원주소
            postList = designatedBloodWriteRepository.filterArea(requestHospitalAddress);
        } else if (content != null) { //내용
            postList = designatedBloodWriteRepository.filterContent(content);
        } else if (patientBlood != null) { //혈액형
            postList = designatedBloodWriteRepository.filterPatientBlood(patientBlood);
        } else if (hospitalName != null) { //병원이름
            postList = designatedBloodWriteRepository.filterHospitalName(hospitalName);
        } else if (bloodType != null) { //혈액종류
            postList = designatedBloodWriteRepository.filterBloodType(bloodType);
        } else if (patientBlood != null && requestHospitalAddress != null) { //혈액형 + 병원주소
            postList = designatedBloodWriteRepository.filterBloodAndAddress(patientBlood, requestHospitalAddress);
        } else if (title != null && content != null) { //제목 + 내용
            postList = designatedBloodWriteRepository.filterTitleAndContent(title, content);
        } else if (title != null && requestHospitalAddress != null) { //제목 + 병원주소
            postList = designatedBloodWriteRepository.filterTitleAndAddress(title, requestHospitalAddress);
        } else if (patientIsRH != null && bloodType != null) { //rh여부 + 혈액종류
            postList = designatedBloodWriteRepository.filterRhAndType(patientIsRH, bloodType);
        } else if (patientIsRH != null && requestHospitalAddress != null && title != null) { //rh여부 + 병원주소 + 제목
            postList = designatedBloodWriteRepository.IsRHAndAreaAndTitle(patientIsRH, requestHospitalAddress, title);
        } else if (patientIsRH != null && title != null && content != null) { //rh여부 + 제목 + 내용
            postList = designatedBloodWriteRepository.filterRhAndContentAndTitle(patientIsRH, title, content);
        } else if (hospitalName != null && title != null) { //병원이름 + 제목
            postList = designatedBloodWriteRepository.filterHospitalNameAndTitle(hospitalName, title);
        } else if (hospitalName != null && content != null) { //병원이름 + 내용
            postList = designatedBloodWriteRepository.filterHospitalNameAndContent(hospitalName, content);
        } else if (hospitalName != null && patientBlood != null) { //병원이름 + 혈액형
            postList = designatedBloodWriteRepository.filterHospitalNameAndPatientBlood(hospitalName, patientBlood);
        } else if (hospitalName != null && bloodType != null) { //병원이름 + 혈액종류
            postList = designatedBloodWriteRepository.filterHospitalNameAndBloodType(hospitalName, bloodType);
        } else if (hospitalName != null && patientIsRH != null) { //병원이름 + rh여부
            postList = designatedBloodWriteRepository.filterHospitalNameAndRh(hospitalName, patientIsRH);
        } else {
            postList = designatedBloodWriteRepository.findAll();
        }

        List<BoardListResponse> resultList = new ArrayList<>();
        for (DesignatedBloodWrite post : postList) {
            DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWrite(post).orElse(null);
            if (designatedBloodWriteUser == null) {
                continue;
            }
            BoardListResponse boardListResponse = new BoardListResponse(post.getHospitalName(), post.getTitle(), post.getContent(),
                    post.getPatientBlood(), post.getBloodType(), post.getStartDate(), post.getId(), designatedBloodWriteUser.getBloodNumber());
            resultList.add(boardListResponse);
        }

        return resultList;
    }


    @Transactional(readOnly = true)
    public List<BoardListResponse> boardListResponseList() {
        List<DesignatedBloodWrite> boardWriteList = designatedBloodWriteRepository.findAll();
        List<BoardListResponse> boardListResponse = new ArrayList<>();
        for (DesignatedBloodWrite designatedBloodWrite : boardWriteList) {
            DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWriteId(designatedBloodWrite.getId()).orElse(null);
            if (designatedBloodWriteUser == null) {
                throw new IllegalArgumentException("DesignatedBloodWriteUser 값이 null");
            }

            boardListResponse.add(new BoardListResponse(designatedBloodWrite.getHospitalName(), designatedBloodWrite.getTitle(),
                    designatedBloodWrite.getContent(), designatedBloodWrite.getPatientBlood(), designatedBloodWrite.getBloodType(), designatedBloodWrite.getStartDate(),
                    designatedBloodWrite.getId(), designatedBloodWriteUser.getBloodNumber()));
        }
        return boardListResponse;
    }


//    @Transactional
//    public String wishListInsert(WishListRequestDto wishListRequestDto) {
//        User user = userRepository.findByUserID(wishListRequestDto.getLoginId());
//
//        if (wishListRequestDto.getBoardType().equals("user")) { //user 타입
//            DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWriteId(wishListRequestDto.getBoardId()).get();
//            DesignatedBloodWishList designatedBloodWishList = new DesignatedBloodWishList();
//            designatedBloodWishList.setUser(user);
//            designatedBloodWishList.setDesignatedBloodWriteUser(designatedBloodWriteUser);
//            designateBloodWishListRepository.save(designatedBloodWishList);
//            return "찜하기 성공";
//        }
//        //병원에 대한 로직 작성
//        return "실패";
//    }


    //좋아요 여부
    @Transactional
    public String wishListUpdate(WishListRequestDto wishListRequestDto) {
        //User user = userRepository.findByUserID(wishListRequestDto.getLoginId());
        User user = userRepository.findAllByUserID(wishListRequestDto.getLoginId());
        if (wishListRequestDto.getBoardType().equals("user")) {
            DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWriteId(wishListRequestDto.getBoardId()).get();
            designatedBloodWriteUser.setUserNumber(user);
            designatedBloodWriteUser.setWishListCount(wishListRequestDto.getWishListCount() + 1);
            designatedBloodWriteUserRepository.save(designatedBloodWriteUser);
            return "찜하기 성공";
        }
        //병원에 대한 로직 작성
        return "실패";
    }


//    @Transactional
//    public List<WishListResponse> wishListResponseList() {
//
//    }

//    public List<BloodHouseReservationResponse> registerReservationResponse(RequestCenterNameDto centerName) {
//        BloodHouse blood = bloodHouseRepository.findByCenterName(centerName.getCenterName());
//        System.out.println("centerName = " + centerName.getCenterName());
//        if (blood == null) {
//            throw new IllegalArgumentException("BloodHouse 값이 null");
//        }
//        List<BloodHouseRegister> bloodHouseRegisterList = bloodHouseRegisterRepository.findAllByBloodHouse(blood);
//        List<BloodHouseReservationResponse> bloodHouseReservationResponses = new ArrayList<>();
//        for (BloodHouseRegister bloodHouseRegister : bloodHouseRegisterList) {
//            bloodHouseReservationResponses.add(new BloodHouseReservationResponse((bloodHouseRegister.getBloodHouse().getCenterName()), bloodHouseRegister.getDate(),bloodHouseRegister.getTime(),bloodHouseRegister.getWholeBlood(), bloodHouseRegister.getPlasma(), bloodHouseRegister.getPlatelet()));
//        }
//        return bloodHouseReservationResponses;
//    }

    @Transactional
    public String wishListDelete(WishListRequestDto wishListRequestDto) {
        User user  = userRepository.findByUserID(wishListRequestDto.getLoginId());

        if (wishListRequestDto.getBoardType().equals("user")){
            DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWriteId(wishListRequestDto.getBoardId()).get();
            DesignatedBloodWishList designatedBloodWishList = new DesignatedBloodWishList();
            designatedBloodWishList.setUser(user);
            designatedBloodWishList.setDesignatedBloodWriteUser(designatedBloodWriteUser);
            designateBloodWishListRepository.delete(designatedBloodWishList);
            return "찜하기 취소";
        }
        //병원에 대한 로직 작성
        return "실패";
    }



}