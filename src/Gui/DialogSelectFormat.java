package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.*;

public class DialogSelectFormat {
    ComboBoxFormat comboBox;
    public Object Open(MainFrame mainFrame) {
        JPanel jPanel = new JPanel(new FlowLayout());
        comboBox = new ComboBoxFormat(true);
        jPanel.add(comboBox);
        int response = JOptionPane.showConfirmDialog(mainFrame, jPanel);
        if (response == JOptionPane.CLOSED_OPTION) {
            return null;
        }
        return comboBox.getSelectedItem();
    }
}
