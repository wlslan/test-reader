package Gui;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;

public class FillLayout implements java.awt.LayoutManager {
    private Utils.Fit fit;
    public FillLayout() {
        this(Utils.Fit.FILL);
    }
    public FillLayout(Utils.Fit fit) {
        this.fit=fit;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        return;
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        return;
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        synchronized (parent.getTreeLock()) {
            Dimension dim = new Dimension(0, 0);
            int nmembers = parent.getComponentCount();

            for (int i = 0 ; i < nmembers ; i++) {
                Component m = parent.getComponent(i);
                if (m.isVisible()) {
                    Dimension d = m.getPreferredSize();
                    dim.height = Math.max(dim.height, d.height);
                    dim.width = Math.max(dim.width, d.width);
                }
            }
            Insets insets = parent.getInsets();
            dim.width += insets.left + insets.right;
            dim.height += insets.top + insets.bottom;
            return dim;
        }
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        synchronized (parent.getTreeLock()) {
            Dimension dim = new Dimension(0, 0);
            int nmembers = parent.getComponentCount();

            for (int i = 0 ; i < nmembers ; i++) {
                Component m = parent.getComponent(i);
                if (m.isVisible()) {
                    Dimension d = m.getMinimumSize();
                    dim.height = Math.max(dim.height, d.height);
                    dim.width = Math.max(dim.width, d.width);
                }
            }
            Insets insets = parent.getInsets();
            dim.width += insets.left + insets.right;
            dim.height += insets.top + insets.bottom;
            return dim;
        }
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int nmembers = parent.getComponentCount();
            Dimension dim=parent.getPreferredSize();
            Point pos = new Point(insets.left, insets.top);
            for (int i = 0 ; i < nmembers ; i++) {
                Component m = parent.getComponent(i);
                if (m.isVisible()) {
                    Dimension target=m.getPreferredSize();
                    Utils.FitDimension(target,dim,fit);
                    m.setSize(target);
                    Point targetPos=new Point(pos);
                    targetPos.x+=(dim.width-target.width)/2;
                    targetPos.y+=(dim.height-target.height)/2;
                    m.setLocation(targetPos);

                }
            }
        }
    }
}
