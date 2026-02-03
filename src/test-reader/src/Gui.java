import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Gui extends Frame {
    private static FileNameExtensionFilter imageFilter =new FileNameExtensionFilter("Image files",ImageIO.getReaderFileSuffixes());
    private JButton createLayoutButton;
    private JButton readTestButton;
    private JFileChooser test;
    public Gui() {
        setLayout(new FlowLayout());
        createLayoutButton=new JButton("Create test layout");
        readTestButton = new JButton("Open tests for reading");
        test=new JFileChooser();
        add(createLayoutButton);
        add(readTestButton);
        setTitle("Test reader");
        setSize(900,300);
        setVisible(true);
        int returnVal=test.showOpenDialog(this);
    }
    static void main(String[] args) {
        new Gui();
        Lang.setLanguage();
    }
}
