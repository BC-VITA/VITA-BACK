package project.bcvita.point.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.bcvita.point.repository.PointHistoryRepository;
import project.bcvita.point.dto.response.PointHistoryResponse;
import project.bcvita.point.entity.PointHistory;
import project.bcvita.user.entity.user.User;
import project.bcvita.user.repository.user.UserRepository;
import project.bcvita.user.service.user.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointHistoryService {
    private final PointHistoryRepository pointHistoryRepository;
    private final UserService userService;
    private final UserRepository userRepository;


    public List<PointHistoryResponse> pointHistory(HttpSession session) {
        User user = userRepository.findByUserID(userService.loginId(session));
        List<PointHistory> userPointHistoryList = pointHistoryRepository.findAllByUser(user);
        List<PointHistoryResponse> responseList = new ArrayList<>();
        String saveType = "";
        for (PointHistory pointHistory : userPointHistoryList) {
            if (pointHistory.getVolunteerReservation() != null || pointHistory.getReviewRegister() != null) {
                if (pointHistory.getReviewRegister() != null) {
                    saveType = "후기 작성";
                } else if (pointHistory.getVolunteerReservation() != null) {
                    saveType = "봉사 활동";
                }
                PointHistoryResponse reviewRegisterHistory = new PointHistoryResponse(saveType, pointHistory.getPoint(), pointHistory.getCreatedAt());
                responseList.add(reviewRegisterHistory);
            }
        }
        return responseList;
    }
}
