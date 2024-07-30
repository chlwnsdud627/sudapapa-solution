package sudapapa.java.boardserver.controller;

import jakarta.servlet.http.HttpSession;
import lombok.experimental.ExtensionMethod;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sudapapa.java.boardserver.aop.LoginCheck;
import sudapapa.java.boardserver.domain.UserDTO;
import sudapapa.java.boardserver.domain.request.LoginRequest;
import sudapapa.java.boardserver.domain.request.UpdatePasswordRequest;
import sudapapa.java.boardserver.domain.response.LoginResponse;
import sudapapa.java.boardserver.exception.DuplicatedIdException;
import sudapapa.java.boardserver.exception.InvalidPasswordException;
import sudapapa.java.boardserver.exception.NotFoundUserException;
import sudapapa.java.boardserver.service.UserServiceExtension;
import sudapapa.java.boardserver.service.impl.UserServiceMybatisImpl;
import sudapapa.java.boardserver.util.SessionUtil;
import sudapapa.java.codepack.security.Sha256;

@RestController
@Log4j2
@RequestMapping("/users")
@ExtensionMethod({UserServiceExtension.class})
public class UserController {

    private  final UserServiceMybatisImpl userService;
    private static final ResponseEntity<LoginResponse> FAIL_RESPONSE = new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);

    @Autowired
    public UserController(UserServiceMybatisImpl userService) {
        this.userService = userService;
    }

    // Response 를 아래와 같이 두면, 무조건 HttpStatus.CREATED 만 반환된다.
    @PostMapping("sign-up")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registerUser(@RequestBody UserDTO userDTO) {
        try {
            userDTO.setSalt(Sha256.generateRandomSalt());
            userDTO.setPassword(Sha256.encrypt(userDTO.getPassword(), userDTO.getSalt()));
            this.userService.registerUser(userDTO);
        }catch (DuplicatedIdException ex) {
            log.error(ex.getMessage());
        }
    }

    // Delete 의 경우, Session 을 받아 처리하는 것이 좀 더 좋은 방법이다!
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteUser(@PathVariable("id") String id) {
        try {
            this.userService.deleteUser(id);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
    }

    @PostMapping("sign-in")
    public HttpStatus login(@RequestBody LoginRequest loginInfo, HttpSession session) {
        String userId = loginInfo.getUserId();
        String password = loginInfo.getPassword();
        try {
            UserDTO user = this.userService.login(userId, password);
            if(user.isAdmin())
                SessionUtil.setLoginAdminId(session, user.getToken());
            else
                SessionUtil.setLoginMemberId(session, user.getToken());
            return HttpStatus.OK;

        } catch (NotFoundUserException | InvalidPasswordException ex) {
            log.error(ex.getMessage());
            return  HttpStatus.BAD_REQUEST;
        }
    }

    @PutMapping("sign-out")
    public void logout(HttpSession session) {
        SessionUtil.clear(session);
    }

    @PatchMapping("password")
    //@LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<LoginResponse> updateUserPassword(String accountId, @RequestBody UpdatePasswordRequest userUpdatePasswordRequest,
                                                            HttpSession session) throws Throwable{
        ResponseEntity<LoginResponse> responseEntity = null;
        String userToken = null;
        String newPassword = userUpdatePasswordRequest.getNewPassword();

        try {
            String id = userToken.split("_")[1];
            userService.updatePassword(id, newPassword);
            ResponseEntity.ok();
        } catch (IllegalArgumentException e) {
            log.error("updatePassword 실패", e);
            responseEntity = FAIL_RESPONSE;
        }
        return responseEntity;
    }



}
