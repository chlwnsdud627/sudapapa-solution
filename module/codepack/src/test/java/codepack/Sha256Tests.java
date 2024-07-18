package codepack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sudapapa.java.codepack.security.Sha256;

public class Sha256Tests {

    @DisplayName("Salt 제작 테스트")
    @Test
    public void generateSalt() {
        String salt = Sha256.generateRandomSalt();
        Assertions.assertNotNull(salt);
        Assertions.assertFalse(salt.isEmpty() || salt.isBlank());
    }

    @DisplayName("SHA-256 암호화 테스트")
    @Test
    public  void encrypt() {
        String salt = Sha256.generateRandomSalt();
        String password = "이것은 암호입니다";
        Assertions.assertNotNull(Sha256.encrypt(password));
        Assertions.assertNotNull(Sha256.encrypt(password, salt));
    }
}
