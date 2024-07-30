package sudapapa.java.boardserver.domain.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NonNull
    public String userId;
    @NonNull
    public String password;
}
