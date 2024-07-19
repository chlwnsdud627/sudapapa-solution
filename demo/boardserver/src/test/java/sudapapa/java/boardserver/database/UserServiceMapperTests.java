package sudapapa.java.boardserver.database;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sudapapa.java.boardserver.dto.UserDTO;
import sudapapa.java.boardserver.mapper.UserServiceMapper;
import sudapapa.java.codepack.security.Sha256;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UserServiceMapperTests: Mybatis UserServiceMapper Test")
public class UserServiceMapperTests {

    private static String testId;
    private static String testPassword;
    private static String testSalt;
    @Autowired
    private UserServiceMapper mapper;

    @BeforeAll
    public static void setTestId() {
        testId = "__test_" + Long.toString(System.currentTimeMillis());
        testSalt = Sha256.generateRandomSalt();
        testPassword = Sha256.encrypt("0000", testSalt);
    }

    @Order(1)
    @DisplayName("아이디 등록 테스트")
    @Test
    public void register() {
        UserDTO newUser = new UserDTO();
        newUser.setUserId(testId);
        newUser.setPassword(testPassword);
        newUser.setSalt(testSalt);
        newUser.setAdmin(true);
        newUser.setPassword(testPassword);

        // 영향을 받는 행의 갯수가 Return 된다.
        int changedRowCount = mapper.register(newUser);
        Assertions.assertEquals(changedRowCount, 1);
    }

    @Order(2)
    @DisplayName("아이디 카운트 체크")
    @Test
    public void checkIdCount() {
        int result = mapper.checkIdCount(testId);
        Assertions.assertEquals(result, 1);
    }

    @Order(3)
    @DisplayName("삭제 테스트")
    @Test
    public void delete() {
        int changedRowCount = mapper.delete(testId);
        Assertions.assertEquals(changedRowCount, 1);
    }

}
