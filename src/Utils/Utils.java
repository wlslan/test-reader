package Utils;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.*;

public class Utils {
    public enum ImageFit {
        FIT, //aspect, no more
        COVER, //aspect, no less
        FILL //no aspect, exact
    }
    public static BufferedImage resizeImage(BufferedImage image, int targetWidth,int targetHeight, ImageFit imageFit) {
        switch (imageFit)    {
            case FIT -> {
                int width=image.getWidth(),height=image.getHeight();
                double scaleFactor=min((double)targetWidth /width, (double)targetHeight /height);
                targetWidth=(int)(width*scaleFactor);
                targetHeight=(int)(height*scaleFactor);
            }
            case COVER -> {
                int width=image.getWidth(),height=image.getHeight();
                double scaleFactor=max((double)targetWidth /width, (double)targetHeight /height);
                targetWidth=(int)(width*scaleFactor);
                targetHeight=(int)(height*scaleFactor);
            }
            case FILL -> {
            }
        }

        BufferedImage bi = new BufferedImage(targetWidth, targetHeight, BufferedImage.OPAQUE);
        Graphics2D g2d = bi.createGraphics();
        //g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return bi;
    }
    public static BufferedImage resizeImage(BufferedImage image, Dimension targetSize, ImageFit imageFit) {
        return resizeImage(image, targetSize.width, targetSize.height, imageFit);
    }

    public static class Rect {
        public double x0, y0, x1, y1;
        public Rect(double x0, double y0, double x1, double y1) {
            Update(x0,y0,x1,y1);
        }
        public Rect Update(double x0, double y0, double x1, double y1) {
            this.x0 = x0;
            this.x1 = x1;
            this.y0 = y0;
            this.y1 = y1;
            Validate();
            return this;
        }
        public Rect() {
            this(0f,0f,0f,0f);
        }
        private void Validate() {
            double temp;
            if (x0>x1) {
                temp=x0;
                x0=x1;
                x1=temp;
            }
            if (y0>y1) {
                temp=y0;
                y0=y1;
                y1=temp;
            }
            x0=max(0f,x0);
            y0=max(0f,y0);
            x1=min(1f,x1);
            y1=min(1f,y1);
        }
    }
}
