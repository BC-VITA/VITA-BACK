package project.bcvita.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;

@RestController
@Slf4j
@RequestMapping("img")
@RequiredArgsConstructor
@Valid
public class ImageUploadController {

    @PostMapping("/upload")
    public String handleImageUpload(@RequestParam("image") MultipartFile image) {

        try {
            // 이미지 저장 경로 설정
            String imagePath = "C:\\Users\\lby99\\OneDrive\\바탕 화면\\springTest\\java.png";

            // 이미지 파일 저장
            image.transferTo(new File(imagePath));

            return "Image uploaded successfully";
        } catch (Exception e) {
            return "Error uploading image: " + e.getMessage();
        }
    }

}
