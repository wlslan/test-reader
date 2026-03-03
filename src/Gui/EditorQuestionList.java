package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.*;

public class EditorQuestionList extends JPanel {
    public ListModdable<TestFormat.Question> questionList;
    public EditorQuestionList() {
        super();
        setLayout(new FlowLayout());
        questionList=new ListModdable<>() {
            @Override
            public TestFormat.Question Create() {
                return new TestFormat.Question();
            }

            @Override
            public void Modify(TestFormat.Question obj) {
                return;
            }
        };
        questionList.list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                value= String.format("%d. %s", index,value.toString());
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        add(questionList);

    }
    public void SetFormat(TestFormat testFormat) {
        questionList.listModel.clear();
        for (TestFormat.Question question : testFormat.questions) {
            questionList.listModel.addElement(question);
        }
    }
}
