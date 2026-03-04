package Gui;

import Data.TestFormat;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

public class EditorQuestionList extends JPanel {
    public ListModdable<TestFormat.Question> list;
    public TestFormat activeFormat;
    public EditorAnswerList answerList;

    private ListSelectionListener listSelectionListener;

    private class ModifyQuestionDialog {
        public static JPanel jPanel;
        public static ButtonGroup questionType;
        public static Object test;
        static {
            jPanel = new JPanel(new FlowLayout());
            questionType = new ButtonGroup();
            jPanel.add(questionType);
        }
        public static TestFormat Open(MainFrame mainFrame) {
            int response = JOptionPane.showConfirmDialog(mainFrame, jPanel, "Izvēlieties pārbaudes darba formātu", OK_CANCEL_OPTION);

            if (response == JOptionPane.CLOSED_OPTION || response == JOptionPane.CANCEL_OPTION) {
                return null;
            }
            Object value = comboBox.getSelectedItem();
            if (!(value instanceof TestFormat)) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(false);
                fileChooser.setFileFilter(MainFrame.imageFilter);
                response=fileChooser.showOpenDialog(mainFrame);
                if (response != JFileChooser.APPROVE_OPTION) {
                    return null;
                }
                try {
                    value = new TestFormat(ImageIO.read(fileChooser.getSelectedFile()),"placeholder");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return (TestFormat)value;
        }
    }

    public EditorQuestionList(SceneEditor scene) {
        super();
        answerList=scene.answerList;
        list =new ListModdable<>() {
            @Override
            public void Create() {
                AddList(new TestFormat.Question());
            }

            @Override
            public void Modify(TestFormat.Question obj) {
                return;
            }

            @Override
            public void Destroy(TestFormat.Question obj, int index) {
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
        add(list);
        listSelectionListener= e -> {
            answerList.SetQuestion(list.GetSelectedValue());

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
