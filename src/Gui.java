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
                    testImageLabel=new JLabel(new ImageIcon (currentTestLayoutImage));
                    CardLayout cl = (CardLayout) rootPanel.getLayout();
                    cl.show(rootPanel,editorSceneName);
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
    public Gui(LayoutManager layout) {
        gui=this;
        rootPanel=new JPanel(layout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Test reader");
        setSize(900,300);
        setLayout(new CardLayout());
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
        rootPanel.add(editorScene,editorSceneName);
        baseScene.setVisible(true);
        editorScene.setVisible(true);
        rootPanel.setVisible(true);
        add(rootPanel);
        setVisible(true);
    }
    static void main(String[] args) {
        new Gui(new CardLayout());
    }
}
