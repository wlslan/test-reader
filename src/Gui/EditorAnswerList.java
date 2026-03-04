package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.*;

public class EditorAnswerList extends JPanel {
    public ListModdable<TestFormat.Question.Answer> list;
    public JPanel answerPanel;
    public JPanel nullPanel;
    public final String answerPanelName="answer";
    public final String nullPanelName="null";
    public CardLayout cardLayout;

    public TestFormat.Question activeQuestion;

    public EditorCanvas canvas;

    public EditorAnswerList(SceneEditor scene) {
        canvas =scene.canvas;
        setLayout(cardLayout = new CardLayout());
        list = new ListModdable<>() {
            @Override
            public void Create() {
                canvas.CreateRect(rect -> {
                    if (rect == null) {
                        return;
                    }
                    TestFormat.Question.Answer answer = new TestFormat.Question.Answer(rect);
                    AddList(answer);
                    canvas.CreateAnswer(answer);
                });
            }

            @Override
            public void Modify(TestFormat.Question.Answer answer) {

                canvas.ModifyAnswer(answer);
            }

            @Override
            public void Destroy(TestFormat.Question.Answer answer, int index) {
                canvas.DestroyAnswer(answer);
                RemoveList(index);
            }
        };
        list.list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                value= String.format("%d. %s", index,value.toString());
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        answerPanel=new JPanel();
        answerPanel.add(list);
        nullPanel = new JPanel();
        nullPanel.add(new JLabel("Nav izvēlēts jautājums"));
        add(answerPanel,answerPanelName);
        add(nullPanel,nullPanelName);
        SetQuestion(null);
    }
    public void SetQuestion(TestFormat.Question question) {
        activeQuestion=question;
        if (question==null) {
            cardLayout.show(this,nullPanelName);
            return;
        }
        list.model.ChangeList(question.answerList);
        cardLayout.show(this,answerPanelName);
    }
}
