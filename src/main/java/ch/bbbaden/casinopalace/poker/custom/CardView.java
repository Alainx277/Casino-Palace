package ch.bbbaden.casinopalace.poker.custom;

import ch.bbbaden.casinopalace.poker.TransitionFuture;
import ch.bbbaden.casinopalace.poker.game.Card;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.NamedArg;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class CardView extends ImageView {
    private static Media flipSound;
    private static Image backgroundImage;

    private Image cachedImage;
    private TransitionFuture currentFlip;

    private BooleanProperty sound;
    private BooleanProperty facingUp;
    private BooleanProperty allowFlip;
    private ObjectProperty<Card> card = new SimpleObjectProperty<>(Card.random());
    private MediaPlayer flipPlayer;

    public CardView() {
        this(false, false, true);
    }

    public CardView(@NamedArg("allowFlip") boolean allowFlip, @NamedArg("facingUp") boolean facingUp, @NamedArg(value = "sound", defaultValue = "true") boolean sound) {
        setPreserveRatio(true);

        this.allowFlip = new SimpleBooleanProperty(allowFlip);
        this.facingUp = new SimpleBooleanProperty(facingUp);
        this.sound = new SimpleBooleanProperty(sound);

        if (flipSound == null) {
            flipSound = new Media(getClass().getResource("/poker/card_flip.mp3").toString());
        }
        this.flipPlayer = new MediaPlayer(flipSound);

        if (backgroundImage == null) {
            backgroundImage = new Image(getClass().getResource("/images/cards/background.png").toString());
        }

        updateImage(true);
        card.addListener((observable, oldValue, newValue) -> updateImage(true));

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY && allowFlip() && currentFlip == null) {
                flip();
            }
        });

        this.setPreserveRatio(true);
    }

    public TransitionFuture flip() {
        if (currentFlip != null) {
            currentFlip.cancel(true);
        }

        boolean oldState = facingUp.get();

        ScaleTransition shrink = new ScaleTransition(Duration.millis(100), this);
        shrink.setFromX(1);
        shrink.setToX(0.001);
        shrink.setOnFinished(x -> {
            facingUp.set(!oldState);
            updateImage(false);
        });

        ScaleTransition reveal = new ScaleTransition(Duration.millis(100), this);
        reveal.setFromX(0.001);
        reveal.setToX(1);

        SequentialTransition transition = new SequentialTransition(shrink, reveal);
        transition.setOnFinished(event -> {
            if (currentFlip.getTransition().equals(event.getSource())) {
                currentFlip = null;
            }
        });
        transition.play();

        currentFlip = new TransitionFuture(transition) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                boolean canceled = super.cancel(mayInterruptIfRunning);
                if (canceled && facingUp.get() != oldState) {
                    facingUp.set(oldState);
                    updateImage(false);
                }
                return canceled;
            }
        };

        if (hasSound()) {
            flipPlayer.pause();
            flipPlayer.seek(flipPlayer.getStartTime());
            flipPlayer.play();
        }

        return currentFlip;
    }

    public void flipInstantly() {
        if (currentFlip != null) {
            currentFlip.cancel(true);
        }

        facingUp.set(!facingUp.get());
        updateImage(false);
    }


    public boolean hasSound() {
        return sound.get();
    }

    public BooleanProperty soundProperty() {
        return sound;
    }

    public boolean isFacingUp() {
        return facingUp.get();
    }

    public BooleanProperty facingUpProperty() {
        return facingUp;
    }

    private void updateImage(boolean cardChanged) {
        if (cardChanged || cachedImage == null) {
            cachedImage = fetchImage();
        }

        if (isFacingUp()) {
            setImage(cachedImage);
        } else {
            setImage(backgroundImage);
        }
    }

    private Image fetchImage() {
        String loc = "/images/cards/" + card.get().getRank().toShortString() + card.get().getSuit().toShortString() + ".png";
        return new Image(getClass().getResource(loc).toString());
    }

    public boolean allowFlip() {
        return allowFlip.get();
    }

    public BooleanProperty allowFlipProperty() {
        return allowFlip;
    }

    public Card getCard() {
        return card.get();
    }

    public ObjectProperty<Card> cardProperty() {
        return card;
    }

    public void setCard(Card card) {
        this.card.set(card);
    }
}
