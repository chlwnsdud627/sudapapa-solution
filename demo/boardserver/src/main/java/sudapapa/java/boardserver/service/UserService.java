package sudapapa.java.boardserver.service;

import sudapapa.java.boardserver.domain.UserDTO;

public interface UserService {

    UserDTO getUserInfo(String userId);
    void registerUser(UserDTO userDTO);
    void deleteUser(String userId)  throws Throwable;
    void updatePassword(String userId, String newPassword) throws Throwable;
    boolean isDuplicatedId(String userId);

}