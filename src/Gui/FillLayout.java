package Gui;

import Utils.Utils;
import org.w3c.dom.css.Rect;

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
    public Dimension preferredLayoutSize(Container container) {
        synchronized (container.getTreeLock()) {
            Dimension dim = new Dimension(0,0);
            int nmembers = container.getComponentCount();

            for (int i = 0 ; i < nmembers ; i++) {
                Component m = container.getComponent(i);
                if (m.isVisible()) {
                    Dimension d = m.getPreferredSize();
                    dim.height = Math.max(dim.height, d.height);
                    dim.width = Math.max(dim.width, d.width);
                }
            }
            Insets insets = container.getInsets();
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

            int top = insets.top;
            int bottom = parent.getHeight() - insets.bottom;
            int left = insets.left;
            int right = parent.getWidth() - insets.right;
            Rectangle rect=new Rectangle(left,top,right - left, bottom - top) ;
            int nmembers = parent.getComponentCount();
            for (int i = 0 ; i < nmembers ; i++) {
                Component m = parent.getComponent(i);
                if (m.isVisible()) {
                    Rectangle bounds=new Rectangle(left, top, right - left, bottom - top);
                    bounds=Utils.CenterFillRect(rect,bounds,Utils.Center.CENTER,fit);
                    m.setBounds(bounds);
                }
            }
        }
    }
}
