package ch.bbbaden.casinopalace.common;

import ch.bbbaden.casinopalace.common.states.State;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

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
        switchStage(state.getURL());
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

    public void switchStage(URL url) throws IOException {
        Stage oldStage = sceneCreator.getCurrentStage();
        sceneCreator.createScene(url, loader -> loader.setControllerFactory(param -> {
            Object controller;
            try {
                controller = param.getConstructor().newInstance();
            } catch (ReflectiveOperationException ex) {
                throw new RuntimeException(ex);
            }
            if (controller instanceof Controller) {
                ((Controller) controller).setStateManager(this);
            }
            return controller;
        }));
        if (oldStage != null){
            oldStage.close();
        }
    }
}
