package project.bcvita.user.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.bcvita.user.dto.request.BloodHouseRequestDto;
import project.bcvita.user.dto.request.BoardCreateRequestDto;
import project.bcvita.user.dto.response.BloodHouseResponse;
import project.bcvita.user.dto.response.BoardListResponse;
import project.bcvita.user.service.BloodHouseService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("blood")
@RequiredArgsConstructor
@Valid
public class BloodHouseController {
    private final BloodHouseService bloodHouseService;


    @GetMapping("board/list")
    public List<BloodHouseResponse> bloodHouseResponseList() {
        return bloodHouseService.bloodHouseResponseList();
    }









}
