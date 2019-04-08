package ch.bbbaden.casinopalace.common;

import ch.bbbaden.casinopalace.common.states.State;
import javafx.stage.Stage;

import java.io.IOException;

public class StateManager {
    private State state;
    private Casino casino;
    private SceneCreator sceneCreator;

    public StateManager(Casino casino, SceneCreator sceneCreator) {
        this.casino = casino;
        this.sceneCreator = sceneCreator;
    }

    public void transition(State state) throws IOException {
        this.state = state;
        Stage oldStage = sceneCreator.getCurrentStage();
        sceneCreator.createScene(state.getURL()).setStateManager(this);
        if (oldStage != null){
            oldStage.close();
        }
    }

    public State getState() {
        return state;
    }

    public Casino getCasino() {
        return casino;
    }

    public SceneCreator getSceneCreator() {
        return sceneCreator;
    }
}
