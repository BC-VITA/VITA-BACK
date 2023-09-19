package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.heart.WishListRepository;
import project.bcvita.heart.dto.WishListTableResponse;
import project.bcvita.heart.entity.WishList;
import project.bcvita.user.dto.request.BoardCreateRequestDto;
import project.bcvita.user.dto.request.WishListRequestDto;
import project.bcvita.user.dto.response.BoardListResponse;
import project.bcvita.user.entity.*;
import project.bcvita.user.repository.*;

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
    private final WishListRepository wishListRepository;

    private final DesignateBloodWishListRepository designateBloodWishListRepository;
    private final VolunteerRegisterRepository volunteerRegisterRepository;
    private final HospitalRepository hospitalRepository;
    private final UserService userService;



    @Transactional
    public String create(HttpSession session, BoardCreateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElse(null);
        //User user = userRepository.findByUserID((String) session.getAttribute("loginId"));

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
        designatedBloodWrite.setLocalDateTime(requestDto.getLocalDateTime().now());
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


//    public List<BoardListResponse> filter() {
//        List<DesignatedBloodWrite> postList = null;

        /*if (patientIsRH != null) { //rh여부
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
        }*/
//        postList = designatedBloodWriteRepository.findAll();
//        List<BoardListResponse> resultList = new ArrayList<>();
//
//        //User user = userRepository.findByUserID(userId);
//        List<DesignatedBloodWriteUser> designatedBloodWriteUserList = designatedBloodWriteUserRepository.findAll();
//        String loginId = null;
//
//        for (DesignatedBloodWrite post : postList) {
//            boolean isWishList = false;
//
//                    DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWrite(post).orElse(null);
//                    if (designatedBloodWriteUser == null) {
//                        continue;
//                    }
//
//                    if (user != null) {
//                        WishList wishList = wishListRepository.findByUserAndDesignatedBloodWriteUser(user, designatedBloodWriteUser).orElse(null);
//                        if (wishList != null) {
//                            isWishList = true;
//                        }
//                        loginId = user.getUserID();
//                    }
//
//                    BoardListResponse boardListResponse = new BoardListResponse(post.getHospitalName(), post.getTitle(), post.getContent(),
//                            post.getPatientBlood(), post.getBloodType(), post.getStartDate(), post.getId(), designatedBloodWriteUser.getBloodNumber(), wishListRepository.countByDesignatedBloodWriteUser(designatedBloodWriteUser), isWishList,designatedBloodWriteUser1.getUserNumber().getUserID(), post.getId());
//                    resultList.add(boardListResponse);
//                }
//
//            return resultList;
//        }


    public List<BoardListResponse> filter() {
        List<DesignatedBloodWrite> postList = designatedBloodWriteRepository.findAll();
        List<BoardListResponse> resultList = new ArrayList<>();

        for (DesignatedBloodWrite post : postList) {
            boolean isWishList = false;

            DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWrite(post).orElse(null);
            if (designatedBloodWriteUser == null) {
                continue;
            }

            User user = designatedBloodWriteUser.getUserNumber();
            if (user != null) {
                WishList wishList = wishListRepository.findByUserAndDesignatedBloodWriteUser(user, designatedBloodWriteUser).orElse(null);
                if (wishList != null) {
                    isWishList = true;
                }

                BoardListResponse boardListResponse = new BoardListResponse(
                        post.getHospitalName(), post.getTitle(), post.getContent(),
                        post.getPatientBlood(), post.getBloodType(), post.getStartDate(),
                        post.getId(), designatedBloodWriteUser.getBloodNumber(),
                        wishListRepository.countByDesignatedBloodWriteUser(designatedBloodWriteUser),
                        isWishList, user.getUserID(), post.getId()
                );

                resultList.add(boardListResponse);
            }
        }

        return resultList;
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
        User user = userRepository.findByUserID(wishListRequestDto.getUserId());
        Long boardId = wishListRequestDto.getBoardId();
        if (wishListRequestDto.getBoardType().equals("designatedBlood")) {
            DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWriteId(boardId).orElse(null);
            if(designatedBloodWriteUser == null) {
                throw new IllegalArgumentException("게시판 없습니다.");
            }
            WishList wishList = wishListRepository.findByUserAndDesignatedBloodWriteUser(user, designatedBloodWriteUser).orElse(null);
            if(wishList != null) {
                wishListRepository.delete(wishList);
            }else {
                WishList designateBloodWishList = new WishList();
                designateBloodWishList.createDesignateBloodHeart(user, "designatedBlood", true, designatedBloodWriteUser);
                wishListRepository.save(designateBloodWishList);
            }
        }else if(wishListRequestDto.getBoardType().equals("volunteer")) {
            VolunteerRegister volunteerRegister = volunteerRegisterRepository.findById(boardId).orElse(null);
            if(volunteerRegister == null) {
                throw new IllegalArgumentException("게시판 없습니다.");
            }
            WishList wishList = wishListRepository.findByUserAndVolunteerRegister(user, volunteerRegister).orElse(null);
            if(wishList != null) {
                wishListRepository.delete(wishList);
            }else {
                WishList volunteerList = new WishList();


                volunteerList.createVolunteerHeart(user, "volunteer", true, volunteerRegister);
                wishListRepository.save(volunteerList);
            }
        }
        //병원에 대한 로직 작성
        return "성공";
    }



    //마이페이지 관심있는 게시물 -> 타입별
//    @Transactional
//    public List<MypageWishListResponse> mypageWishList(String userId,MypageWishListRequest mypageWishListRequest) {
//        User user = userRepository.findByUserID(userId);
//        DesignatedBloodWriteUser designatedBloodWriteUser = designatedBloodWriteUserRepository.findByDesignatedBloodWriteId(mypageWishListRequest.getUserId()).get();
//        List<WishList> wishLists = wishListRepository.findByUserAndDesignatedBloodWriteUser(user,designatedBloodWriteUser).get();
//
//    }


        /*
         //마이페이지 헌혈 예약 내역 api
    @Transactional
    public List<MyPageBloodReservationHistoryResponse> mypageBloodReservationHistory(String userId){
        User user = userRepository.findByUserID(userId);
        //BloodHouseReservation reservation = bloodHouseReservationRepository.findById(request.getBloodReservationId()).get();
        List<BloodHouseReservation> reservations = bloodHouseReservationRepository.findAllByUser(user);
        List<MyPageBloodReservationHistoryResponse> list = new ArrayList<>();
        for (BloodHouseReservation bloodHouseReservation : reservations) {
            list.add(new MyPageBloodReservationHistoryResponse(user.getUserName(), user.getBloodHistory(), bloodHouseReservation.getIsBloodType(), bloodHouseReservation.getBloodHouse().getCenterName(), bloodHouseReservation.getDate()));
        }
        return list;
    }
         */

    //wishList테이블을 볼수있는 api
//    @Transactional
//    public List<WishListTableResponse> wishListTableResponses() {
//        List<WishList> wishLists = wishListRepository.findAll();
//        //WishList wishLists = wishListRepository.findAllBy().orElse(null);
//        List<WishListTableResponse> wishListTableResponses = new ArrayList<>();
//        for (WishList wishList : wishLists){
//            wishListTableResponses.add(new WishListTableResponse(wishList.getId(), wishList.getBoardType(), wishList.isWishList(), wishList.getDesignatedBloodWriteUser().getId(), wishList.getUser().getUserID(), wishList.getVolunteerRegister().getId()));
//        }
//        return wishListTableResponses;
//    }
    

    //wishList 테이블 볼수있는 api 최종
    @Transactional
    public List<WishListTableResponse> wishListTableResponses() {
        List<WishList> wishLists = wishListRepository.findAll();
        List<WishListTableResponse> wishListTableResponses = new ArrayList<>();

        for (WishList wishList : wishLists) {
            Long designatedBloodWriteUserId = wishList.getDesignatedBloodWriteUser() != null ? wishList.getDesignatedBloodWriteUser().getId() : null;
            String userId = wishList.getUser() != null ? wishList.getUser().getUserID() : null;
            Long volunteerRegisterId = wishList.getVolunteerRegister() != null ? wishList.getVolunteerRegister().getId() : null;

            wishListTableResponses.add(new WishListTableResponse(
                    wishList.getId(),
                    wishList.getBoardType(),
                    wishList.isWishList(),
                    designatedBloodWriteUserId,
                    wishList.getUser().getUserID(),
                    volunteerRegisterId
            ));
        }

        return wishListTableResponses;
    }
}