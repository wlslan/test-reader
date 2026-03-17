package SaveData;

import Data.SavableArray;

import java.io.*;

public class SaveDataHandler {
    static final String saveFolder = System.getenv("APPDATA")+"/TestReader/";
    static final String resultsFile = "results.csv";

    public interface SavableObject extends Serializable {
        public String GetSaveFile();
    }
    public static String GetSaveFilePath(SavableObject object) {
        return saveFolder+object.GetSaveFile();
    }
    public static String GetSaveFilePath(SavableArray object) {
        return saveFolder+object.GetSaveFile();
    }


    public static void writeFile (SavableObject object) {
        File file = new File(GetSaveFilePath(object));
        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public static File writeCSV (SavableArray array) {

        File file = new File(GetSaveFilePath(array));
        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileWriter fileOut = new FileWriter(file);
            fileOut.write(array.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
