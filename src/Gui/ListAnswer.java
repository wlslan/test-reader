package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import static Data.TestFormat.testFormats;

public class ListAnswer extends JList<Object> {
    public ListAnswer(TestFormat testFormat) {
        DefaultListModel<Object> listModel = new DefaultListModel<Object>();
        for (TestFormat.Answer answer : testFormat.answers) {
            listModel.addElement(answer);
        }
        super(listModel);
        setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

    }
}
