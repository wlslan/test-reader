package Gui;

import Data.ExternalComboBoxModel;
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
    JComboBox<TestFormat> comboBox;
    boolean AllowCreate;
    ExternalComboBoxModel<TestFormat> comboBoxModel;
    public ComboBoxFormat(boolean allowCreate) {
        AllowCreate=allowCreate;
        BoxPanel=new JPanel();
        add(BoxPanel);
        comboBox=new JComboBox<>(comboBoxModel= new ExternalComboBoxModel<>(testFormats));
        if (allowCreate) {
            comboBoxModel.AddSpecial(null);
        }
        add(comboBox);
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                value = (value == null) ? "Izveidot jaunu..." : ((TestFormat) value).Name; //i wish i was in C# right now
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        JButton deleteButton = new JButton("Dzēst");
        add(deleteButton);
        deleteButton.addActionListener(e -> {
            int index=comboBox.getSelectedIndex();
            if (!comboBoxModel.DeletableIndex(index)) {
                return;
            }
            int response = JOptionPane.showConfirmDialog(MainFrame.mainFrame,"Tiešām izdzēst?","Izdzēst formātu",OK_CANCEL_OPTION);
            if (!Utils.AcceptedDialog(response)) {
                return;
            }
            comboBoxModel.removeElementAt(index);
        });
        boolean deletable = comboBoxModel.DeletableIndex( comboBox.getSelectedIndex());
        deleteButton.setEnabled(deletable);
        comboBox.addItemListener(e -> {
            boolean deletable1 = comboBoxModel.DeletableIndex( comboBox.getSelectedIndex());
            deleteButton.setEnabled(deletable1);
        });
    }
}
