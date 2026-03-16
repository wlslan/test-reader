package Gui;

import Data.TestFormat;
import SaveData.SaveDataHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public final class SceneEditor extends Scene {
    static final String defaultName = "editor";
    public JMenuBar menuBar;
    public EditorCanvas canvas;
    public EditorAnswerList answerList;
    public EditorQuestionList questionList;
    public JLabel testImageLabel;
    public BufferedImage currentTestLayoutImage;
    public boolean saved; //to prevent accidental closing

    SceneEditor(MainFrame mainFrame) {
        super(new FlowLayout());
        name=defaultName;
        this.mainFrame = mainFrame;
        add(menuBar);
        JMenu menu = new JMenu("Fails");
        JMenuItem saveButton = new JMenuItem("Saglabāt");
        saveButton.addActionListener(e -> {
            TestFormat.testFormats.add(currentFormat); //figure that out also have a system where actively edite dthings have a wip parameter so in this case update if not wip
            SaveDataHandler.writeFile(TestFormat.testFormats);
        });
        menu.add(saveButton);
        JMenuItem closeButton = new JMenuItem("Aizvērt");
        closeButton.addActionListener(e -> {
            if (!saved) {
                //stop it or something
            }
            mainFrame.ChangeScene(mainFrame.sceneBase); //maybe give scenes a quit method that closes program from sceneBase and returns to sceneBase else
        });
        add(canvas = new EditorCanvas());
        add(answerList = new EditorAnswerList());
        add(questionList = new EditorQuestionList());
    }
    public void OpenFormat(TestFormat testFormat) {
        canvas.SetFormat(testFormat);
        answerList.SetFormat(testFormat);
        questionList.SetFormat(testFormat);
    }
}