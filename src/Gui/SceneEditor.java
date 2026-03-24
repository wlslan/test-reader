package Gui;

import Data.TestFormat;
import SaveData.SaveDataHandler;
import Utils.Utils;

import javax.swing.*;
import java.awt.image.BufferedImage;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

public final class SceneEditor extends Scene {
    static final String defaultName = "editor";
    public EditorCanvas canvas;
    public EditorAnswerList answerList;
    public EditorQuestionList questionList;
    public boolean saved;

    public TestFormat currentFormat;

    public MainFrame mainFrame;
    public JMenu menu;

    SceneEditor(MainFrame mainFrame) {
        super();
        this.mainFrame=mainFrame;
        name=defaultName;
        this.mainFrame = mainFrame;
        menu = new JMenu("Fails");
        mainFrame.menuBar.add(menu);
        menu.setEnabled(false);
        JMenuItem saveButton = new JMenuItem("Saglabāt");
        saveButton.addActionListener(e -> {
            if (currentFormat.created) {
                JPanel panel = new JPanel();
                JTextField field = new JTextField("",20 );
                panel.add(field);
                int response = JOptionPane.showConfirmDialog(mainFrame, panel, "Izvēlieties nosaukumu", OK_CANCEL_OPTION);
                if (!Utils.AcceptedDialog(response)) {
                    return;
                }
                currentFormat.Name=field.getText();
                TestFormat.testFormats.add(currentFormat);
                currentFormat.created=false;
            }
            SaveDataHandler.writeFile(TestFormat.testFormats);
            saved=true;
        });
        menu.add(saveButton);
        JMenuItem closeButton = new JMenuItem("Aizvērt");
        closeButton.addActionListener(e -> {
            boolean forbidden = false;
            if (!saved) {
                int response = JOptionPane.showConfirmDialog(mainFrame, "Aizvērt atvērto formātu?", "Nesaglabātas izmaiņas", OK_CANCEL_OPTION);
                forbidden = response == JOptionPane.CLOSED_OPTION || response == JOptionPane.CANCEL_OPTION;
            }
            if (!forbidden) {
                menu.setEnabled(false);
                mainFrame.ChangeScene(mainFrame.sceneBase); //maybe give scenes a quit method that closes program from sceneBase and returns to sceneBase else
            }
        });
        menu.add(closeButton);
        add(canvas = new EditorCanvas(this));
        add(answerList = new EditorAnswerList(this));
        add(questionList = new EditorQuestionList(this));
    }
    public void OpenFormat(TestFormat testFormat) {
        menu.setEnabled(true);
        currentFormat=testFormat;
        canvas.SetFormat(testFormat);
        questionList.SetFormat(testFormat);
    }
}