package ch.bbbaden.casinopalace.common;

public class User {
    private String username;
    private String hash;
    private int chips;

    public User(String username, String hash, int chips) {
        this.username = username;
        this.hash = hash;
        this.chips = chips;
    }

    public String getUsername() {
        return username;
    }

    public String getHash() {
        return hash;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }
}
