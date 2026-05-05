import Data.TestFormat;
import Gui.MainFrame;
import SaveData.SaveDataHandler;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        TestFormat.testFormats = (TestFormat.TestFormats) SaveDataHandler.readFile(TestFormat.testFormats);
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
        }
        new MainFrame();
    }
}
