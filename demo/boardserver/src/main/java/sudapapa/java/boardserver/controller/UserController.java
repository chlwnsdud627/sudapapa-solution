//package sudapapa.java.boardserver.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import sudapapa.java.boardserver.dto.UserDTO;
//import sudapapa.java.boardserver.service.impl.UserServiceMybatisImpl;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    private  final UserServiceMybatisImpl userService;
//
//    @Autowired
//    public UserController(UserServiceMybatisImpl userService) {
//        this.userService = userService;
//    }
//
//    // 가입
//    @PostMapping("sign-up")
//    public  void signup(@RequestBody UserDTO userDTO) {
//
//    }
//
//}
