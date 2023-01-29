package project.bcvita.user.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import project.bcvita.user.entity.User;
import project.bcvita.user.repository.UserRepository;

@RestController
@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private static final UserRepository userRepository = null;

    public static boolean login(User user){
        User findUser = userRepository.findByUserID(user.getUserID());

        if(findUser == null){
            return false;
        }

        if(!findUser.getUserPW().equals(user.getUserPW())){
            return false;
        }
        return true;

    }



}
