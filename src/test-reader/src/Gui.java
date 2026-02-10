import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Gui extends Frame {
    private static final FileNameExtensionFilter imageFilter =new FileNameExtensionFilter("Image files",ImageIO.getReaderFileSuffixes());
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
        addWindowListener(new MyWindowListener());
        setTitle("Test reader");
        setSize(900,300);
        setVisible(true);
        int returnVal=test.showOpenDialog(this);
    }
    private class MyWindowListener implements java.awt.event.WindowListener {
        @Override
        public void windowClosing(WindowEvent evt) {
            System.exit(0);
        }
        @Override public void windowOpened(WindowEvent evt) { }
        @Override public void windowClosed(WindowEvent evt) { }
        @Override public void windowIconified(WindowEvent evt) { System.out.println("Window Iconified"); }
        @Override public void windowDeiconified(WindowEvent evt) { System.out.println("Window Deiconified"); }
        @Override public void windowActivated(WindowEvent evt) { System.out.println("Window Activated"); }
        @Override public void windowDeactivated(WindowEvent evt) { System.out.println("Window Deactivated"); }
    }
    static void main(String[] args) {
        new Gui();
    }
}
