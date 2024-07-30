package sudapapa.java.boardserver.service;

import org.apache.ibatis.javassist.NotFoundException;
import sudapapa.java.boardserver.domain.UserDTO;
import sudapapa.java.boardserver.exception.InvalidPasswordException;
import sudapapa.java.boardserver.exception.NotFoundUserException;
import sudapapa.java.codepack.security.Sha256;

public class UserServiceExtension {
    public  static  boolean isAdmin(UserService source, String id) {
        UserDTO user = source.getUserInfo(id);
        if (user == null)
            return  false;

        return user.isAdmin();
    }

    public  static  UserDTO login(UserService source, String id, String password) {
        UserDTO user = source.getUserInfo(id);
        if(user == null)
            throw new NotFoundUserException("Not found user : {%s}".formatted(id));

        String encryptedPassword = Sha256.encrypt(password, user.getSalt());
        if(encryptedPassword.equals(user.getPassword()))
            return user;
        else
            throw  new InvalidPasswordException("Invalid password {%s}".formatted(password));
    }

    public  static  String getToken(UserDTO user) {
        return "%s_%s".formatted(user.getUserNo(), user.getUserId());
    }
}
