import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.*;

public class Utils {
    public enum ImageFit {
        FIT, //aspect, no more
        COVER, //aspect, no less
        FILL //no aspect, exact
    }
    public static BufferedImage resizeImage(BufferedImage image, int targetHeight,int targetWidth, ImageFit imageFit) {
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

        BufferedImage bi = new BufferedImage(targetWidth, targetHeight, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = bi.createGraphics();
        //g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return bi;
    }
}
