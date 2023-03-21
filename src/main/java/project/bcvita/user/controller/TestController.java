package project.bcvita.user.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping
    public String getRequestParam(@RequestParam String keyword) {
        return "키워드 = " + keyword;
    }
    @PostMapping
    public String postRequestParam(@RequestParam String keyword) {
        return "키워드 = " + keyword;
    }

    @PutMapping
    public String putRequestParam(@RequestParam String keyword) {
        return "키워드 = " + keyword;
    }
    @DeleteMapping
    public String deleteRequestParam(@RequestParam String keyword) {
        return "키워드 = " + keyword;
    }
}
