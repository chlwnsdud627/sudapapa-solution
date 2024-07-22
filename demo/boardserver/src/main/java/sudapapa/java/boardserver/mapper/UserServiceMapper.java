package sudapapa.java.boardserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sudapapa.java.boardserver.domain.UserDTO;

// For myBatis
@Mapper
public interface UserServiceMapper {

    public  int register(UserDTO userDTO);
    public  int delete(String id);
    public  UserDTO findUser(@Param("id") String id);
    public  int updatePassword(@Param("id") String id, @Param("password") String newPassword);

    // 아래와 같이 쿼리를 직접 사용하는 것도 가능
    //@Select("SELECT COUNT(id) FROM \"member.user\" WHERE id=#{id}")
    //public int checkIdCount(@Param("id") String id);
}
