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
                float scaleFactor=min((float)targetWidth /width, (float)targetHeight /height);
                targetWidth=(int)(width*scaleFactor);
                targetHeight=(int)(height*scaleFactor);
            }
            case COVER -> {
                int width=image.getWidth(),height=image.getHeight();
                float scaleFactor=max((float)targetWidth /width, (float)targetHeight /height);
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
        public float x1,y1,x2,y2;
        Rect(float x1,float y1,float x2,float y2) {
            this.x1=x1;
            this.x2=x2;
            this.y1=y1;
            this.y2=y2;
        }
    }
}
