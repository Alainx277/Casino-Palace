package ch.bbbaden.casinopalace.common;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.math.BigDecimal;

public class UserFactory {
    public User createUser(String username, String password){
        String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
        return new User(username, hashpw, new BigDecimal(100));
    }

    public boolean checkPassword(String plaintext, String stored){
        return BCrypt.checkpw(plaintext, stored);
    }
}
