package Debug;

import Gui.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Debug {
    public static void ShowImage(BufferedImage image) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        panel.add(label);
        label.setIcon(new ImageIcon(image));
        JOptionPane.showMessageDialog(MainFrame.mainFrame,panel);
    }
}
