package Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import static java.lang.Math.*;

public class Utils {
    public enum Fit {
        FIT, //aspect, no more
        COVER, //aspect, no less
        FILL //no aspect, exact
    }
    public static Dimension FitDimension(Dimension dim,int targetWidth,int targetHeight, Fit fit) {
        switch (fit) {
            case FIT -> {
                if ((targetWidth==dim.width && targetHeight>=dim.height) || (targetWidth>=dim.width && targetHeight==dim.height)) {
                    break;
                }
                double scaleFactor=min((double)targetWidth /dim.width, (double)targetHeight /dim.height);
                dim.setSize(dim.getWidth()*scaleFactor,dim.getHeight()*scaleFactor);
            }
            case COVER -> {
                if ((targetWidth==dim.width && targetHeight<=dim.height) || (targetWidth<=dim.width && targetHeight==dim.height)) {
                    break;
                }
                double scaleFactor=max((double)targetWidth /dim.width, (double)targetHeight /dim.height);
                dim.setSize(dim.getWidth()*scaleFactor,dim.getHeight()*scaleFactor);
            }
            case FILL -> {
                dim.setSize(targetWidth,targetHeight);
            }
        }
        return dim;
    }
    public static Dimension FitDimension(Dimension dim, Dimension target,Fit fit) {
        return FitDimension(dim,target.width, target.height, fit);
    }


    public static class UnitRect implements Serializable {
        public double x, y, width, height;
        public UnitRect(double x, double y, double width, double height) {
            Update(x, y, width, height);
        }
        public UnitRect Update(double x, double y, double width, double height) {
            this.x = x;
            this.width = width;
            this.y = y;
            this.height = height;
            Validate();
            return this;
        }
        public UnitRect() {
            this(0f,0f,0f,0f);
        }
        public UnitRect(Rectangle rect,int fullWidth,int fullHeight) {
            this(rect.getX()/fullWidth,
                    rect.getY()/fullHeight,
                    rect.getWidth()/fullWidth,
                    rect.getHeight()/fullHeight);
        }
        public Rectangle ToFull (int fullWidth,int fullHeight) {
            return new Rectangle((int) (x*fullWidth), (int) (y*fullHeight), (int) (width*fullWidth), (int) (height*fullHeight));
        }

        private void Validate() {
            if (width<0f) {
                x+=width;
                width*=-1;
            }
            if (x<0f) {
                width+=x;
                x=0f;
            }
            if (x+width>1f) {
                width=1f-x;
            }

            if (height<0f) {
                y+=height;
                height*=-1;
            }
            if (y<0f) {
                height+=y;
                y=0f;
            }
            if (y+height>1f) {
                height=1f-y;
            }
        }
    }
    /**
     * For use with Graphics.DrawRect and repaint.
     */
    public static Rectangle RectIncrement(Rectangle rect) {
        Rectangle newRect= (Rectangle) rect.clone();
        newRect.width+=1;
        newRect.height+=1;
        return newRect;
    }
    public static Rectangle FromPoints(Point a,Point b) {
        int x=a.x, y=a.y, width=b.x-x,height=b.y-y;
        if (width<0) {
            x+=width;
            width*=-1;
        }
        if (height<0) {
            y+=height;
            height*=-1;
        }
        return new Rectangle(x,y,width,height);
    }
    public static boolean AcceptedDialog (int response) {
        return response!=JOptionPane.CLOSED_OPTION && response!= JOptionPane.CANCEL_OPTION;
    }
}
