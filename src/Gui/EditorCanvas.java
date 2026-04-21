package Gui;

import Data.DelayedCreator;
import Data.TestFormat;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EditorCanvas extends JPanel {
    static final int baseSizeX=640,baseSizeY=640;
    public int sizeX, sizeY;

    private final SceneEditor scene;
    private final JPanel imagePanel;
    private final JLabel testImageLabel;
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
        this.scene=scene;
        setBorder(BorderFactory.createLineBorder(new Color(0)));
        layeredPane=new JLayeredPane();
        layeredPane.setLayout(new FillLayout());

        imagePanel=new JPanel();
        imagePanel.add(testImageLabel = new JLabel());

        layeredPane.add(imagePanel, Integer.valueOf(0));
        layeredPane.add(answerRects= new AnswerRects(), Integer.valueOf(1));
        layeredPane.add(selectRect= new SelectRect(), Integer.valueOf(2));

        add(layeredPane);
    }
    public void SetFormat(TestFormat testFormat) {
        Dimension size=new Dimension(testFormat.BaseImage.getWidth(),testFormat.BaseImage.getHeight());
        Utils.FitDimension(size,baseSizeX,baseSizeY, Utils.Fit.FIT);
        sizeX=size.width;
        sizeY=size.height;
        layeredPane.setPreferredSize(new Dimension(sizeX, sizeY));
        BufferedImage image = Images.Images.resizeImage(testFormat.BaseImage, size);
        testImageLabel.setIcon(new ImageIcon(image));
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
