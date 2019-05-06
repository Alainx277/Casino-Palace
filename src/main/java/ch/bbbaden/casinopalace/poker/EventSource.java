package ch.bbbaden.casinopalace.poker;

import java.util.ArrayList;

public class EventSource<T> {
    private ArrayList<EventHandler<T>> handlers = new ArrayList<>();

    public void addHandler(EventHandler<T> handler){
        handlers.add(handler);
    }

    public boolean removeHandler(Object o) {
        return handlers.remove(o);
    }

    public ArrayList<EventHandler<T>> getHandlers() {
        return handlers;
    }

    public void invoke(T arg){
        for (EventHandler<T> handler : handlers) {
            handler.handle(arg);
        }
    }
}
