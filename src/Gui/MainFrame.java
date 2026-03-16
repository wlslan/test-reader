package Gui;

import Data.TestFormat;
import Utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainFrame extends JFrame {
    public static final String programName = "Test reader";
    public static final FileNameExtensionFilter imageFilter =new FileNameExtensionFilter("Image files",ImageIO.getReaderFileSuffixes());
    public SceneBase sceneBase;
    public SceneEditor sceneEditor;

    private JPanel rootPanel;
    private CardLayout cardLayout;
    public MainFrame() {
        cardLayout = new CardLayout();
        rootPanel=new JPanel(cardLayout);
        add(rootPanel);

        InitScene(sceneBase = new SceneBase(this));
        InitScene(sceneEditor = new SceneEditor(this));

        ChangeScene(sceneBase);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        //setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        setTitle(programName);
    }
    public void InitScene(Scene scene) {
        rootPanel.add(scene,scene.name);
    }
    public void ChangeScene(Scene scene) {
        CardLayout cl = (CardLayout) rootPanel.getLayout();
        cl.show(rootPanel,scene.name);
    }

    public Results ReadTest(BufferedImage image, TestFormat format, int resolution) {
        resolution=10;
        int questionCount=format.questions.size();
        int[] points = new int[questionCount];
        int i=0;
        for (TestFormat.Question question : format.questions) {
            for (TestFormat.Question.Answer answer : question.answerList) {
                boolean filled=ReadAnswered(image, answer.bounds,resolution);
            }
            i++;
        }
    }
    public boolean ReadAnswered(BufferedImage testImage,Utils.Rect area, int resolution) {
        BufferedImage result=new BufferedImage(1,1, BufferedImage.OPAQUE);
        int x=testImage.getWidth(),y=testImage.getHeight();
        Rectangle rectangle = new Rectangle((int)(area.x0*x), (int) (area.y0*y), (int) ((area.x1-area.x0)*x), (int) ((area.y1-area.y0)*y));
        BufferedImage source=testImage.getSubimage(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
        Graphics2D graphics=result.createGraphics();
        graphics.drawImage(source,0,0,1,1,null);
        //get average color or sum ok whatevs
    }
}
