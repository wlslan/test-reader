package Images;

import Data.TestFormat;
import Gui.MainFrame;
import Gui.SceneBase;
import Utils.Utils;
import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {
    public final static int resolution=1;
    public static Dimension getSize(BufferedImage image) {
        return new Dimension(image.getWidth(),image.getHeight());
    }
    public static BufferedImage resizeImage(BufferedImage image, int targetWidth, int targetHeight) {
        if (image.getWidth()==targetWidth && image.getHeight()==targetHeight) {
            return image;
        }
        BufferedImage bi = new BufferedImage(targetWidth, targetHeight, BufferedImage.OPAQUE);
        Graphics2D g2d = bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return bi;
    }
    public static BufferedImage resizeImage(BufferedImage image, Dimension targetSize) {
        return resizeImage(image, targetSize.width, targetSize.height);
    }
    public static boolean highlightedArea(BufferedImage image, Utils.UnitRect area) {
        Rectangle rect = area.ToFull(image.getWidth(), image.getHeight());
        BufferedImage sub = image.getSubimage(rect.x,rect.y,rect.width,rect.height);
        BufferedImage goal = new BufferedImage(resolution,resolution,BufferedImage.OPAQUE);
        Graphics2D g2d=goal.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        boolean boi = g2d.drawImage(sub,0,0,resolution,resolution,null);
        if (!boi) {
            JOptionPane.showMessageDialog(MainFrame.mainFrame,"Failed");
        }
        g2d.dispose();
        //Debug.Debug.ShowImage(sub);
        //Debug.Debug.ShowImage(goal);
        int col=goal.getRGB(0,0);
        int blue = col & 0xff;
        int green = (col & 0xff00) >> 8;
        int red = (col & 0xff0000) >> 16;
        double score = ((double)(red+green+blue)/3)/255;
        return score <= 0.5;
    }
    public static Object[][] ReadTests(File[] files, TestFormat testFormat) {
        int n = files.length+1, m=testFormat.questions.size()+1;
        Object[][] results = new Object[n][];
        results[0]= new Object[m+1];
        results[0][0]="Darbs";
        results[0][m]="Summa";
        for (int i=1;i<m;i++) {
            results[0][i]=i;
        }
        int i=1;
        for (File file:files) {
            BufferedImage current;
            try {
                current = ImageIO.read(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int j=1;
            results[i]= new Object[m+1];
            results[i][m]=0;
            results[i][0]=file.getName();
            for (TestFormat.Question question : testFormat.questions) {
                boolean correct=true,correctFound=false;
                for (TestFormat.Question.Answer answer : question.answerList) {
                    boolean currentMarked =highlightedArea(current,answer.bounds);
                    if (currentMarked) {
                        if (correctFound) {
                            correct=false;
                            break;
                        }
                        correctFound=true;
                    }
                    correct &= (currentMarked == answer.isCorrect);
                }
                results[i][j]=correct ? 1 : 0;
                results[i][m] = (Integer)results[i][m] +(Integer)results[i][j];
                j++;
            }
            i++;
        }
        return results;
    }
}
