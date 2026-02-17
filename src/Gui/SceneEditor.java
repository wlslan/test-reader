package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public final class SceneEditor extends Scene {
    static final String defaultName = "editor";
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
        add(canvas = new EditorCanvas());
        add(answerList = new EditorAnswerList());
        add(questionList = new EditorQuestionList());
    }
    public void OpenFormat(TestFormat testFormat) {
        canvas.SetBaseImage(testFormat.BaseImage);
    }
}