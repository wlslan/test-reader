package Gui;

import Data.SavableArray;
import Data.TestFormat;
import SaveData.SaveDataHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static Images.Images.ReadTests;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

public final class SceneBase extends Scene {
    static final String defaultName = "base";
    private MainFrame mainFrame;
    private JButton editorButton,readTestButton;
    private class EditorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Object currentFormat = DialogSelectFormat.Open(mainFrame);
            if (currentFormat == null) {
                return;
            }
            mainFrame.sceneEditor.OpenFormat((TestFormat) currentFormat);
            mainFrame.ChangeScene(mainFrame.sceneEditor);
        }
    }
    private static class DialogReadTest {
        public static JPanel jPanel;
        public static ComboBoxFormat comboBox;
        public static JButton selectFiles;
        public static File[] selectedFiles;
        public static TestFormat currentFormat;
        static {
            jPanel = new JPanel(new FlowLayout());
            comboBox = new ComboBoxFormat(false);
            jPanel.add(comboBox);
            selectFiles=new JButton("Izvēlēties pārbaudes darbus");
            selectFiles.addActionListener(_ -> {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true);
                fileChooser.setFileFilter(MainFrame.imageFilter);
                int response=fileChooser.showOpenDialog(MainFrame.mainFrame);
                if (response != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                selectedFiles= fileChooser.getSelectedFiles();

            });
            jPanel.add(selectFiles);
        }
        public static boolean Open(MainFrame mainFrame) {
            int response = JOptionPane.showConfirmDialog(mainFrame, jPanel, "Izvēlieties pārbaudes darba formātu un attēlus", OK_CANCEL_OPTION);

            if (response == JOptionPane.CLOSED_OPTION || response == JOptionPane.CANCEL_OPTION) {
                return false;
            }
            currentFormat = (TestFormat) comboBox.getSelectedItem();
            return true;
        }
    }
    private class ReadTestButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (!DialogReadTest.Open(mainFrame)) {
                return;
            }
            File results = SaveDataHandler.writeCSV(new SavableArray(ReadTests(DialogReadTest.selectedFiles,DialogReadTest.currentFormat)));
            try {
                Desktop.getDesktop().open(results);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    SceneBase(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        name=defaultName;
        setSize(getSize());
        editorButton =new JButton("Izveidot pārbaudes darba formātu");
        editorButton.addActionListener(new EditorButtonListener());
        readTestButton = new JButton("Pārbaudīt pārbaudes darbus");
        readTestButton.addActionListener(new ReadTestButtonListener());
        add(editorButton);
        add(readTestButton);

    }
}