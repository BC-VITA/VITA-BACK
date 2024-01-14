package project.bcvita.user.service.bloodHouse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bcvita.heart.WishListRepository;
import project.bcvita.heart.dto.WishListTableResponse;
import project.bcvita.heart.entity.WishList;
import project.bcvita.user.dto.request.bloodHouse.BoardCreateRequestPostDto;
import project.bcvita.user.dto.request.wishList.WishListRequestDto;
import project.bcvita.user.dto.response.bloodHouse.BoardListResponse;
import project.bcvita.user.entity.designatedBlood.DesignatedBloodWrite;
import project.bcvita.user.entity.designatedBlood.DesignatedBloodWriteUser;
import project.bcvita.user.entity.user.User;
import project.bcvita.user.entity.volunteer.VolunteerRegister;
import project.bcvita.user.repository.designatedBlood.DesignateBloodWishListRepository;
import project.bcvita.user.repository.designatedBlood.DesignatedBloodWriteRepository;
import project.bcvita.user.repository.designatedBlood.DesignatedBloodWriteUserRepository;
import project.bcvita.user.repository.hospital.HospitalRepository;
import project.bcvita.user.repository.user.UserRepository;
import project.bcvita.user.repository.volunteer.VolunteerRegisterRepository;
import project.bcvita.user.service.UserService;

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
    public String create(BoardCreateRequestPostDto requestDto) {
        User user = userRepository.findByUserID(requestDto.getUserName());

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
        return "게시글 작성완료";
    }


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