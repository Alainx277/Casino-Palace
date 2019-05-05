package ch.bbbaden.casinopalace.poker.controller;

import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.poker.TransitionFuture;
import ch.bbbaden.casinopalace.poker.custom.CardStackView;
import ch.bbbaden.casinopalace.poker.custom.CardView;
import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.Poker;
import ch.bbbaden.casinopalace.poker.game.PokerBet;
import ch.bbbaden.casinopalace.poker.game.hand.Hand;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class PokerGameController extends Controller implements Initializable {
    @FXML
    private HBox cardsContainer;

    private List<CardView> cardViews = new ArrayList<>();
    @FXML
    private CardStackView cardStack;
    @FXML
    private AnchorPane playField;
    @FXML
    private ComboBox<Double> chipValueBox;
    @FXML
    private Label numChipsLabel;

    private PokerBet bet = new PokerBet(0, 1);
    private Poker poker = new Poker();
    private boolean cardsAnimating = false;

    @FXML
    private Label betValueLabel;
    @FXML
    private Button cardButton;
    @FXML
    private Button increaseBetButton;
    @FXML
    private Button maxBetButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cardStack.setStack(poker.getStack());

        poker.getStateMachine().addTransitionListener(x -> refreshUi());

        ArrayList<Double> col = new ArrayList<>();
        for (double chipValue : PokerBet.CHIP_VALUES) {
            col.add(chipValue);
        }
        chipValueBox.setItems(FXCollections.observableArrayList(col));
        chipValueBox.getSelectionModel().select(0);

        Callback<ListView<Double>, ListCell<Double>> factory = param -> new ListCell<Double>(){
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(this.removeTrailingZeroes(removeTrailingZeroes(item.toString())));
                    setAlignment(Pos.CENTER_RIGHT);
                    setPadding(new Insets(3, 3, 3, 0));
                }
            }

            String removeTrailingZeroes(String s) {
                if (!s.contains(".")){
                    return s;
                }

                int index;
                for (index = s.length() - 1; index > 0; index--) {
                    if (s.charAt(index) == '0') {
                        continue;
                    }
                    else if (s.charAt(index) == '.'){
                        index--;
                        break;
                    }
                    break;
                }
                return s.substring(0, index + 1);
            }
        };
        chipValueBox.setButtonCell(factory.call(null));
        chipValueBox.setCellFactory(factory);

        for (int i = 0; i < poker.getNumberOfCards(); i++) {
            CardView cardView = new CardView();
            cardView.setFitWidth(100);
            cardView.allowFlipProperty().set(true);
            cardView.setVisible(false);
            cardsContainer.getChildren().add(cardView);
            cardViews.add(cardView);
        }
    }



    private void drawCards(int... indices){
        if (indices.length == 0){
            cardsAnimating = false;
            refreshUi();
            return;
        }
        drawCard(poker.getCards().get(indices[0])).addOnFinished(event -> drawCards(Arrays.copyOfRange(indices, 1, indices.length)));
    }

    private TransitionFuture drawCard(Card card) {
        return drawCard(card, -1);
    }

    private TransitionFuture drawCard(Card card, int index){
        // Get empty card
        CardView cardTarget = null;
        if (index >= 0){
            cardTarget = ((CardView) cardsContainer.getChildren().get(index));
        }
        else{
            for (Node node : cardsContainer.getChildren()) {
                if (!node.isVisible() && node instanceof CardView){
                    cardTarget = (CardView) node;
                    break;
                }
            }
        }


        if (cardTarget == null){
            return new TransitionFuture(null);
        }

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

    private TransitionFuture dumpCard(CardView view){
        // Hide original view
        view.setVisible(false);
        view.facingUpProperty().set(false);

        // Create temporary view for animation
        CardView temp = new CardView(false, false, false);
        temp.setFitWidth(100);
        temp.setTranslateX(view.localToScene(view.getBoundsInLocal()).getMinX());
        temp.setTranslateY(view.localToScene(view.getBoundsInLocal()).getMinY());
        playField.getChildren().add(temp);

        TranslateTransition transition = new TranslateTransition(Duration.millis(500), temp);
        Bounds bounds = temp.getParent().localToParent(temp.getBoundsInParent());
        transition.setByY(-bounds.getMaxY());
        transition.setOnFinished(x -> playField.getChildren().remove(temp));
        transition.play();
        return new TransitionFuture(transition);
    }

    @FXML
    private void handleIncreaseBet(ActionEvent actionEvent) {
        if (poker.canBet()) {
            bet = new PokerBet(bet.getChipIndex(), (bet.getNumberOfChips()) % (PokerBet.MAX_CHIPS ) + 1);
        }
        refreshBet();
    }

    @FXML
    private void handleMaxBet(ActionEvent actionEvent) {
        if (poker.canBet()) {
            bet = new PokerBet(bet.getChipIndex(), PokerBet.MAX_CHIPS);
        }
        refreshBet();
    }

    @FXML
    private void handleChipSelectionChanged(ActionEvent actionEvent) {
        if (poker.canBet()) {
            bet = PokerBet.fromValue(chipValueBox.getValue(), bet.getNumberOfChips());
        }
        refreshBet();
    }

    private void refreshUi(){
        cardButton.setDisable(false);

        refreshBet();
        refreshCardButton();
    }

    private void refreshCardButton(){
        if (cardsAnimating){
            cardButton.setDisable(true);
        }
        else if (poker.canDrawCards()){
            cardButton.setText("Draw");
        }
        else if (poker.canHoldCards()){
            cardButton.setText("Hold");
        }
        else if (poker.isEnd()){
            cardButton.setText("Neue Runde");
        }
    }

    private void refreshBet(){
        boolean canBet = poker.canBet();
        if (!canBet){
            chipValueBox.setDisable(true);
        }

        increaseBetButton.setDisable(!canBet);
        maxBetButton.setDisable(!canBet);

        numChipsLabel.setText(String.valueOf(bet.getNumberOfChips()));
        chipValueBox.getSelectionModel().select(bet.getChipIndex());
        betValueLabel.setText(String.valueOf(bet.getValue()));

        boolean hasMoney = bet.getValue() <= getStateManager().getCasino().getCurrentUser().getChips().doubleValue();
        cardButton.setDisable(!hasMoney);
        if (hasMoney){
            betValueLabel.setTextFill(Paint.valueOf("black"));
        } else {
            betValueLabel.setTextFill(Paint.valueOf("red"));
        }
    }


    @FXML
    private void handleCardButton(ActionEvent actionEvent) {
        if (poker.canDrawCards()){
            cardsAnimating = true;
            poker.drawCards();
            cardStack.shuffle().addOnFinished(event1 -> drawCards(IntStream.range(0, poker.getCards().size()).toArray()));
        }
        else if (poker.canHoldCards()){
            cardsAnimating = true;
            Card[] heldCards = cardViews.stream().filter(CardView::isFacingUp).map(CardView::getCard).toArray(Card[]::new);
            poker.holdCards(heldCards);
            System.out.println(poker.getCurrentHand().orElseGet(() -> new Hand("None", 0)).getName());
            EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
                boolean called = false;

                @Override
                public void handle(ActionEvent event) {
                    if (!called) {
                        cardsAnimating = true;
                        called = true;
                        drawCards(IntStream.range(0, cardViews.size()).filter(x -> !cardViews.get(x).isVisible()).toArray());
                    }
                }
            };
            cardViews.stream().filter(x -> !x.isFacingUp()).map(this::dumpCard).forEach(x -> x.addOnFinished(handler));
        }
        else if (poker.isEnd()){
            cardsAnimating = true;
            poker.reset();
            cardViews.forEach(this::dumpCard);
            refreshUi();
        }
    }
}
