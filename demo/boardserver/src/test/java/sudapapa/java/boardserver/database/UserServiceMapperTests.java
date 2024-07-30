package sudapapa.java.boardserver.database;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.Ordered;
import sudapapa.java.boardserver.domain.UserDTO;
import sudapapa.java.boardserver.mapper.UserServiceMapper;
import sudapapa.java.codepack.security.Sha256;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UserServiceMapperTests: Mybatis UserServiceMapper Test")
public class UserServiceMapperTests {

    private static  UserDTO testUser;
    @Autowired
    private UserServiceMapper mapper;

    @BeforeAll
    public static void setTestId() {
        testUser = new UserDTO();
        testUser.setUserId("__test_" + Long.toString(System.currentTimeMillis()));
        testUser.setSalt(Sha256.generateRandomSalt());
        testUser.setPassword(Sha256.encrypt("0000", testUser.getSalt()));
        testUser.setAdmin(true);
    }

    @Order(1)
    @Timeout(10)
    @DisplayName("아이디 등록 테스트")
    @Test
    public void register() {
        // 영향을 받는 행의 갯수가 Return 된다.
        int changedRowCount = mapper.register(testUser);
        Assertions.assertEquals(changedRowCount, 1);
    }

    // 쿼리문 테스트를 위해..
//    @Order(2)
//    @DisplayName("아이디 카운트 체크")
//    @Test
//    public void checkIdCount() {
//        int result = mapper.checkIdCount(testId);
//        Assertions.assertEquals(result, 1);
//    }

    @Order(2)
    @DisplayName("Id 검색 테스트")
    @Test
    public void findUser() {
        UserDTO user = mapper.findUser(testUser.getUserId());
        Assertions.assertNotNull(user);
        Assertions.assertEquals(testUser.getUserId(), user.getUserId());
        Assertions.assertEquals(testUser.getPassword(), user.getPassword());
        Assertions.assertEquals(testUser.getSalt(), user.getSalt());
        Assertions.assertEquals(testUser.isAdmin(), user.isAdmin());

        // Case : Not found
        user = mapper.findUser("__________");
        Assertions.assertNull(user);
    }

    @Order(3)
    @DisplayName("암호 업데이트 테스트")
    @Test
    public void updatePassword() {
        String newPassword = Sha256.encrypt("1234", testUser.getSalt());
        int result = mapper.updatePassword(testUser.getUserId(), newPassword);

        UserDTO user = mapper.findUser(testUser.getUserId());
        Assertions.assertEquals(user.getPassword(), newPassword);
    }

    // Last order
    @Order(Ordered.LOWEST_PRECEDENCE)
    @DisplayName("삭제 테스트")
    @Test
    public void delete() {
        int changedRowCount = mapper.delete(testUser.getUserId());
        Assertions.assertEquals(changedRowCount, 1);
    }
}
