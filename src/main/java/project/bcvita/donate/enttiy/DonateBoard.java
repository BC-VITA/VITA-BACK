package project.bcvita.donate.enttiy;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import project.bcvita.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
public class DonateBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String imageUrl;

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
