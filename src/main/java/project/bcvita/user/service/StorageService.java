/*package project.bcvita.user.service;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.bcvita.user.ImageUtils;
import project.bcvita.user.entity.ImageData;
import project.bcvita.user.repository.StorageRepository;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;

    /*public String uploadImage(MultipartFile file) throws IOException {
        log.info("upload file: {}", file);
        ImageData imageData = storageRepository.save(
                ImageData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .imageData(ImageUtils.compressImage(file.getBytes()))
                        .build());
        if (imageData != null) {
            log.info("imageData: {}", imageData);
            return "file uploaded successfully : " + file.getOriginalFilename();
        }

        return null;
    }

    @Value("${upload.path}")
    private String uploadPath;


    public String uploadImage(MultipartFile imageFile) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
        String filePath = uploadPath + File.separator + fileName;

        // 파일 업로드 처리
        File destFile = new File(filePath);
        imageFile.transferTo(destFile);

        String imageUrl = "/uploads/" + fileName; // 이미지 URL 생성 (예시: /uploads/파일명)
        return imageUrl;
    }

    public void createDonationPost(String userId, String title, String content, String imageUrl) {
        // 게시물 생성 로직 수행
        // MySQL 데이터베이스에 imageUrl을 저장하는 코드를 구현해야 합니다.
        // 예시로 콘솔에 게시물 정보를 출력하는 코드를 작성했습니다.
        System.out.println("게시물 생성:");
        System.out.println("유저 아이디: " + userId);
        System.out.println("제목: " + title);
        System.out.println("내용: " + content);
        System.out.println("이미지 URL: " + imageUrl);
    }

}

 */
