package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.*;

public class EditorAnswerList extends JPanel {
    public ListModdable<TestFormat.Answer> answerList;
    public EditorAnswerList() {
        super(new FlowLayout());
        answerList=new ListModdable<TestFormat.Answer>() {
            @Override
            public TestFormat.Answer Default() {
                return null;
            }

            @Override
            public void Modify(TestFormat.Answer obj) {
                return;
            }
        };
        add(answerList);

    }
    public void SetFormat(TestFormat testFormat) {
        answerList.listModel.clear();
        for (TestFormat.Answer answer : testFormat.answers) {
            answerList.listModel.addElement(answer);
        }
    }
}
