package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.*;

public class EditorAnswerList extends JPanel {
    public ListModdable<TestFormat.Question.Answer> answerList;
    public JPanel answerPanel;
    public JPanel nullPanel;
    public final String answerPanelName="answer";
    public final String nullPanelName="null";
    public CardLayout cardLayout;

    public TestFormat.Question activeQuestion;

    public EditorAnswerList() {
        super();
        setLayout(cardLayout = new CardLayout());
        answerList= new ListModdable<TestFormat.Question.Answer>() {
            @Override
            public TestFormat.Question.Answer Create() {

                return new TestFormat.Question.Answer(activeQuestion,);
            }

            @Override
            public void Modify(TestFormat.Question.Answer obj) {
                return;
            }
        };
        answerList.list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                value= String.format("%d. %s", index,value.toString());
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        answerPanel=new JPanel();
        answerPanel.add(answerList);
        nullPanel = new JPanel();
        nullPanel.add(new JLabel("Nav izvēlēts jautājums"));
        add(answerPanel,answerPanelName);
        add(nullPanel,nullPanelName);
    }
    public void SetQuestion(TestFormat.Question question) {
        activeQuestion=question;
        if (question==null) {
            cardLayout.show(this,nullPanelName);
            return;
        }
        answerList.listModel.clear();
        for (TestFormat.Question.Answer answer : question.answerList) {
            answerList.listModel.addElement(answer);
        }
        cardLayout.show(this,answerPanelName);
    }
}
