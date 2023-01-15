package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.bcvita.user.dto.request.UserPasswordCheck;
import project.bcvita.user.dto.request.UserRequest;
import project.bcvita.user.dto.response.UserListResponse;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;


    @Transactional
    public String join(UserRequest request){
        userRepository.save(User.builder()
                .userID(request.getUserID())
                .userPW(request.getPassword())
                .userName(request.getUserName())
                .userEmail(request.getUserEmail())
                .userBirth(request.getUserBirth())
                .userBlood(request.getUserBlood())
                //.userPoint(request.getUserPoint())
                .sex(request.getSex())
                .isRH(request.getIsRH())
                .bloodHistory(Integer.valueOf(request.getBloodHistory()))
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
        if(!userPasswordCheck.getPassword().equals(userPasswordCheck.getConfirmPassword())) {
            return "비밀번호가 일치하지 않습니다";
        }
        return "비밀번호가 일치합니다.";
   }

}
