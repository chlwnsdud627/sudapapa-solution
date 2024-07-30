package sudapapa.java.boardserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private  String userNo;
    private  String userId; //notNull
    private  String password;
    private  boolean isAdmin;
    private  String salt;


}

