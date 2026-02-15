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

        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
