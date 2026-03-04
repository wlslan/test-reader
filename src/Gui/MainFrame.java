package Gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

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
        setSize(1100,600);
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
}
