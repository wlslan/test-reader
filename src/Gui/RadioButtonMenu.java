package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RadioButtonMenu<E> extends JPanel {
    private final ButtonGroup buttonGroup=new ButtonGroup();
    private E selected;
    private Map<E,ButtonModel> buttons = new HashMap<>();
    public RadioButtonMenu() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }
    public void Add(E value, boolean state) {
        System.out.println( value.toString());
        JRadioButton rb = new JRadioButton(value.toString(),state);
        buttons.put(value,rb.getModel());
        rb.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected=value;
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
    public void AddList(E[] list, E selected) {
        for (E e:list) {
            Add(e,e==selected);
        }
    }
    public E Get() {
        return selected;
    }
    public void Set(E selected) {
        buttonGroup.setSelected(buttons.get(selected),true);
    }
}
