package Gui;

import Images.Images;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Utils.Utils.*;

public class ImageLabel extends JComponent {
    private BufferedImage image;
    private Image scaled;
    private ImageIcon imageIcon;
    private BufferedImage trueImage;
    public ImageLabel() {
    }
    public ImageLabel(BufferedImage image) {
        this();
        SetImage(image);
    }
    public void SetImage(BufferedImage image) {
        imageIcon.setImage(this.image=image);
        setVisible(image!=null);
        invalidate();
    }
    @Override
    public Dimension getPreferredSize() {
        return image == null ? super.getPreferredSize() : new Dimension(image.getWidth(this), image.getHeight(this));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image==null) {
            return;
        }
        g.drawImage(scaled,0,0,this);
    }
    @Override
    public void invalidate() {
        if (image!=null) {
            scaled = Images.resizeImage(image, getSize());
        }
        super.invalidate();
    }
}
