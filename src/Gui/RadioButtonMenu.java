package Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RadioButtonMenu<E> extends JPanel {
    private final ButtonGroup buttonGroup=new ButtonGroup();
    public RadioButtonMenu() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }
    public void Add(E value, boolean state) {
        JRadioButton rb = new JRadioButton(value.toString(),state);
        rb.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonGroup.add(rb);
        add(rb);
    }
    public void AddList(List<E> list, E selected) {
        for (E e:list) {
            Add(e,e==selected);
        }
    }
    public E Get() {
        return buttonGroup.getSelection().
    }
}
