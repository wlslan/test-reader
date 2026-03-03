package Gui;

import javax.swing.*;
import java.awt.*;

public abstract class ListModdable<E> extends JPanel {
    private JButton buttonModify,buttonAdd,buttonRemove;
    protected JList<E> list;
    protected DefaultListModel<E> listModel;

    public abstract E Default();
    public abstract void Modify(E obj);

    public ListModdable() {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        list=new JList<>(listModel = new DefaultListModel<>());
        buttonModify=new JButton("Rediģēt");
        buttonAdd=new JButton("Pievienot");
        buttonRemove=new JButton("Izdzēst");
        buttonModify.addActionListener(_ -> {
            if (list.getSelectedIndex()!=-1) {
                Modify(listModel.get(list.getSelectedIndex()));
            }
        });
        buttonAdd.addActionListener(_ -> {
            listModel.addElement(Default());
        });
        buttonRemove.addActionListener(_ -> {
            if (list.getSelectedIndex()!=-1) {
                listModel.remove(list.getSelectedIndex());
            }
        });
        add(list);
        add(buttonModify);
        add(buttonAdd);
        add(buttonRemove);
    }
}
