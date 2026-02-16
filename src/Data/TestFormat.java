package Data;

import SaveData.SaveDataHandler;

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
    public static TestFormats testFormats = new TestFormats();

    String Name;
    transient BufferedImage BaseImage;
    Dimension Placeholder;

    public TestFormat(BufferedImage baseImage,String name) {
        BaseImage=baseImage;
        Name=name;
    }
    @Override
    public String toString () {
        return Name;
    }
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(BaseImage,"png",out);
    }
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        BaseImage= ImageIO.read(in);

    }
    private void readObjectNoData() throws ObjectStreamException {

    }
}
