package sudapapa.java.codepack.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Sha256 {
    private static final String ENCRYPTION_TYPE = "SHA-256";

    public static String generateRandomSalt() {
        return UUID.randomUUID().toString();
    }

    public static String encrypt(String message) {
        return encrypt(message, null);
    }

    public static String encrypt(String message, String salt) {
        String encryptResult = null;

        MessageDigest sh;
        try {
            sh = MessageDigest.getInstance(ENCRYPTION_TYPE);
            sh.update((message + salt).getBytes());
            byte[] byteData = sh.digest();
            StringBuilder sb = new StringBuilder();
            for (byte byteDatum : byteData) {
                sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }
            encryptResult = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("failed to encrypt", e);
        }
        return encryptResult;
    }

}
