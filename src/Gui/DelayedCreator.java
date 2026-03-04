package Gui;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public abstract class DelayedCreator<T>{
    public interface Listener<T> extends EventListener {
        public void objectCreated(T result) ;
    }
    public abstract T Result();

    List<Listener<T>> listeners = new ArrayList<>();
    void addListener(Listener<T> listener) {
        listeners.add(listener);
    }
    void fireEvent() {
        T result = Result();
        for (Listener<T> listener : listeners) {
            listener.objectCreated(result);
        }
        listeners.clear();
    }
}
