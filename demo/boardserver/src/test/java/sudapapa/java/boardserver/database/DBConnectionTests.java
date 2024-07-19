package sudapapa.java.boardserver.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class DBConnectionTests {

    @Autowired
    private DataSource dataSource;

    @DisplayName("DB 연결 테스트")
    @Test
    public  void ConnectionTest() throws SQLException {
        Connection connector = dataSource.getConnection();
        Assertions.assertTrue(connector.isValid(1000));
        connector.close();
    }
}
