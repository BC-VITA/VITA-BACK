package project.bcvita.donate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class DonateBoardRequest {
    private String userId;
    private String title;
    private String content;
    private String imageUrl;
}
