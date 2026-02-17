package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.*;

public class EditorAnswerList extends JPanel {
    public JList<TestFormat.Answer> answerList;
    public EditorAnswerList() {
        super(new FlowLayout());
        answerList=new JList<>();
    }
}
