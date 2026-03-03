package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class EditorCanvas extends JPanel {
    private JLabel testImageLabel;
    public EditorCanvas (){
        add(testImageLabel = new JLabel());
    }
    public void SetFormat(TestFormat testFormat) {
        BufferedImage image = Utils.Utils.resizeImage(testFormat.BaseImage, 640, 640, Utils.Utils.ImageFit.FIT);
        testImageLabel.setIcon(new ImageIcon(image));
    }
}
