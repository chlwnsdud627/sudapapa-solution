package sudapapa.java.boardserver.domain.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class UpdatePasswordRequest {
    @NonNull
    private String newPassword;
}
