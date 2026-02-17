package Gui;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class EditorCanvas extends JPanel {
    private JLabel testImageLabel;
    public EditorCanvas (){
        add(testImageLabel = new JLabel());
    }
    public void SetBaseImage(BufferedImage image) {
        image = Utils.Utils.resizeImage(image, 640, 640, Utils.Utils.ImageFit.FIT);
        testImageLabel.setIcon(new ImageIcon(image));
    }
}
