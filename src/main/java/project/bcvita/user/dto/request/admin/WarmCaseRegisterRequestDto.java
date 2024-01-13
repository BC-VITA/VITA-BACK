package project.bcvita.user.dto.request.admin;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WarmCaseRegisterRequestDto {
    
    private String title;

    private String content;

    private String imageUrl;

    private String userId;
}
