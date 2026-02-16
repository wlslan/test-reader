package Gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class SceneEditor extends Scene {
    static final String defaultName = "editor";
    public JLabel testImageLabel;
    public BufferedImage currentTestLayoutImage;

    SceneEditor(MainFrame mainFrame) {
        super(new FlowLayout());
        this.mainFrame = mainFrame;
        testImageLabel = new JLabel();
        add(testImageLabel);
        name=defaultName;
    }
    public void SetImage(File file) throws IOException {
        currentTestLayoutImage = ImageIO.read(file);
        currentTestLayoutImage = Utils.Utils.resizeImage(currentTestLayoutImage, 640, 640, Utils.Utils.ImageFit.FIT);
        testImageLabel.setIcon(new ImageIcon(currentTestLayoutImage));
    }
}