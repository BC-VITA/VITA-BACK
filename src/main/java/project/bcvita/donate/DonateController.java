package project.bcvita.donate;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import project.bcvita.donate.dto.request.DonateBoardRequest;
import project.bcvita.donate.dto.response.DonateBoardResponse;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donate")
public class DonateController {

    private final DonateService donateService;

    @PostMapping("/board")
    public String writeDonateBoard(HttpSession session, @RequestBody DonateBoardRequest donateBoardRequest) {
        return donateService.writeDonateBoard(session, donateBoardRequest);
    }

    @GetMapping("/board")
    public Page<DonateBoardResponse> boardList(@PageableDefault(size = 6)Pageable pageable) {
        return donateService.boardList(pageable);
    }

    @GetMapping("/board/{id}")
    public DonateBoardResponse boardDetail(@PathVariable Long id) {
        return donateService.boardDetail(id);
    }

}
