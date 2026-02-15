package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public final class SceneBase extends Scene {
    static final String defaultName = "base";
    private JButton editorButton,readTestButton;
    private class EditorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setFileFilter(MainFrame.imageFilter);
            int response=fileChooser.showOpenDialog(mainFrame);
            if (response == JFileChooser.APPROVE_OPTION) {
                try {
                    mainFrame.sceneEditor.SetImage(fileChooser.getSelectedFile());
                    mainFrame.ChangeScene(mainFrame.sceneEditor);
                } catch (IOException e) {
                    throw new RuntimeException(e); //add invalid file alert
                }
            }
        }
    }
    private class ReadTestButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(true);
            fileChooser.setFileFilter(MainFrame.imageFilter);
            int response=fileChooser.showOpenDialog(mainFrame);
            if (response == JFileChooser.APPROVE_OPTION) {
                //todo (getSelectedFiles plural
            }
        }
    }

    SceneBase(MainFrame mainFrame) {
        super(new FlowLayout());
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