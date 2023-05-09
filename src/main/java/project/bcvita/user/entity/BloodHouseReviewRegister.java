package project.bcvita.user.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodHouseReviewRegister {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //작성한 후기 타입
    private String isReviewType;

    //작성한 회원 번호
    @ManyToOne
    private User user;
    
    //이미지 주소
    private String imgAddress;

    //후기내용
    private String content;

    //신고여부
    private boolean isReport;


}

