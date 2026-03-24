package Gui;

import Data.TestFormat;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static Data.TestFormat.testFormats;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

public class ComboBoxFormat extends JPanel {
    JPanel BoxPanel;
    JComboBox<Object> comboBox;
    boolean AllowCreate;
    public ComboBoxFormat(boolean allowCreate) {
        AllowCreate=allowCreate;
        BoxPanel=new JPanel();
        add(BoxPanel);
        LinkedList<Object> testFormatsCopy= new LinkedList<>();
        testFormats.forEach(testFormatsCopy::addLast);
        if (allowCreate) {
            testFormatsCopy.addFirst(new Object());
        }
        comboBox=new JComboBox<>(testFormatsCopy.toArray());
        add(comboBox);
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                value = (value instanceof TestFormat) ? ((TestFormat) value).Name : "Izveidot jaunu...";
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        JButton deleteButton = new JButton("Dzēst");
        add(deleteButton);
        deleteButton.addActionListener(e -> {
            Object value = comboBox.getSelectedItem();
            if (!(value instanceof TestFormat)) {
                return;
            }
            int response = JOptionPane.showConfirmDialog(MainFrame.mainFrame,"Tiešām izdzēst?","Izdzēst formātu",OK_CANCEL_OPTION);
            if (!Utils.AcceptedDialog(response)) {
                return;
            }
            Data.TestFormat.testFormats.remove((TestFormat) value);
            resyncComboBox();
        });
        boolean deletable = comboBox.getSelectedItem() instanceof TestFormat;
        deleteButton.setEnabled(deletable);
        comboBox.addItemListener(e -> {
            boolean deletable1 = comboBox.getSelectedItem() instanceof TestFormat;
            deleteButton.setEnabled(deletable1);
        });
    }
    private JComboBox<Object> resyncComboBox() {
        BoxPanel.remove(comboBox);
        LinkedList<Object> testFormatsCopy= new LinkedList<>();
        testFormats.forEach(testFormatsCopy::addLast);
        if (AllowCreate) {
            testFormatsCopy.addFirst(new Object());
        }
        comboBox=new JComboBox<>(testFormatsCopy.toArray());
        BoxPanel.add(comboBox);
        return comboBox;
    }
}
