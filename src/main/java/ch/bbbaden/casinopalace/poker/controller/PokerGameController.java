package ch.bbbaden.casinopalace.poker.controller;

import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.poker.PokerCasinoAdapter;
import ch.bbbaden.casinopalace.poker.TransitionFuture;
import ch.bbbaden.casinopalace.poker.custom.CardStackView;
import ch.bbbaden.casinopalace.poker.custom.CardView;
import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.Poker;
import ch.bbbaden.casinopalace.poker.game.PokerBet;
import ch.bbbaden.casinopalace.poker.game.hand.Hand;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.util.Callback;
import javafx.util.Duration;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;
import java.util.stream.IntStream;

public class PokerGameController extends Controller implements Initializable {

    @FXML
    private Label balanceLabel;
    @FXML
    private Label balanceChangeLabel;
    @FXML
    private GridPane possibleHandsPane;
    @FXML
    private HBox cardsContainer;
    @FXML
    private CardStackView cardStack;
    @FXML
    private AnchorPane playField;
    @FXML
    private ComboBox<Double> chipValueBox;
    @FXML
    private Label numChipsLabel;
    @FXML
    private Label betValueLabel;
    @FXML
    private Button cardButton;
    @FXML
    private Button increaseBetButton;
    @FXML
    private Button maxBetButton;

    private List<CardView> cardViews = new ArrayList<>();

    private PokerBet bet = new PokerBet(0, 1);
    private Poker poker = new Poker();
    private SimpleIntegerProperty blockingAnimations = new SimpleIntegerProperty(this, "blockingAnimations", 0);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PokerCasinoAdapter adapter = new PokerCasinoAdapter(poker, getStateManager().getCasino(), param -> bet);
        balanceLabel.setText(getStateManager().getCasino().getCurrentUser().getChips().toPlainString());

        // Monitor balance changes
        adapter.onBalanceChanged().addHandler(arg -> {
            boolean positive = arg.getDifference().compareTo(BigDecimal.ZERO) > 0;
            balanceChangeLabel.setText((positive ? "+" : "") + arg.getDifference().toPlainString());
            balanceChangeLabel.getStyleClass().removeAll("success", "error");
            balanceChangeLabel.getStyleClass().add(positive ? "success" : "error");
            balanceChangeLabel.setOpacity(1);
            PauseTransition pause = new PauseTransition(positive ? Duration.seconds(5) : Duration.seconds(2));
            pause.setOnFinished(event -> {
                FadeTransition fade = new FadeTransition(Duration.millis(500), balanceChangeLabel);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.play();
            });
            pause.play();


            balanceLabel.setText(arg.getNewValue().toPlainString());
        });

        cardStack.setStack(poker.getStack());

        poker.getStateMachine().addTransitionListener(x -> refreshUi());

        // Initialize combobox with possible chip values
        ArrayList<Double> col = new ArrayList<>();
        for (double chipValue : PokerBet.CHIP_VALUES) {
            col.add(chipValue);
        }
        chipValueBox.setItems(FXCollections.observableArrayList(col));
        chipValueBox.getSelectionModel().select(0);

        // Make sure the value is properly displayed (no unneeded zeroes)
        Callback<ListView<Double>, ListCell<Double>> factory = param -> new ListCell<Double>() {
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
                if (!s.contains(".")) {
                    return s;
                }

                int index;
                for (index = s.length() - 1; index > 0; index--) {
                    if (s.charAt(index) == '0') {
                        continue;
                    } else if (s.charAt(index) == '.') {
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

        // Generate invisible placeholder cards
        for (int i = 0; i < poker.getNumberOfCards(); i++) {
            CardView cardView = new CardView();
            cardView.setFitWidth(100);
            cardView.allowFlipProperty().set(true);
            cardView.setVisible(false);
            cardsContainer.getChildren().add(cardView);
            cardViews.add(cardView);
        }

        // Make sure to update ui if blocking animations are freed or added
        blockingAnimations.addListener((observable, oldValue, newValue) -> {
            if (oldValue.intValue() == 0 && newValue.intValue() != 0) {
                refreshUi();

            } else if (oldValue.intValue() != 0 && newValue.intValue() == 0) {
                refreshUi();
                refreshResult();
            }
        });

        // Add possible hands
        for (Hand hand : poker.getPossibleHands()) {
            ObservableList<RowConstraints> constraints = possibleHandsPane.getRowConstraints();
            RowConstraints constraint = new RowConstraints(10, 17, 20);
            constraints.add(constraint);


            int rowIndex = constraints.size() - 1;
            possibleHandsPane.add(new Label(hand.getName()), 0, rowIndex);
            Label valueLabel = new Label("1:" + hand.getValue());
            valueLabel.setAlignment(Pos.CENTER_RIGHT);
            valueLabel.setMaxWidth(Double.MAX_VALUE);
            valueLabel.setPadding(new Insets(0, 5, 0, 0));
            possibleHandsPane.add(valueLabel, 1, rowIndex);
        }
    }


    private void drawCards(int... indices) {
        if (indices.length == 0) {
            return;
        }
        drawCard(poker.getCards().get(indices[0]), indices[0]).addOnFinished(event -> drawCards(Arrays.copyOfRange(indices, 1, indices.length)));
    }

    private TransitionFuture drawCard(Card card) {
        return drawCard(card, -1);
    }

    private TransitionFuture drawCard(Card card, int index) {
        blockingAnimations.set(blockingAnimations.get() + 1);

        // Get empty card
        CardView cardTarget = null;
        if (index >= 0) {
            cardTarget = ((CardView) cardsContainer.getChildren().get(index));
        } else {
            for (Node node : cardsContainer.getChildren()) {
                if (!node.isVisible() && node instanceof CardView) {
                    cardTarget = (CardView) node;
                    break;
                }
            }
        }


        if (cardTarget == null) {
            return new TransitionFuture(null);
        }

        // Create temporary card for animation
        CardView temp = new CardView(false, false, false);
        temp.setFitWidth(100);
        temp.setTranslateX(cardStack.getBoundsInParent().getMinX());
        temp.setTranslateY(0);
        playField.getChildren().add(temp);

        // Animate
        TranslateTransition transition = new TranslateTransition(Duration.millis(400), temp);
        Bounds bounds = cardTarget.getParent().localToParent(cardTarget.getBoundsInParent());
        transition.setToX(bounds.getMinX());
        transition.setToY(bounds.getMinY());
        final CardView finalCardTarget = cardTarget;
        transition.setOnFinished(event -> {
            finalCardTarget.setVisible(true);
            finalCardTarget.setCard(card);
            finalCardTarget.flip().addOnFinished(x -> blockingAnimations.set(blockingAnimations.get() - 1));
            playField.getChildren().remove(temp);
        });
        transition.play();

        return new TransitionFuture(transition);
    }

    private TransitionFuture dumpCard(CardView view) {
        blockingAnimations.set(blockingAnimations.get() + 1);

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
        transition.setOnFinished(x -> {
            playField.getChildren().remove(temp);
            blockingAnimations.set(blockingAnimations.get() - 1);
        });
        transition.play();
        return new TransitionFuture(transition);
    }

    @FXML
    private void handleIncreaseBet(ActionEvent actionEvent) {
        if (poker.canBet()) {
            bet = new PokerBet(bet.getChipIndex(), (bet.getNumberOfChips()) % (PokerBet.MAX_CHIPS) + 1);
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

    private void refreshUi() {
        cardButton.setDisable(false);

        refreshBet();
        refreshCardButton();
        refreshCards();
    }

    private void refreshCards() {
        if (blockingAnimations.get() > 0 && poker.canHoldCards()) {
            cardViews.forEach(x -> x.allowFlipProperty().set(false));
        } else {
            cardViews.forEach(x -> x.allowFlipProperty().set(true));
        }
    }

    private void refreshCardButton() {
        if (blockingAnimations.get() > 0) {
            cardButton.setDisable(true);
        } else if (poker.canDrawCards()) {
            cardButton.setText("Draw");
        } else if (poker.canHoldCards()) {
            cardButton.setText("Hold");
        } else if (poker.isEnd()) {
            cardButton.setText("Neue Runde");
        }
    }

    private void refreshBet() {
        boolean canBet = poker.canBet();
        chipValueBox.setDisable(!canBet);


        increaseBetButton.setDisable(!canBet);
        maxBetButton.setDisable(!canBet);

        numChipsLabel.setText(String.valueOf(bet.getNumberOfChips()));
        chipValueBox.getSelectionModel().select(bet.getChipIndex());
        betValueLabel.setText(String.valueOf(bet.getValue()));

        boolean hasMoney = bet.getValue() <= getStateManager().getCasino().getCurrentUser().getChips().doubleValue();
        if (poker.canBet()){
            cardButton.setDisable(!hasMoney);
        }
        if (hasMoney) {
            betValueLabel.getStyleClass().removeAll("error");
        } else {
            betValueLabel.getStyleClass().add("error");
        }
    }

    private void refreshResult() {
        Optional<Hand> currentHand = poker.getCurrentHand();
        // Remove all highlight
        possibleHandsPane.getChildren().forEach(x -> x.getStyleClass().removeAll("highlight"));
        // Highlight hand in list
        if (currentHand.isPresent()) {
            Hand hand = currentHand.get();
            Node handLabel = possibleHandsPane.getChildren().filtered(x -> x instanceof Label && ((Label) x).getText().equals(hand.getName())).get(0);
            Integer rowIndex = GridPane.getRowIndex(handLabel);
            possibleHandsPane.getChildren().filtered(x -> GridPane.getRowIndex(x).equals(rowIndex)).forEach(x -> x.getStyleClass().add("highlight"));
        }

    }


    @FXML
    private void handleCardButton(ActionEvent actionEvent) {
        if (poker.canDrawCards()) {
            poker.drawCards();
            blockingAnimations.set(blockingAnimations.get() + 1);
            cardStack.shuffle().addOnFinished(event1 -> {
                drawCards(IntStream.range(0, poker.getCards().size()).toArray());
                blockingAnimations.set(blockingAnimations.get() - 1);
            });
        } else if (poker.canHoldCards()) {
            Card[] heldCards = cardViews.stream().filter(CardView::isFacingUp).map(CardView::getCard).toArray(Card[]::new);
            poker.holdCards(heldCards);
            EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
                boolean called = false;

                @Override
                public void handle(ActionEvent event) {
                    if (!called) {
                        called = true;
                        blockingAnimations.set(blockingAnimations.get() + 1);
                        drawCards(IntStream.range(0, cardViews.size()).filter(x -> !cardViews.get(x).isVisible()).toArray());
                        blockingAnimations.addListener(new ChangeListener<Number>() {
                            @Override
                            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                if (newValue.intValue() == 0) {
                                    refreshResult();
                                    blockingAnimations.removeListener(this);
                                }
                            }
                        });
                        blockingAnimations.set(blockingAnimations.get() - 1);
                    }
                }
            };
            if (heldCards.length < poker.getNumberOfCards()) {
                cardViews.stream().filter(x -> !x.isFacingUp()).map(this::dumpCard).forEach(x -> x.addOnFinished(handler));
            } else {
                handler.handle(null);
            }
        } else if (poker.isEnd()) {
            poker.reset();
            refreshResult();
            cardViews.forEach(this::dumpCard);
        }
    }
}
