package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.util.LinkedList;

public class ComboBoxFormat extends JComboBox {
    public ComboBoxFormat(boolean allowCreate) {
        LinkedList<TestFormat> testFormatsCopy= TestFormat.testFormats;
        if (allowCreate) {
            testFormatsCopy.addFirst(null);
        }
        super(testFormatsCopy.toArray());
    }
}
