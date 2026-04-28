package Data;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.List;

public class ExternalComboBoxModel<E> extends DefaultComboBoxModel<E> {
    private List<E> synced;
    private boolean updating;
    private int specialOptions;
    private int syncedIndex(int index) {
        return index-specialOptions;
    }
    private boolean IsUnsynced() {
        return synced == null || updating;
    }

    public boolean DeletableIndex(int index) {
        return index>=specialOptions;
    }

    public ExternalComboBoxModel(List<E> list) {
        addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                if (IsUnsynced()) {
                    return;
                }
                for (int i = e.getIndex0(); i <= e.getIndex1(); i++) {
                    synced.add(syncedIndex(i), getElementAt(i));
                }
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
                if (IsUnsynced()) {
                    return;
                }
                synced.subList(syncedIndex(e.getIndex0()), syncedIndex(e.getIndex1() + 1)).clear();
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                if (IsUnsynced()) {
                    return;
                }
                TransferList();
            }
        });
        ChangeList(list);
    }
    public void ChangeList(List<E> synced) {
        this.synced=synced;
        updating =true;
        while (getSize()>specialOptions) {
            removeElementAt(getSize()-1);
        }
        if (synced!=null) {
            for (E e : this.synced) {
                addElement(e);
            }
        }
        updating=false;
    }
    public void ResyncList() {
        ChangeList(synced);
    }
    public void TransferList() {
        if (synced==null) {
            return;
        }
        synced.clear();
        for (int i=specialOptions;i<getSize();i++) {
            synced.add(getElementAt(i));
        }
    }
    public List<E> GetList() {
        return synced;
    }
    public void AddSpecial(E item) {
        updating=true;
        specialOptions++;
        insertElementAt(item,0);
        updating=false;
    }
}