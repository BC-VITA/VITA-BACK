package project.bcvita.donate.enttiy;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import project.bcvita.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class DonateBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //기부 게시글 제목
    private String title;
    
    //기부 게시글 내용
    private String content;
    
    //기부 게시글 이미지주소
    private String imageUrl;

    //기부 게시글마다 포인트내역
    private Integer pointHistory;

    //기부 게시글 등록 날짜
    private LocalDateTime localDateTime;


    public void create(String title, String content, String imageUrl, User user) {
        this.title =title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private User user;


}
