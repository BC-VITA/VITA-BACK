package project.bcvita.point.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.bcvita.point.service.PointHistoryService;
import project.bcvita.point.dto.response.PointHistoryResponse;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("point")
public class PointHistoryController {
    private final PointHistoryService pointHistoryService;

    //마이페이지에서 포인트 조회
    @GetMapping
    public List<PointHistoryResponse> pointHistory(HttpSession session) {
        return pointHistoryService.pointHistory(session);
    }
}
