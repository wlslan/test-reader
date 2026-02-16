package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static Data.TestFormat.testFormats;

public class ComboBoxFormat extends JComboBox {
    public ComboBoxFormat(boolean allowCreate) {
        LinkedList<Object> testFormatsCopy= new LinkedList<Object>();
        for (Object item : testFormats) {
            testFormatsCopy.addLast(item);
        }
        if (allowCreate) {
            testFormatsCopy.addFirst(new Object());
        }
        super(testFormatsCopy.toArray());
    }
}
