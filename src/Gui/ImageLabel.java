package Gui;

import Images.Images;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageLabel extends JComponent {
    private Image scaled;
    private BufferedImage image;
    private Utils.Fit fit;
    private Utils.Center center;
    private Rectangle area;
    public ImageLabel() {
    }
    public ImageLabel(Utils.Fit fit, Utils.Center center, BufferedImage image) {
        this();
        this.fit=fit;
        this.center=center;
        SetImage(image);
    }
    public void SetImage(BufferedImage image) {
        this.image =image;
        setVisible(image!=null);
        invalidate();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image==null) {
            return;
        }
        g.drawImage(scaled,area.x,area.y,this);
    }
    @Override
    public void invalidate() {
        if (image!=null) {
            area=Utils.CenterRect(Utils.FitDimension(Images.getSize(image),getSize(),fit),getSize(), Utils.Center.CENTER);
            scaled = Images.resizeImage(image, area.getSize());
        }
        super.invalidate();
    }
}
