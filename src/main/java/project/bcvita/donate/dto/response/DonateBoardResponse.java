package project.bcvita.donate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DonateBoardResponse {

    private Long boardId;
    private String title;
    private String imageUrl;
    private String content;
}
