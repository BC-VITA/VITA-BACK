package project.bcvita.user.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;

@Data
@ToString
public class NotReadMessageDTO {
    private long writer;
    private String username;
    private int cnt;

    public NotReadMessageDTO(long writer, String username, BigInteger cnt) {
        this.writer = writer;
        this.username = username;
        this.cnt = cnt.intValue();
    }
}
