package Gui;

import Images.Images;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;

public class ImageLabel extends JComponent {
    private Image scaled;
    private BufferedImage image;
    private Utils.Fit fit;
    private Utils.Center center;
    private Rectangle area;
    public ImageLabel() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                invalidate();
            }
        });
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
            area=new Rectangle(Images.getSize(image));
            area=Utils.CenterFillRect(getBounds(), area,center, fit);
            if (area.getWidth()==0 || area.getHeight()==0) {
                scaled=null;
            }
            else {
                scaled = Images.resizeImage(image, area.getSize());
            }
        }
        super.invalidate();
    }
}
