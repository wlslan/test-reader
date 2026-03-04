package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.image.BufferedImage;

public final class SceneEditor extends Scene {
    static final String defaultName = "editor";
    public EditorCanvas canvas;
    public EditorAnswerList answerList;
    public EditorQuestionList questionList;
    public JLabel testImageLabel;
    public BufferedImage currentTestLayoutImage;
    public boolean saved; //todo prevent accidental closing

    SceneEditor(MainFrame mainFrame) {
        super();
        name=defaultName;
        this.mainFrame = mainFrame;
        add(canvas = new EditorCanvas(this));
        add(answerList = new EditorAnswerList(this));
        add(questionList = new EditorQuestionList(this));
    }
    public void OpenFormat(TestFormat testFormat) {
        canvas.SetFormat(testFormat);
        questionList.SetFormat(testFormat);
    }
}