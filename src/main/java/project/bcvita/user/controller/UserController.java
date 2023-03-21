package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.BoardCreateRequestDto;
import project.bcvita.user.dto.request.UserPasswordCheck;
import project.bcvita.user.dto.request.UserRequest;
import project.bcvita.user.dto.response.BoardListResponse;
import project.bcvita.user.dto.response.UserListResponse;
import project.bcvita.user.entity.DesignatedBloodWrite;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.DesignatedBloodWriteRepository;
import project.bcvita.user.service.BoardService;
import project.bcvita.user.service.LoginService;
import project.bcvita.user.service.TestService;
import project.bcvita.user.service.UserService;

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

        if(userService.join(request).equals("Success")){
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
    public String passwordCheck(@Valid @RequestBody UserPasswordCheck userPasswordCheck){
        return userService.passwordCheck(userPasswordCheck);
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginId(@ModelAttribute User user){
        if(LoginService.login(user)){
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/board/{user-id}")
    public String create(@PathVariable("user-id")Long id,@RequestBody BoardCreateRequestDto requestDto) {
        return boardService.create(id,requestDto);
    }

//    @PostMapping("/board/{user-id}")
//    public String createTest(@PathVariable("user-id")Long id,@RequestBody testDto requestDto) {
//        return testService.createTest(id,requestDto);
//    }


//    @GetMapping("/board/list")
//    public List<BoardTestListResponse> boardTest() {
//        return testService.boardTestListResponseList();
//    }


  /*  @GetMapping("/board/list")
    public List<BoardListResponse> boardList() {
        return boardService.boardListResponseList();
    }
*/
    @GetMapping("/board/filter")
    public List<BoardListResponse> boardFilter(@RequestParam(required = false) String patientIsRH, @RequestParam(required = false) String requestHospitalAddress, @RequestParam(required = false) String title) {
        return boardService.filter(patientIsRH,requestHospitalAddress,title);
    }


}





