package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static Data.TestFormat.testFormats;

public class ListAnswer extends JList<Object> {
    public ListAnswer() {
        LinkedList<Object> testFormatsCopy= new LinkedList<Object>();
        for (Object item : testFormats) {
            testFormatsCopy.addLast(item);
        }
        super(testFormatsCopy.toArray());
        setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                value = (value instanceof TestFormat) ? ((TestFormat) value).Name : "Izveidot jaunu...";
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
    }
}
