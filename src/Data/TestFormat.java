package Data;

import SaveData.SaveDataHandler;
import Utils.Utils.UnitRect;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

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
    public static class Question implements Serializable {
        public static class Answer implements Serializable {
            public UnitRect bounds;
            public boolean isCorrect;
            public Answer(UnitRect bounds, boolean isCorrect) {
                this.bounds=bounds;
                this.isCorrect=isCorrect;
            }
            public Answer(UnitRect bounds) {
                this(bounds,false);
            }
            public Answer() {
                this(null,false);
            }
            @Override
            public String toString() {
                return String.format("%s", isCorrect ? "Pareizi" : "Nepareizi");
            }
        }
        public List<Answer> answerList = new LinkedList<>();
        public enum Type {
            SINGLE_CHOICE,
            MULTI_CHOICE;
            @Override
            public String toString() {
                return switch (this) {
                    case SINGLE_CHOICE -> "Viena atbilde";
                    case MULTI_CHOICE -> "Vairākas izvēles";
                };
            }
        }
        public Type type;
        public Question (Type type) {
            this.type=type;
        }
        public Question () {
            this(Type.SINGLE_CHOICE);
        }

        @Override
        public String toString() {
            return type.toString();
        }
    }


    public static TestFormats testFormats = new TestFormats();

    public String Name;
    transient public BufferedImage BaseImage;
    public LinkedList<Question> questions = new LinkedList<>();
    public boolean created;

    public TestFormat(BufferedImage baseImage,String name) {
        created=true;
        BaseImage=baseImage;
        Name=name;
    }
    @Override
    public String toString () {
        return Name;
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(BaseImage,"png",out);
    }
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        BaseImage= ImageIO.read(in);

    }
    @Serial
    private void readObjectNoData() throws ObjectStreamException {

    }
}
