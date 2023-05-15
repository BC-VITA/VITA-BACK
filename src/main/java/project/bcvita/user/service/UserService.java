package project.bcvita.user.service;

import com.mysql.cj.xdevapi.Session;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import project.bcvita.user.dto.request.MyPageRequest;
import project.bcvita.user.dto.request.UserLoginRequestDto;
import project.bcvita.user.dto.request.UserPasswordCheck;
import project.bcvita.user.dto.request.UserRequest;
import project.bcvita.user.dto.response.MyPageResponse;
import project.bcvita.user.dto.response.UserInfo;
import project.bcvita.user.dto.response.UserListResponse;
import project.bcvita.user.dto.response.UserLoginResponse;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public String join(UserRequest request) {
        userRepository.save(User.builder()
                .userID(request.getUserID())
                .userPW(request.getPassword())
                .userName(request.getUserName())
                .userEmail(request.getUserEmail())
                .userBirth(request.getUserBirth())
                .userBlood(request.getUserBlood())
                //.userPoint(request.getUserPoint())
                .sex(request.getSex())
                .isRH(String.valueOf(request.getIsRH()))
                .bloodHistory(String.valueOf(request.getBloodHistory()))
                .userPhoneNumber(request.getUserPhoneNumber())
                .build());
        return "Success";
    }


    public List<UserListResponse> userList() {
        List<User> userList = userRepository.findAll();
        List<UserListResponse> userListResponse = new ArrayList<>();
        for (User user : userList) {
            userListResponse.add(new UserListResponse(user.getUserNumber(), user.getUserID(), user.getUserName()));

        }
        return userListResponse;
    }


    public String passwordCheck(UserPasswordCheck userPasswordCheck) {
        if (!userPasswordCheck.getPassword().equals(userPasswordCheck.getConfirmPassword())) {
            return "비밀번호가 일치하지 않습니다";
        }
        return "비밀번호가 일치합니다.";
    }


    public String login(UserLoginRequestDto userLoginRequestDto, HttpSession session) {
        User user = userRepository.findByUserIDAndUserPW(userLoginRequestDto.getUserId(), userLoginRequestDto.getUserPw());
        if (user == null) {
            throw new IllegalArgumentException("회원가입을 진행해주세요."); // 예외처리
        } else {
            session.setAttribute("loginId", user.getUserID());
            System.out.println("ooo");
        }
            System.out.println("user = " + user.getUserID());
        return "로그인 성공";
    }

    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "로그아웃 성공";
    }



    public UserInfo userInfo(HttpSession session)  {
        System.out.println("httpSession.getId() = " + session.getId());
        String userId = (String)session.getAttribute("loginId");
        System.out.println("userId = " + userId);
            if (userId == null) {
                throw new IllegalArgumentException("로그인한 사용자가 없음");
            }
        User user = userRepository.findByUserID(userId);
        return new UserInfo(user.getUserID(),user.getUserName());
    }

    public MyPageResponse myPage(HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");
        if(loginId == null) {
            return null;
        }
        User user = userRepository.findByUserID(loginId);
        return new MyPageResponse(user.getUserID(),user.getUserName(),user.getUserPhoneNumber(),user.getUserEmail(),
                user.getUserBirth(), user.getUserBlood(), user.getSex(),user.getIsRH(),user.getBloodHistory(),user.getUserPoint());
    }
    @Transactional
    public MyPageResponse updateMyPage(HttpSession session, MyPageRequest request) {
        String loginId = (String) session.getAttribute("loginId");

        if(loginId == null) {
            return null;
        }
        User user = userRepository.findByUserID(loginId);
        String password = user.getUserPW();
        if((request.getPassword() != null && request.getConfirmPassword() == null) || (request.getPassword() == null && request.getConfirmPassword() != null)) {
            throw new RuntimeException("비밀번호와 비밀번호 재확인이 일치하지 않습니다.");
        }
        if(request.getPassword() != null && request.getConfirmPassword() != null){
            if (!request.getPassword().equals(request.getConfirmPassword())) {
                throw new RuntimeException("비밀번호와 비밀번호 재확인이 일치하지 않습니다.");
            }else {
                password = request.getPassword();
            }
        }

        user.setUserID(request.getUserId());
                user.setUserName(request.getUserName());
                user.setUserBirth(request.getUserBirth());
                user.setUserPW(password);
                user.setUserEmail(request.getUserEmail());
                user.setUserBlood(request.getUserBlood());
                user.setSex(request.getSex());
                user.setIsRH(request.getIsRH());
                user.setBloodHistory(request.getBloodHistory());
                user.setUserPhoneNumber(request.getUserPhoneNumber());
        return new MyPageResponse(user.getUserID(),user.getUserName(),user.getUserPhoneNumber(),user.getUserEmail(),
                user.getUserBirth(), user.getUserBlood(), user.getSex(),user.getIsRH(),user.getBloodHistory(),user.getUserPoint());

    }


}
