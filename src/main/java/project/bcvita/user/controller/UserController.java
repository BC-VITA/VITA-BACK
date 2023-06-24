package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.*;
import project.bcvita.user.dto.response.*;
import project.bcvita.user.repository.DesignatedBloodWriteRepository;
import project.bcvita.user.service.BoardService;
import project.bcvita.user.service.TestService;
import project.bcvita.user.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("user")
@RequiredArgsConstructor
@Valid
public class UserController {

    private final UserService userService;
    private final BoardService boardService;
    private final TestService testService;

    private final DesignatedBloodWriteRepository designatedBloodWriteRepository;


    @PostMapping("/join")
    public ResponseEntity join(@Valid @RequestBody UserRequest request) {

        if (userService.join(request).equals("Success")) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/list")
    public List<UserListResponse> test() {
        return userService.userList();
    }


    /*@PostMapping("/confirmPassword")
    @ResponseBody
    public PasswordCheckDto passwordCheck(@RequestBody PasswordCheckDto passwordCheckDto){
        passwordCheckDto.setResult(UserService.passwordcheck);
    }*/

    @PostMapping("/password-check")
    public String passwordCheck(@Valid @RequestBody UserPasswordCheck userPasswordCheck) {
        return userService.passwordCheck(userPasswordCheck);
    }

//    @PostMapping("/board/{user-id}")
//    public String create(@PathVariable("user-id")Long id,@RequestBody BoardCreateRequestDto requestDto) {
//        return boardService.create(id,requestDto);
//    }

    @PostMapping("/board")
    public String create(HttpSession session, @RequestBody BoardCreateRequestDto requestDto) {
        return boardService.create(session, requestDto);
    }

//    @PostMapping("/board/{user-id}")
//    public String createTest(@PathVariable("user-id")Long id,@RequestBody testDto requestDto) {
//        return testService.createTest(id,requestDto);
//    }


//    @GetMapping("/board/list")
//    public List<BoardTestListResponse> boardTest() {
//        return testService.boardTestListResponseList();
//    }


//    @GetMapping("/board/list") // -> 이제 board.filter부분으로 확인하면 된다. 프론트쪽 api 수정하기
//    public List<BoardListResponse> boardList() {
//        return boardService.boardListResponseList();
//    }

    @GetMapping("/board/filter")
    public List<BoardListResponse> boardFilter(HttpSession session,@RequestParam(required = false) String patientIsRH, @RequestParam(required = false) String requestHospitalAddress, @RequestParam(required = false) String title, @RequestParam(required = false) String content, @RequestParam(required = false) String patientBlood, @RequestParam(required = false) String hospitalName, @RequestParam(required = false) String bloodType) {
        return boardService.filter(session,patientIsRH, requestHospitalAddress, title, content, patientBlood, hospitalName, bloodType);
    }

    @PostMapping("/login")
    public String loginPost(@RequestBody UserLoginRequestDto userLoginRequestDto, HttpSession session) {
        return userService.login(userLoginRequestDto, session);
    }


    @GetMapping("/logout")
    public String logoutGet(HttpSession session) {
        System.out.println("userService.loginId(session) = " + userService.loginId(session));
        return userService.logout(session);
    }


    //좋아요 / 좋아요 취소 API
    // 좋아요 처음 클릭시 좋아요 됨
    // 좋아요 된 상태에서 좋아요 클릭하면 좋아요 해제됨
    @PostMapping("/wishList/wishListUpdate")
    public String wishListUpdate(HttpSession session,@RequestBody WishListRequestDto wishListRequestDto) {
        return boardService.wishListUpdate(session,wishListRequestDto);
    }


    /*@GetMapping("/check")
    public UserInfo check(HttpSession session) {
        if(session == null ){
            throw new IllegalArgumentException("세션이 존재하지 않습니다.");
        }
        System.out.println(session.getId());
        return userService.userInfo(session);
    }*/

    @GetMapping("/mypage")
    public MyPageResponse myPage(String userId,@RequestParam(required = false) String reviewType) {
        return userService.myPage(userId,reviewType);
    }
    @PutMapping("/mypage")
    public MyPageUserInfoResponse updateMyPage(String userId, @RequestBody MyPageRequest request) {
        return userService.updateMyPage(userId,request);
    }

    @GetMapping("/mypage-designated-write")
    public List<MyPageDesignatedBloodBoardResponse> mypageWrite(HttpSession session){
        return userService.myPage(session);
    }

    @PutMapping("/mypage-designated-write-update")
    public MyPageDesignatedBloodHistoryResponse mypageWriteUpdate(HttpSession session, @RequestBody BoardCreateRequestDto requestDto) {
        return userService.updateDesignatedBoardMyPage(session, requestDto);
    }

    @DeleteMapping("/mypage-designated-write-delete/{designatedId}/{designatedUserId}")
    public String mypageWriteDelete(HttpSession session, @PathVariable("designatedId") Long designatedId, @PathVariable("designatedUserId") Long designatedUserId){
        return userService.deleteMypageDesignatedBoard(session, designatedId, designatedUserId);
    }

    @GetMapping("/mypage-blood-reservation-history")
    public List<MyPageBloodReservationHistoryResponse> myPageBloodReservationHistory(HttpSession session){
        return userService.mypageBloodReservationHistory(session);
    }

    @DeleteMapping("/mypage-blood-reservation-cancel/{reservationId}")
    public String cancelMyPageBloodReservation(@PathVariable Long reservationId){
        return userService.CancelMyPageBloodReservation(reservationId);
    }

    @GetMapping("/mypage-designated-review")
    public List<MyPageDesignatedBloodReviewResponse> myPageDesignatedBloodReviewResponses(HttpSession session, String reviewType) {
        return userService.myPageBloodReviewResponses(session, reviewType);
    }

}





