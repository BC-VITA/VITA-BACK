package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.bcvita.user.dto.request.ReviewRegisterRequestDto;
import project.bcvita.user.service.ReviewService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("review")
@RequiredArgsConstructor
@Valid
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviewBoard")
    public String reviewRegister(HttpSession session, @RequestBody ReviewRegisterRequestDto requestDto){
        return reviewService.reviewRegister(session, requestDto);
    }

}
