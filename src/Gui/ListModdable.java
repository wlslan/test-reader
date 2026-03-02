package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ListModdable<T> extends JList<T> {
    private JButton buttonModify,buttonAdd,buttonRemove;
    private DefaultListModel<T> listModel = new DefaultListModel<T>();

    public abstract T Default();
    public abstract void Modify(T obj);

    public ListModdable() {
        super();
        buttonModify=new JButton("Rediģēt");
        buttonAdd=new JButton("Pievienot");
        buttonRemove=new JButton("Izdzēst");
        buttonModify.addActionListener(_ -> {
            ListModdable outer=ListModdable.this;
            if (outer.getSelectedIndex()!=-1) {
                Modify((T) outer.listModel.get(outer.getSelectedIndex()));
            }
        });
        buttonAdd.addActionListener(_ -> {
            ListModdable outer=ListModdable.this;
            outer.listModel.addElement(Default());
        });
        buttonRemove.addActionListener(_ -> {
            ListModdable outer=ListModdable.this;
            if (outer.getSelectedIndex()!=-1) {
                outer.remove(outer.getSelectedIndex());
            }
        });
    }
}
