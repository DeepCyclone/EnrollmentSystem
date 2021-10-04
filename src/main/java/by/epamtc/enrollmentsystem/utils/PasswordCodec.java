package by.epamtc.enrollmentsystem.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class PasswordCodec {

    //private static final SecureRandom RANDOM = SecureRandom.getInstanceStrong();
    private static final int HASHING_ROUNDS = 10;

    public static byte[] generateEncodedPassword(String originalPassword){
        byte[] encryptedPassword;
        String salt = BCrypt.gensalt(HASHING_ROUNDS);
        encryptedPassword = BCrypt.hashpw(originalPassword,salt).getBytes(StandardCharsets.UTF_8);
        return encryptedPassword;
    }

    public static boolean validatePassword(String encodedPassword,String plainPassword){
        return BCrypt.checkpw(plainPassword,encodedPassword);
    }
}
