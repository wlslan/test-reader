package Gui;

import Data.DelayedCreator;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectRect extends JComponent {
    static final Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
    private DelayedCreator<Utils.UnitRect> creator = new DelayedCreator<Utils.UnitRect>() {
        @Override
        public Utils.UnitRect Result() {
            return curRect;
        }
    };
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!IsActive()) {return;}
        if (rectStart==null || rectEnd==null) {return;}
        g.setColor(Color.BLACK);
        Rectangle drawRect= Utils.FromPoints(rectStart,rectEnd);
        g.drawRect(drawRect.x,drawRect.y,drawRect.width,drawRect.height);
    }

    private Utils.UnitRect curRect;
    private Point rectStart,rectEnd;
    private boolean active;
    public boolean IsActive() {
        return active;
    }
    private void activate() {
        curRect=null;
        rectStart=null;
        rectEnd=null;
        active=true;
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

    }
    private void deactivate(boolean finished) {
        if (finished) {
            Dimension size = getSize();
            double x = rectStart.getX() / size.width,
                    y = rectStart.getY() / size.height;
            double width = rectEnd.getX() / size.width - x,
                    height = rectEnd.getY() / size.height - y;

            curRect = new Utils.UnitRect(x, y, width, height);
        }
        active=false;
        setCursor(null);
        repaint();
        creator.fireEvent();
    }
    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (!IsActive()) {
                return;
            }
            if (rectStart != null) {
                deactivate(true);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };
    private MouseMotionListener mouseMotionListener = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (!IsActive()) {
                return;
            }
            rectEnd=e.getPoint();
            repaint();

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (!IsActive()) {
                return;
            }
            rectStart=e.getPoint();
            rectEnd=e.getPoint();
            repaint();
        }
    };
    public SelectRect () {
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseMotionListener);
    }
    public void AddListener(DelayedCreator.Listener<Utils.UnitRect> listener) {
        creator.addListener(listener);
    }
    public void Create() {
        activate();
    }
    public void Cancel() {
        deactivate(false);
    }
};