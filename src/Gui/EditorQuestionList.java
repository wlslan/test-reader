package Gui;

import Data.TestFormat;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

public class EditorQuestionList extends JPanel {
    public ListModdable<TestFormat.Question> list;
    public TestFormat activeFormat;
    public EditorAnswerList answerList;

    private ListSelectionListener listSelectionListener;

    private static class ModifyQuestionDialog {
        public static JPanel jPanel;
        public static RadioButtonMenu<TestFormat.Question.Type> questionType;
        public static Object test;
        static {
            jPanel = new JPanel(new FlowLayout());
            questionType = new RadioButtonMenu<>();
            questionType.AddList(TestFormat.Question.Type.values(),null);
            jPanel.add(questionType);
        }
        public static void Open(MainFrame mainFrame, TestFormat.Question question) {
            questionType.Set(question.type);
            int response = JOptionPane.showConfirmDialog(mainFrame, jPanel, "Reģidēt "+question, OK_CANCEL_OPTION);

            if (response == JOptionPane.CLOSED_OPTION || response == JOptionPane.CANCEL_OPTION) {
                return;
            }
            TestFormat.Question.Type value = questionType.Get();
            question.type=value;
        }
    }

    public EditorQuestionList(SceneEditor scene) {
        super();
        answerList=scene.answerList;
        setBorder(new TitledBorder("Jautājumi"));
        list =new ListModdable<>() {
            @Override
            public void Create() {
                AddList(new TestFormat.Question());
                scene.saved=false;
            }

            @Override
            public void Modify(TestFormat.Question obj, int i) {
                ModifyQuestionDialog.Open(scene.mainFrame,obj);
                scene.saved=false;
            }

            @Override
            public void Destroy(TestFormat.Question obj, int index) {
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
        add(list);
        listSelectionListener= e -> {
            answerList.SetQuestion(list.GetSelectedValue());
            scene.canvas.RefreshAnswerDisplay();
        };
        list.list.addListSelectionListener(listSelectionListener);
    }
    public void SetFormat(TestFormat testFormat) {
        activeFormat=testFormat;
        if (testFormat==null) {
            return;
        }
        list.model.ChangeList(testFormat.questions);
    }
}
