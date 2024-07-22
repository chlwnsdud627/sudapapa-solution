package sudapapa.java.boardserver.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sudapapa.java.boardserver.domain.UserDTO;
import sudapapa.java.boardserver.exception.DuplicatedIdException;
import sudapapa.java.boardserver.service.impl.UserServiceMybatisImpl;
import sudapapa.java.codepack.security.Sha256;

@RestController
@Log4j2
@RequestMapping("/users")
public class UserController {

    private  final UserServiceMybatisImpl userService;

    @Autowired
    public UserController(UserServiceMybatisImpl userService) {
        this.userService = userService;
    }

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody UserDTO userDTO) {
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
    public void withdrawal(@PathVariable("id") String id) {
        try {
            this.userService.deleteUser(id);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
    }
}
