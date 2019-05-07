package ch.bbbaden.casinopalace.poker.game;

public enum CardRank {
    Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;

    public String toShortString(){
        switch (this) {

            case Two:
                return "2";
            case Three:
                return "3";
            case Four:
                return "4";
            case Five:
                return "5";
            case Six:
                return "6";
            case Seven:
                return "7";
            case Eight:
                return "8";
            case Nine:
                return "9";
            case Ten:
                return "10";
            case Jack:
                return "J";
            case Queen:
                return "Q";
            case King:
                return "K";
            case Ace:
                return "A";
        }

        return "?";
    }
}
