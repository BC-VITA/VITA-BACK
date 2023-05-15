package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.*;
import project.bcvita.user.dto.response.BoardListResponse;
import project.bcvita.user.dto.response.MyPageResponse;
import project.bcvita.user.dto.response.UserListResponse;
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
    public List<BoardListResponse> boardFilter(@RequestParam(required = false) String patientIsRH, @RequestParam(required = false) String requestHospitalAddress, @RequestParam(required = false) String title, @RequestParam(required = false) String content, @RequestParam(required = false) String patientBlood, @RequestParam(required = false) String hospitalName, @RequestParam(required = false) String bloodType) {
        return boardService.filter(patientIsRH, requestHospitalAddress, title, content, patientBlood, hospitalName, bloodType);
    }

    @PostMapping("/login")
    public String loginPost(@RequestBody UserLoginRequestDto userLoginRequestDto, HttpSession session) {
        return userService.login(userLoginRequestDto, session);
    }


    @GetMapping("/logout")
    public String logoutGet(HttpSession session) {
        return userService.logout(session);
    }


    @PostMapping("/wishList/wishListUpdate")
    public String wishListUpdate(@RequestBody WishListRequestDto wishListRequestDto) {
        return boardService.wishListUpdate(wishListRequestDto);
    }

    @PostMapping("/wishList/delete")
    public String wishListDelete(@RequestBody WishListRequestDto wishListRequestDto) {
        return boardService.wishListDelete(wishListRequestDto);
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
    public MyPageResponse myPage(HttpSession session, MyPageRequest request) {
        return userService.myPage(session,request);
    }
    @PutMapping("/mypage")
    public MyPageResponse updateMyPage(HttpSession session, @RequestBody MyPageRequest request) {
        return userService.updateMyPage(session,request);
    }
}





