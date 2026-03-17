package Gui;

import Data.TestFormat;
import Utils.Utils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;


public class EditorAnswerList extends JPanel {
    public ListModdable<TestFormat.Question.Answer> list;
    public JPanel answerPanel;
    public JPanel nullPanel;
    public final String answerPanelName="answer";
    public final String nullPanelName="null";
    public CardLayout cardLayout;

    public TestFormat.Question activeQuestion;

    public EditorCanvas canvas;

    private static class ModifyAnswerDialog {
        public static JPanel jPanel;
        public static JCheckBox isCorrect;
        public static JButton rebound;
        private static ActionListener actionListener;
        private static Utils.UnitRect curRect;
        static {
            jPanel = new JPanel(new FlowLayout());
            isCorrect = new JCheckBox("Pareiza");
            rebound = new JButton("Mainīt formu");
            jPanel.add(isCorrect);
            jPanel.add(rebound);
        }
        public static void Open(MainFrame mainFrame, TestFormat.Question.Answer answer,ActionListener listener) {
            isCorrect.setSelected(answer.isCorrect);
            curRect = answer.bounds;
            Reopen(mainFrame,answer,listener);
        }
        private static void Reopen (MainFrame mainFrame, TestFormat.Question.Answer answer,ActionListener listener) {
            if (actionListener!=null) {
                rebound.removeActionListener(actionListener);
            }
            JOptionPane optionPane = new JOptionPane(jPanel,JOptionPane.PLAIN_MESSAGE,JOptionPane.OK_CANCEL_OPTION,null);
            JDialog dialog = optionPane.createDialog(mainFrame,"Reģidēt "+answer);
            dialog.getContentPane().add(optionPane);
            rebound.addActionListener(actionListener= e -> {
                dialog.dispose();
                optionPane.setValue(JOptionPane.CLOSED_OPTION);
                mainFrame.sceneEditor.canvas.CreateRect(result -> {
                    curRect=result;
                    Reopen(mainFrame,answer,listener);
                });
            });

            dialog.validate();
            dialog.setLocationRelativeTo(mainFrame);
            dialog.setVisible(true);

            Object response = optionPane.getValue();
            int trueResponse;
            if (response==null) {
                trueResponse=JOptionPane.CLOSED_OPTION;
            }
            else {
                if(response instanceof Integer) {
                    trueResponse = (Integer) response;
                }
                else {
                    trueResponse = JOptionPane.CLOSED_OPTION;
                }
            }

            if (trueResponse == JOptionPane.CLOSED_OPTION || trueResponse == JOptionPane.CANCEL_OPTION) {
                return;
            }

            boolean value1 = isCorrect.isSelected();
            Utils.UnitRect value2 = curRect;
            answer.isCorrect=value1;
            answer.bounds=value2;
            listener.actionPerformed(null);

        }
    }
    public EditorAnswerList(SceneEditor scene) {
        canvas =scene.canvas;
        setBorder(new TitledBorder("Atbildes"));
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
                    canvas.RefreshAnswerDisplay();
                    scene.saved=false;
                });
            }

            @Override
            public void Modify(TestFormat.Question.Answer answer, int index) {
                if (answer == null) {
                    return;
                }
                ActionListener actionListener= _ -> {
                    ModifyList(index);
                    canvas.RefreshAnswerDisplay();
                    scene.saved=false;
                };
                ModifyAnswerDialog.Open(scene.mainFrame,answer,actionListener);
            }

            @Override
            public void Destroy(TestFormat.Question.Answer answer, int index) {
                canvas.RefreshAnswerDisplay();
                RemoveList(index);
                scene.saved=false;
            }
        };
        list.list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                value= String.format("%d. %s", index+1,value.toString());
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
