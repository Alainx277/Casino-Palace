package ch.bbbaden.casinopalace.poker;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TransitionFuture implements Future<Void> {

    private final Transition transition;
    private final Semaphore lock = new Semaphore(1);
    private boolean finished = false;
    private List<EventHandler<ActionEvent>> onFinishedEvents = new ArrayList<>();

    public TransitionFuture(Transition transition){
        this.transition = transition;

        if (transition == null){
            finished = true;
            return;
        }

        if (transition.getOnFinished() != null){
            onFinishedEvents.add(transition.getOnFinished());
        }

        transition.setOnFinished(x -> {
            onFinishedEvents.forEach(y -> y.handle(x));
            synchronized (lock){
                finished = true;
                lock.notifyAll();
            }
        });
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        transition.stop();
        return true;
    }

    @Override
    public boolean isCancelled() {
        return transition.getStatus() == Animation.Status.STOPPED;
    }

    @Override
    public boolean isDone() {
        return finished;
    }

    @Override
    public Void get() throws InterruptedException, ExecutionException {
        synchronized (lock){
            while (!finished){
                lock.wait();
            }
        }
        return null;
    }

    @Override
    public Void get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        synchronized (lock){
            if (!finished){
                lock.wait(TimeUnit.MILLISECONDS.convert(timeout, unit));
            }
        }
        if (!finished){
            throw new TimeoutException();
        }
        return null;
    }

    public Transition getTransition() {
        return transition;
    }

    public boolean addOnFinished(EventHandler<ActionEvent> actionEventEventHandler) {
        return onFinishedEvents.add(actionEventEventHandler);
    }

    public boolean removeOnFinished(Object o) {
        return onFinishedEvents.remove(o);
    }
}
