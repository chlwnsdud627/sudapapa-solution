package sudapapa.java.boardserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO {
    private  String no;
    private  String id; //notNull
    private  String password;
    private  boolean isAdmin;
    private  Date created;
    private  String salt;
}