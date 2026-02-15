import Data.TestFormat;
import Gui.MainFrame;
import SaveData.SaveDataHandler;

import javax.swing.*;

void main() throws IOException, ClassNotFoundException {

    TestFormat.testFormats = (TestFormat.TestFormats) SaveDataHandler.readFile(TestFormat.testFormats);
    try {
        // Set System L&F
        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
    } catch (UnsupportedLookAndFeelException e) {
        // handle exception
    } catch (ClassNotFoundException e) {
        // handle exception
    } catch (InstantiationException e) {
        // handle exception
    } catch (IllegalAccessException e) {
        // handle exception
    }
    new MainFrame();
}
