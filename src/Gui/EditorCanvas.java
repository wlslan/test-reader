package Gui;

import Data.TestFormat;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class EditorCanvas extends JPanel {
    static final int baseSizeX=640,baseSizeY=640;
    public int sizeX, sizeY;

    private SceneEditor scene;
    private JPanel imagePanel;
    private JLabel testImageLabel;
    private ImageIcon testImage;
    private AnswerRects answerRects;
    private SelectRect selectRect;

    private class AnswerRects extends JComponent {
        private class ColorRectangle {
            public Color color;
            public Rectangle rect;
            public ColorRectangle(Color color, Rectangle rect) {
                this.color=color;
                this.rect=rect;
            }
        }
        private Map<TestFormat.Question.Answer,ColorRectangle> rectangles = new HashMap<>();
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            TestFormat.Question question = scene.answerList.activeQuestion;
            if (question==null) {return;}
            for (ColorRectangle cRect : rectangles.values()) {
                g.setColor(cRect.color);
                Rectangle r=cRect.rect;
                g.drawRect(r.x,r.y,r.width,r.height);
            }
        }
        public void DrawAnswer(TestFormat.Question.Answer answer) {
            Rectangle rect=answer.bounds.ToFull(sizeX,sizeY);
            Color color = answer.isCorrect ? new Color(0x00FF00) : new Color(0xFF0000);
            rectangles.put(answer,new ColorRectangle(color,rect));
            repaint();
        }
        public void UndrawAnswer(TestFormat.Question.Answer answer) {
            Rectangle rect=answer.bounds.ToFull(sizeX,sizeY);
            rectangles.remove(answer);
            repaint();
        }
        public void RedrawAnswer(TestFormat.Question.Answer answer) {
            UndrawAnswer(answer);
            DrawAnswer(answer);
        }
        public void DrawQuestion(TestFormat.Question question) {
            rectangles.clear();
            repaint();
            for (TestFormat.Question.Answer answer:question.answerList) {
                DrawAnswer(answer);
            }
        }
    };

    private JLayeredPane layeredPane;
    private MouseListener mouseListener;
    public EditorCanvas (SceneEditor scene){
        this.scene=scene;
        setBorder(BorderFactory.createLineBorder(new Color(0)));
        layeredPane=new JLayeredPane();
        layeredPane.setLayout(new FillLayout());

        imagePanel=new JPanel();
        imagePanel.add(testImageLabel = new JLabel(testImage=new ImageIcon()));

        layeredPane.add(imagePanel, new Integer(0));
        layeredPane.add(answerRects= new AnswerRects(), new Integer(1));
        layeredPane.add(selectRect= new SelectRect(), new Integer(2));

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
    }
    public void CreateRect(DelayedCreator.Listener<Utils.UnitRect> listener) {
        selectRect.AddListener(listener);
        selectRect.Create();
    }
    public void CreateAnswer(TestFormat.Question.Answer answer) {
        answerRects.DrawAnswer(answer);
    }
    public void ModifyAnswer(TestFormat.Question.Answer answer) {
        answerRects.RedrawAnswer(answer);
    }
    public void DestroyAnswer(TestFormat.Question.Answer answer) {
        answerRects.UndrawAnswer(answer);
    }
}
