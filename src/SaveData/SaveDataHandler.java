package SaveData;

import java.io.*;

public class SaveDataHandler {
    static final String saveFolder = System.getenv("APPDATA")+"/TestReader/";

    public interface SavableObject extends Serializable {
        public String GetSaveFile();
    }
    public static String GetSaveFilePath(SavableObject object) {
        return saveFolder+object.GetSaveFile();
    }
    public static void writeFile (SavableObject object) throws IOException {
        File file = new File(GetSaveFilePath(object));
        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(object);
    }
    public static SavableObject readFile (SavableObject object) throws IOException, ClassNotFoundException {
        File file = new File(GetSaveFilePath(object));
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            return (SavableObject) objIn.readObject();
        } catch (FileNotFoundException | InvalidClassException e) {
            writeFile(object);
            return object;
        }
    }
    public static void removeFile (String name) {
        //careful
    }
}
