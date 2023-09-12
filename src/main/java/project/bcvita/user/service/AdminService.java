package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.bcvita.chat.dto.ChatApproveOrCancelRequest;
import project.bcvita.chat.entity.ChatRoom;
import project.bcvita.user.dto.request.AdminJoinAccpetRequest;
import project.bcvita.user.dto.request.WarmCaseRegisterRequestDto;
import project.bcvita.user.entity.Hospital;
import project.bcvita.user.entity.User;
import project.bcvita.user.entity.WarmCaseRegister;
import project.bcvita.user.repository.HospitalRepository;
import project.bcvita.user.repository.UserRepository;
import project.bcvita.user.repository.WarmCaseRegisterRepository;

import javax.servlet.http.HttpSession;
import java.io.File;

@RequiredArgsConstructor
@Service

public class AdminService {

    private final UserRepository userRepository;

    private final WarmCaseRegisterRepository warmCaseRegisterRepository;

    private final HospitalRepository hospitalRepository;


    //따뜻한 사례 글 등록 api구현
    @Transactional
    public String warmCaseRegister(HttpSession session, WarmCaseRegisterRequestDto warmCaseRegisterRequestDto, MultipartFile file) {
        try {
            String path = "C:\\vita";
            File destination = new File(path + File.separator + file.getOriginalFilename());
            file.transferTo(destination);
            String loginId = (String)session.getAttribute("loginId");
            User user = userRepository.findByUserID(loginId);
            if (user.isAdmin() == false) {
                return "관리자가 아니므로 저장 안됨";
            }
            WarmCaseRegister warmCaseRegister = new WarmCaseRegister();
            warmCaseRegister.create(warmCaseRegisterRequestDto.getTitle(), warmCaseRegisterRequestDto.getContent(), destination.getAbsolutePath());
            warmCaseRegisterRepository.save(warmCaseRegister);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "따뜻한 게시글 작성완료";
}

    //관리자 회원가입 승인 수락/거절 api
    @Transactional
    public String adminJoinAcceptOrCancel(AdminJoinAccpetRequest adminJoinAccpetRequest) {
        Long hospitalId = adminJoinAccpetRequest.getHospitalId();
        Hospital hospital = hospitalRepository.findById(hospitalId).get();
            hospital.updateIsAccept(adminJoinAccpetRequest.getIsAdminJoinAccept());
            return hospital.getIsAdminJoinAccept() == true ? "수락됨" : "취소됨";
        }

    
}
