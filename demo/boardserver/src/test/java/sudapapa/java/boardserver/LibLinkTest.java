package sudapapa.java.boardserver;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;
import sudapapa.java.codepack.security.Sha256;

@TestComponent
class LibLinkTest {
    @DisplayName("CodePack 링크")
    @Test
    void linkCodePack () {
        Assertions.assertNotNull(Sha256.generateRandomSalt());
    }

}
