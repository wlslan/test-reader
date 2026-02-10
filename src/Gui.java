import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Gui extends JFrame {
    Gui gui;
    static final String baseSceneName="base",editorSceneName="editor";
    private static final FileNameExtensionFilter imageFilter =new FileNameExtensionFilter("Image files",ImageIO.getReaderFileSuffixes());
    private JPanel baseScene;
    private JButton editorButton;
    private class EditorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(imageFilter);
            int response=fileChooser.showOpenDialog(gui);
            if (response == JFileChooser.APPROVE_OPTION) {
                try {
                    currentTestLayoutImage=ImageIO.read(fileChooser.getSelectedFile());
                    currentTestLayoutImage=Utils.resizeImage(currentTestLayoutImage,100,100, Utils.ImageFit.COVER);
                    testImageLabel.setIcon(new ImageIcon (currentTestLayoutImage));
                    CardLayout cl = (CardLayout) rootPanel.getLayout();
                    cl.show(rootPanel,editorSceneName);
                    gui.setVisible(true);
                    System.out.println("BOILS");
                } catch (IOException e) {
                    System.out.println("LIGMA");
                    //(file invalid)
                }
            }
        }
    }
    private JButton readTestButton;
    private class ReadTestButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(imageFilter);
            int returnVal=fileChooser.showOpenDialog(gui);
        }
    }

    private JPanel editorScene;
    private JLabel testImageLabel;
    private BufferedImage currentTestLayoutImage;

    private JPanel rootPanel;
    private CardLayout cardLayout;
    public Gui() {
        gui=this;
        cardLayout = new CardLayout();
        rootPanel=new JPanel(cardLayout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Test reader");
        setSize(1920,1080);
        baseScene=new JPanel(new FlowLayout());
        baseScene.setSize(getSize());
        editorButton =new JButton("Izveidot pārbaudes darba formātu");
        editorButton.addActionListener(new EditorButtonListener());
        readTestButton = new JButton("Pārbaudīt pārbaudes darbus");
        readTestButton.addActionListener(new ReadTestButtonListener());
        baseScene.add(editorButton);
        baseScene.add(readTestButton);
        rootPanel.add(baseScene, baseSceneName);
        editorScene = new JPanel(new FlowLayout());
        testImageLabel= new JLabel();
        editorScene.add(testImageLabel);
        rootPanel.add(editorScene,editorSceneName);
        cardLayout.show(rootPanel,baseSceneName);
        add(rootPanel);
        setVisible(true);
    }
    static void main(String[] args) {
        new Gui();
    }
}
