package Gui;

import Data.TestFormat;
import Utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

public class DialogSelectFormat {
    public static JPanel jPanel;
    public static ComboBoxFormat comboBox;
    public static Object test;
    static {
        jPanel = new JPanel(new FlowLayout());
        comboBox = new ComboBoxFormat(true);
        jPanel.add(comboBox);
    }
    public static TestFormat Open(MainFrame mainFrame) {
        {
            comboBox.comboBoxModel.ResyncList();
            int response = JOptionPane.showConfirmDialog(mainFrame, jPanel, "Izvēlieties pārbaudes darba formātu", OK_CANCEL_OPTION);

            if (!Utils.AcceptedDialog(response)) {
                return null;
            }
        }
        Object value = comboBox.comboBox.getSelectedItem();
        if (!(value instanceof TestFormat)) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setFileFilter(MainFrame.imageFilter);
            int response = fileChooser.showOpenDialog(mainFrame);
            if (response != JFileChooser.APPROVE_OPTION) {
                return null;
            }
            try {
                value = new TestFormat(ImageIO.read(fileChooser.getSelectedFile()), null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return (TestFormat)value;
    }
}
