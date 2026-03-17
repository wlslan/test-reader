package Gui;

import Data.ExternalListModel;

import javax.swing.*;
import java.util.List;

public abstract class ListModdable<E> extends JPanel {
    public JButton buttonModify,buttonAdd,buttonRemove;
    public JList<E> list;
    public ExternalListModel<E> model;
    public abstract void Create();
    public abstract void Modify(E obj, int index);
    public abstract void Destroy(E obj, int index);

    public void AddList(E e) {
        if (e!=null) {
            model.addElement(e);
        }
        list.setSelectedIndex(model.size()-1);
    }
    public void RemoveList(int index) {
        if (index!=-1) {
            model.remove(index);
        }
        list.setSelectedIndex(Math.max(index-1,0));
    }
    public void ModifyList(int index) {
        if (index==-1) {
            return;
        }
        E value = model.get(index);
        model.set(index,value);
    }
    public void ClearList() {
        model.clear();
    }
    public E GetSelectedValue() {
        return list.getSelectedValue();
    }

    public ListModdable() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        list=new JList<>(model = new ExternalListModel<>(null));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        buttonModify=new JButton("Rediģēt");
        buttonAdd=new JButton("Pievienot");
        buttonRemove=new JButton("Izdzēst");
        buttonModify.addActionListener(_ -> {
            Modify(GetSelectedValue(),list.getSelectedIndex());
        });
        buttonAdd.addActionListener(_ -> {
            Create();
        });
        buttonRemove.addActionListener(_ -> {
            int index=list.getSelectedIndex();
            if (index!=-1) {
                Destroy(model.get(index),index);
            }
        });
        add(list);
        add(buttonModify);
        add(buttonAdd);
        add(buttonRemove);
    }
}
