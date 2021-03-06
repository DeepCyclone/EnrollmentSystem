package by.epamtc.enrollmentsystem.service.util;

import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
/*
 * An utility that generates and validates encoded password with BCrypt
 */
public class PasswordCodec {

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
