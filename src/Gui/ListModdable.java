package Gui;

import Data.TestFormat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListModdable extends JList<Object> {
    private JButton buttonModify,buttonAdd,buttonRemove;
    private class EditorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Object currentFormat = DialogSelectFormat.Open(mainFrame);
            if (currentFormat == null) {
                return;
            }
            mainFrame.sceneEditor.OpenFormat((TestFormat) currentFormat);
            mainFrame.ChangeScene(mainFrame.sceneEditor);
        }
    }
    public ListModdable() {
        super();
        buttonModify=new JButton("Rediģēt");
        buttonAdd=new JButton("Pievienot");
        buttonRemove=new JButton("Izdzēst");
    }
}
