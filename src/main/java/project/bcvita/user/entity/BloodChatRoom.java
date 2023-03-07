/*package project.bcvita.user.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodChatRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    private DesignatedBloodWriteUser designatedBloodWriteUser;

    @ManyToOne
    private User createWriteUser;

    @ManyToOne
    private User viewWriteUser;


    private LocalDateTime createdAt;

    private String chatIsFinish;
}
*/