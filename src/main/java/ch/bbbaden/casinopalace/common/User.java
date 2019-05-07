package ch.bbbaden.casinopalace.common;

import java.math.BigDecimal;
import java.util.Objects;

public class User {
    private final String username;
    private final String hash;
    private BigDecimal chips;
    private final boolean admin;

    public User(String username, String hash, BigDecimal chips) {
        this(username, hash, chips, false);
    }

    public User(String username, String hash, BigDecimal chips, boolean admin) {
        this.username = username;
        this.hash = hash;
        this.chips = chips;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public String getHash() {
        return hash;
    }

    public BigDecimal getChips() {
        return chips;
    }

    public void setChips(BigDecimal chips) {
        this.chips = chips;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username);
    }
}
