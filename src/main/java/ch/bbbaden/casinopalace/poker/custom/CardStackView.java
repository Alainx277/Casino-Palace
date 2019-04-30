package ch.bbbaden.casinopalace.poker.custom;

import ch.bbbaden.casinopalace.poker.TransitionFuture;
import ch.bbbaden.casinopalace.poker.game.CardStack;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardStackView extends StackPane {

    private ObjectProperty<CardStack> stack = new SimpleObjectProperty<>(new CardStack());
    private List<CardView> cards = new ArrayList<CardView>();

    public CardStackView() {
        for (int i = 0; i < 5; i++) {
            CardView card = new CardView();
            card.setFitWidth(100);
            cards.add(card);
            this.getChildren().add(card);
        }
    }

    public TransitionFuture shuffle(){
        MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("/poker/card_shuffle.wav").toString()));
        player.play();

        Random rand = new Random();
        ParallelTransition transition = new ParallelTransition();
        for (int i = 0; i < cards.size(); i++) {
            CardView card = cards.get(i);
            RotateTransition rotate = new RotateTransition(Duration.millis(rand.nextInt(100 * i + 1) + 500), card);
            rotate.setByAngle(360);
            transition.getChildren().add(rotate);
        }
        transition.play();
        return new TransitionFuture(transition);
    }

    public CardStack getStack() {
        return stack.get();
    }

    public ObjectProperty<CardStack> stackProperty() {
        return stack;
    }

    public void setStack(CardStack stack) {
        this.stack.set(stack);
    }
}
