package Gui;

import Data.DelayedCreator;
import Data.TestFormat;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EditorCanvas extends JPanel {
    public int sizeX, sizeY;

    private final SceneEditor scene;
    private final ImageLabel imagePanel;
    private final AnswerRects answerRects;
    private final SelectRect selectRect;

    private class AnswerRects extends JComponent {
        public static final Color ColorCorrect=new Color(0x00FF00), ColorIncorrect=new Color(0xFF0000);
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            TestFormat.Question question = scene.answerList.activeQuestion;
            if (question==null) {return;}
            for (TestFormat.Question.Answer answer:question.answerList) {
                PaintAnswer(g,answer);
            }
        }
        private void PaintAnswer(Graphics g,TestFormat.Question.Answer answer) {
            Color color = answer.isCorrect ? ColorCorrect:ColorIncorrect;
            Rectangle rect = answer.bounds.ToFull(sizeX,sizeY);
            g.setColor(color);
            g.drawRect(rect.x,rect.y,rect.width,rect.height);
        }
    }

    private final JLayeredPane layeredPane;
    public EditorCanvas (SceneEditor scene){
        super(new BorderLayout());
        this.scene=scene;
        setBorder(BorderFactory.createLineBorder(new Color(0x000000)));
        layeredPane=new JLayeredPane();
        layeredPane.setLayout(new FillLayout());
        imagePanel=new ImageLabel(Utils.Fit.FIT, Utils.Center.CENTER,null);

        layeredPane.add(imagePanel, Integer.valueOf(0));
        layeredPane.add(answerRects= new AnswerRects(), Integer.valueOf(1));
        layeredPane.add(selectRect= new SelectRect(), Integer.valueOf(2));

        add(layeredPane,BorderLayout.CENTER);
    }
    public void SetFormat(TestFormat testFormat) {
        //layeredPane.setPreferredSize(new Dimension(640, 640));
        imagePanel.SetImage(testFormat.BaseImage);
        RefreshAnswerDisplay();
    }
    public void CreateRect(DelayedCreator.Listener<Utils.UnitRect> listener) {
        selectRect.AddListener(listener);
        selectRect.Create();
    }
    public void RefreshAnswerDisplay() {
        answerRects.repaint();
    }
}
