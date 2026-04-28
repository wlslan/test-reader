package Gui;

import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel {
    public String name;
    protected MainFrame mainFrame;
    Scene() {
        super();
    }
    Scene(LayoutManager layout) {
        super(layout);
    }
}
