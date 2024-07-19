package sudapapa.java.boardserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import sudapapa.java.codepack.security.Sha256;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private  String userId; //notNull
    private  String password;
    private  boolean isAdmin;
    private  String salt;

    // TODO : 유효성을 체크하는 아래 코드는 좋지않아 보인다.. 추후 더 나은 방법을 찾자.
    public static boolean validate(UserDTO source) {
        if(source.userId == null || source.userId.isEmpty() || source.userId.isBlank() || source.userId.length() > 20)
            return false;
        return source.password != null && !source.password.isEmpty();
    }
}

