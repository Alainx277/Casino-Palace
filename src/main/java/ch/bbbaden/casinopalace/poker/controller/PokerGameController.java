package ch.bbbaden.casinopalace.poker.controller;

import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.poker.TransitionFuture;
import ch.bbbaden.casinopalace.poker.custom.CardStackView;
import ch.bbbaden.casinopalace.poker.custom.CardView;
import ch.bbbaden.casinopalace.poker.game.Card;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PokerGameController extends Controller implements Initializable {
    @FXML
    private HBox cardsContainer;

    private List<CardView> cardViews = new ArrayList<>();
    @FXML
    private CardStackView cardStack;
    @FXML
    private AnchorPane playField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 5; i++) {
            CardView cardView = new CardView();
            cardView.setFitWidth(100);
            cardView.allowFlipProperty().set(true);
            cardView.setVisible(false);
            cardsContainer.getChildren().add(cardView);
            cardViews.add(cardView);
        }

        //cardViews.get(0).setVisible(true);

       PauseTransition transition = new PauseTransition(Duration.seconds(1));
        transition.setOnFinished(event -> cardStack.shuffle().addOnFinished(event1 -> drawCards()));
        transition.play();
    }

    private void drawCards(){
        drawCard().addOnFinished(event -> {
            drawCards();
        });
    }

    private TransitionFuture drawCard(){
        // Get empty card
        CardView cardTarget = null;
        for (Node node : cardsContainer.getChildren()) {
            if (!node.isVisible() && node instanceof CardView){
                cardTarget = (CardView) node;
                break;
            }
        }

        if (cardTarget == null){
            return new TransitionFuture(null);
        }

        Card card = cardStack.getStack().drawCard();

        // Create temporary card for animation
        CardView temp = new CardView(false, false, false);
        temp.setFitWidth(100);
        temp.setTranslateX(cardStack.getBoundsInParent().getMinX());
        temp.setTranslateY(0);
        playField.getChildren().add(temp);

        // Animate
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), temp);
        Bounds bounds = cardTarget.getParent().localToParent(cardTarget.getBoundsInParent());
        transition.setToX(bounds.getMinX());
        transition.setToY(bounds.getMinY());
        final CardView finalCardTarget = cardTarget;
        transition.setOnFinished(event -> {
            finalCardTarget.setVisible(true);
            finalCardTarget.setCard(card);
            finalCardTarget.flip();
            playField.getChildren().remove(temp);
        });
        transition.play();

        return new TransitionFuture(transition);
    }

    private void flipCard(CardView cardView){
        cardView.flip().addOnFinished(event -> {
            int index = cardViews.indexOf(cardView) + 1;
            if (index < cardViews.size()){
                flipCard(cardViews.get(index));
            }
        });
    }
}
