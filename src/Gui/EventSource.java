package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public interface EventSource {
    String actionCommand=null;
    List<ActionListener> listeners = new ArrayList<>();
    default void addListener(ActionListener listener) {
        listeners.add(listener);
    }
    default void fireEvent() {
        ActionEvent e = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,actionCommand);
        for (ActionListener listener : listeners) {
            listener.actionPerformed(e);
        }
    }
}
