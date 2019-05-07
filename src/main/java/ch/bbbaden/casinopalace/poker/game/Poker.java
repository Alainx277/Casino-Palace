package ch.bbbaden.casinopalace.poker.game;

import ch.bbbaden.casinopalace.poker.EventSource;
import ch.bbbaden.casinopalace.poker.StateMachine;
import ch.bbbaden.casinopalace.poker.game.hand.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Poker {
    private final int numberOfCards;
    private StateMachine<Poker, PokerState> stateMachine = new StateMachine<>(this);
    private ArrayList<Card> cards;
    private CardStack stack = new CardStack();
    private ArrayList<HandParser> handParsers = new ArrayList<>();
    private Boolean won = null;

    public Poker() {
        this(5);
    }

    public Poker(int numberOfCards) {
        this.numberOfCards = numberOfCards;
        cards = new ArrayList<>(numberOfCards);

        handParsers.add(new NaturalRoyalFlush());
        handParsers.add(new FourDeuces());
        handParsers.add(new WildRoyalFlush());
        handParsers.add(new FiveCards());
        handParsers.add(new StraightFlush());
        handParsers.add(new FourCards());
        handParsers.add(new FullHouse());
        handParsers.add(new Flush());
        handParsers.add(new Straight());
        handParsers.add(new Triplet());


        stateMachine.transition(new InitialState());
    }

    public boolean canBet(){
        return stateMachine.getState().canBet(stateMachine);
    }

    public boolean canDrawCards() {
        return stateMachine.getState().canDraw(stateMachine);
    }

    public void drawCards() {
        stateMachine.getState().handleDraw(stateMachine);
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public boolean canHoldCards() {
        return stateMachine.getState().canHold(stateMachine);
    }

    public void holdCards(Card[] heldCards) {
        stateMachine.getState().handleHold(stateMachine, heldCards);
    }

    public CardStack getStack() {
        return stack;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public StateMachine<Poker, PokerState> getStateMachine() {
        return stateMachine;
    }

    /** Gets a list of all possible hands, sorted descending by card value
     */
    public List<Hand> getPossibleHands(){
        ArrayList<Hand> hands = new ArrayList<>(handParsers.size());
        handParsers.forEach(x -> hands.add(x.getPossibleHand()));
        return hands;
    }

    /** Gets the most valued current hand held
     * @return The most valued hand currently held, or empty if no hand was found
     */
    public Optional<Hand> getCurrentHand(){
        Card[] array = cards.toArray(new Card[0]);
        for (HandParser parser : handParsers) {
            Optional<Hand> optionalHand = parser.parse(array);
            if (optionalHand.isPresent()){
                return optionalHand;
            }
        }

        return Optional.empty();
    }

    public boolean isWon(){
        return won != null && won;
    }

    public boolean isLost(){
        return won != null && !won;
    }

    public boolean isEnd(){
        return won != null;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }

    public void reset(){
        getStateMachine().transition(new InitialState());
    }
}
