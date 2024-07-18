package sudapapa.java.boardserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sudapapa.java.boardserver.dto.UserDTO;

@Mapper
public interface UserServiceMapper {
    public  int register(UserDTO userDTO);

    public  UserDTO findUser(@Param("id") String id);
}
