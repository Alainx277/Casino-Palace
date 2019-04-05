package ch.bbbaden.casinopalace.common;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserFactory {
    public User createUser(String username, String password){
        String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
        return new User(username, hashpw, 100);
    }
}
