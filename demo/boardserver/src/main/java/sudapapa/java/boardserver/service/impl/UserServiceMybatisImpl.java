package sudapapa.java.boardserver.service.impl;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sudapapa.java.boardserver.domain.UserDTO;
import sudapapa.java.boardserver.exception.DuplicatedIdException;
import sudapapa.java.boardserver.mapper.UserServiceMapper;
import sudapapa.java.boardserver.service.UserService;

@Service
public class UserServiceMybatisImpl implements UserService {

    private UserServiceMapper userServiceMapper;

    @Autowired
    public UserServiceMybatisImpl(UserServiceMapper userServiceMapper) {
        this.userServiceMapper = userServiceMapper;
    }

    @Override
    public UserDTO getUserInfo(String userId) {
        return this.userServiceMapper.findUser(userId);
    }

    @Override
    public void registerUser(UserDTO userInfo){
        // Validation
        if(isDuplicatedId(userInfo.getUserId()))
            throw new DuplicatedIdException("Already exist id.");
        this.userServiceMapper.register(userInfo);
    }

    @Override
    public void deleteUser(String userId) throws Throwable {
        int result = this.userServiceMapper.delete(userId);
        if(result < 1)
            throw new NotFoundException("Not found user");
    }

    @Override
    public void updatePassword(String userId, String newPassword) throws Throwable {
        int result = this.userServiceMapper.updatePassword(userId, newPassword);
        if(result < 1)
            throw new NotFoundException("Not found user");
    }

    @Override
    public boolean isDuplicatedId(String userId) {
        UserDTO user = getUserInfo(userId);
        return  user != null;
    }

}
