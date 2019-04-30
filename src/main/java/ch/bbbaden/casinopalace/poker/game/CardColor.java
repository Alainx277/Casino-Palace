package ch.bbbaden.casinopalace.poker.game;

public enum CardColor {
    Diamond, Spade, Heart, Club;

    public String toShortString(){
        switch (this) {

            case Diamond:
                return "D";
            case Spade:
                return "S";
            case Heart:
                return "H";
            case Club:
                return "C";
        }
        return "?";
    }
}
