package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.*;

public class EditorQuestionList extends JPanel {
    public ListModdable<TestFormat.Question> questionList;
    public EditorQuestionList() {
        super(new FlowLayout());
        questionList=new ListModdable<TestFormat.Question>() {
            @Override
            public TestFormat.Question Default() {
                return null;
            }

            @Override
            public void Modify(TestFormat.Question obj) {
                return;
            }
        };
        add(questionList);

    }
    public void SetFormat(TestFormat testFormat) {
        questionList.listModel.clear();
        for (TestFormat.Question question : testFormat.questions) {
            questionList.listModel.addElement(question);
        }
    }
}
