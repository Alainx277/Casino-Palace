package ch.bbbaden.casinopalace.poker.game;

public enum CardSuit {
    Diamonds, Spades, Hearts, Clubs;

    public String toShortString(){
        switch (this) {

            case Diamonds:
                return "D";
            case Spades:
                return "S";
            case Hearts:
                return "H";
            case Clubs:
                return "C";
        }
        return "?";
    }
}
