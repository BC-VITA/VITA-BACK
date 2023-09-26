package project.bcvita.user.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.bcvita.user.dto.request.AdminJoinAccpetRequest;
import project.bcvita.user.dto.request.WarmCaseRegisterRequestDto;
import project.bcvita.user.dto.response.AdminWarmCaseResponse;
import project.bcvita.user.service.AdminService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("admin")
@RequiredArgsConstructor
@Valid
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/adminJoinAccept")
    public String adminJoinAcceptOrCancel(@RequestBody AdminJoinAccpetRequest adminJoinAccpetRequest) {
        return adminService.adminJoinAcceptOrCancel(adminJoinAccpetRequest);
    }

    //따뜻한 게시물 등록 api
    @PostMapping("/warm-case-admin-register")
    public String warmCaseRegister(@ModelAttribute WarmCaseRegisterRequestDto warmCaseRegisterRequestDto, @RequestPart(value = "file") MultipartFile file) {
        return adminService.warmCaseRegister(warmCaseRegisterRequestDto, file);
    }

    //따뜻한 게시물 list api
    @GetMapping("/warm-case-admin-list")
    public List<AdminWarmCaseResponse> adminWarmCaseResponses() {
        return adminService.adminWarmCaseResponses();
    }
}
