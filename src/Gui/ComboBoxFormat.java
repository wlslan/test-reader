package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static Data.TestFormat.testFormats;

public class ComboBoxFormat extends JComboBox<Object> {
    public ComboBoxFormat(boolean allowCreate) {
        LinkedList<Object> testFormatsCopy= new LinkedList<>();
        for (Object item : testFormats) {
            testFormatsCopy.addLast(item);
        }
        if (allowCreate) {
            testFormatsCopy.addFirst(new Object());
        }
        super(testFormatsCopy.toArray());
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                value = (value instanceof TestFormat) ? ((TestFormat) value).Name : "Izveidot jaunu...";
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
    }
}
