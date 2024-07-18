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
public class UserDTO {

    private  String userId; //notNull
    private  String password;
    private  boolean isAdmin;
    private Date createTime;
    private  String salt;

    // TODO : salt 는 자동생성이 되야 한다.
    // TODO : createTime 은 이곳에서 가지고 있으면 안된다.

    // TODO : 유효성을 체크하는 아래 코드는 좋지않아 보인다.. 추후 더 나은 방법을 찾자.
    public static boolean validate(UserDTO source) {

        if(source.userId == null || source.userId.isEmpty() || source.userId.isBlank() || source.userId.length() > 20)
            return false;

        if(source.password == null || source.password.isEmpty())
            return false;

        return  true;
    }
}

