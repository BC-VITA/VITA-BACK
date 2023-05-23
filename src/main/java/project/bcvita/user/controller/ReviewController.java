package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.ReviewCommentDto;
import project.bcvita.user.dto.request.ReviewRegisterRequestDto;
import project.bcvita.user.dto.response.ReviewRegisterResponse;
import project.bcvita.user.service.ReviewService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("review")
@RequiredArgsConstructor
@Valid
public class ReviewController {

    private final ReviewService reviewService;

    //후기 작성 게시물 api
    @PostMapping("/reviewBoard")
    public String reviewRegister(HttpSession session, @RequestBody ReviewRegisterRequestDto requestDto){
        return reviewService.reviewRegister(session, requestDto);
    }

    //후기 게시물 list
    @GetMapping("board/list")
    public List<ReviewRegisterResponse> boardListResponseList(@RequestParam String reviewType) {
        return reviewService.boardListResponseList(reviewType);
    }

    //댓글 작성 api
    @PostMapping("/comment")
    public ReviewCommentDto reviewRegisterComment(HttpSession session, @RequestBody ReviewCommentDto reviewCommentDto){
        return reviewService.writeComment(session,reviewCommentDto);
    }

}
