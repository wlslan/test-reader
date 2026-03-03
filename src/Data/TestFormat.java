package Data;

import SaveData.SaveDataHandler;
import Utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;

public class TestFormat implements Serializable {
    public static class TestFormats extends LinkedList<TestFormat> implements SaveDataHandler.SavableObject {
        public static final String saveFile ="formats.dat";
        public TestFormats () {
            super();
        }
        @Override
        public String GetSaveFile() {
            return saveFile;
        }
    }
    public static class Question {
        public enum Type {
            SINGLE_CHOICE,
            MULTI_CHOICE
        }
    }
    public static class Answer {
        public Utils.Rect bounds;
        public boolean isCorrect;
        public Answer(Utils.Rect bounds, boolean isCorrect) {
            this.bounds=bounds;
            this.isCorrect=isCorrect;
        }
    }


    public static TestFormats testFormats = new TestFormats();

    public String Name;
    transient public BufferedImage BaseImage;
    public LinkedList<Answer> answers;
    public LinkedList<Question> questions;

    public TestFormat(BufferedImage baseImage,String name) {
        BaseImage=baseImage;
        Name=name;
        answers=new LinkedList<>();
        questions=new LinkedList<>();
    }
    @Override
    public String toString () {
        return Name;
    }

    @Serial
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(BaseImage,"png",out);
    }
    @Serial
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        BaseImage= ImageIO.read(in);

    }
    @Serial
    private void readObjectNoData() throws ObjectStreamException {

    }
}
