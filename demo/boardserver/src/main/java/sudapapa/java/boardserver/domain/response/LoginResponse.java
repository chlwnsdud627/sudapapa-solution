package sudapapa.java.boardserver.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sudapapa.java.boardserver.domain.UserDTO;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginResponse {
    enum  ResultCode {
        SUCCESS, FAIL, DELETED
    }

    @NonNull
    private ResultCode result;
    private UserDTO userDTO;

    private static final LoginResponse FAIL = new LoginResponse(ResultCode.FAIL);
    public static LoginResponse success(UserDTO userDTO) {
        return new LoginResponse(ResultCode.SUCCESS, userDTO);
    }
}
