package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.UserPasswordCheck;
import project.bcvita.user.dto.request.UserRequest;
import project.bcvita.user.dto.response.UserListResponse;
import project.bcvita.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("user")
@RequiredArgsConstructor
@Valid
public class UserController {

    private  final UserService userService;

    @GetMapping("/join")
    public String join(){
        return "join";
    }

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

    }





