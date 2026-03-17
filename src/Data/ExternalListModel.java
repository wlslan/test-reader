package Data;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.List;

public class ExternalListModel<E> extends DefaultListModel<E> {
    private List<E> synced;
    private boolean updating;

    private boolean IsUnsynced() {
        return synced == null || updating;
    }
    public ExternalListModel(List<E> list) {
        addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                if (IsUnsynced()) {
                    return;
                }
                for (int i = e.getIndex0(); i <= e.getIndex1(); i++) {
                    synced.add(i, get(i));
                }
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
                if (IsUnsynced()) {
                    return;
                }
                synced.subList(e.getIndex0(), e.getIndex1() + 1).clear();
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                ResyncList();
            }
        });
        ChangeList(list);
    }
    public void ChangeList(List<E> synced) {
        this.synced=synced;
        if (synced==null) {
            return;
        }
        updating =true;
        clear();
        for (E e : this.synced) {
            addElement(e);
        }
        updating =false;
    }
    public void ResyncList() {
        ChangeList(synced);
    }
    public List<E> GetList() {
        return synced;
    }
}
