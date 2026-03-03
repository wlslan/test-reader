package Gui;

import Data.TestFormat;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class EditorCanvas extends JPanel {
    static final int sizeX=640,sizeY=640;
    Utils.Rect curRect=null;
    private JLabel testImageLabel;
    private MouseListener mouseListener;
    public EditorCanvas (){
        add(testImageLabel = new JLabel());
        mouseListener = new MouseListener() {
            Point rectStart;
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                rectStart=e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (rectStart!=null) {
                    curRect.Update(rectStart.getX()/sizeX,
                                rectStart.getY()/sizeY,
                                (double) e.getX() /sizeX,
                                (double) e.getY() /sizeY
                    );
                    curRect=null;

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }
    public void SetFormat(TestFormat testFormat) {
        BufferedImage image = Utils.resizeImage(testFormat.BaseImage, sizeX,sizeY, Utils.ImageFit.FIT);
        testImageLabel.setIcon(new ImageIcon(image));
    }
    public void SelectRectangle(Utils.Rect rect) {
        curRect=rect;
        addMouseListener(mouseListener);
    }
}
