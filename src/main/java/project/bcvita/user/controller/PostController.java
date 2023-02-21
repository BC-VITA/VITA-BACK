package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.bcvita.user.dto.response.BoardListResponse;

import javax.validation.Valid;
import java.util.List;
//
//@RestController
//@Slf4j
//@RequestMapping("post")
//@RequiredArgsConstructor
//@Valid
//public class PostController {
////    @GetMapping("/search")
////    public ResponseEntity<List<PostReadResponseDto>> searchPost(@RequestParam String query) {
////        return postService.searchPost(query);
//    }
//}
