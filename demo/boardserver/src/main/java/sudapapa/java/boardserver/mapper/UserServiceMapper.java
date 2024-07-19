package sudapapa.java.boardserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sudapapa.java.boardserver.dto.UserDTO;

// For myBatis
@Mapper
public interface UserServiceMapper {

    public  int register(UserDTO userDTO);
    public  int delete(String id);
    // public  UserDTO findUser(@Param("id") String id);

    // 아래와 같이 쿼리를 직접 사용하는 것도 가능
    @Select("SELECT COUNT(id) FROM \"member.user\" WHERE id=#{id}")
    public  int checkIdCount(@Param("id") String id);
}
