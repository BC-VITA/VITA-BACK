package project.bcvita.user.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.AdminJoinAccpetRequest;
import project.bcvita.user.service.AdminService;

import javax.validation.Valid;

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
}
