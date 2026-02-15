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
        testImageLabel.setMinimumSize(new Dimension(640,640));
        add(testImageLabel);
        name=defaultName;
    }
    public void SetImage(File file) throws IOException {
        currentTestLayoutImage = ImageIO.read(file);
        currentTestLayoutImage = Utils.Utils.resizeImage(currentTestLayoutImage, testImageLabel.getWidth()-10, testImageLabel.getHeight()-10, Utils.Utils.ImageFit.FIT);

        System.out.println(testImageLabel.getWidth());
        testImageLabel.setIcon(new ImageIcon(currentTestLayoutImage));
    }
}