package com.starxg.browserfx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClosableTabbedPane extends JTabbedPane implements MouseListener {

    private CloseListener closeListener;

    public ClosableTabbedPane setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
        return this;
    }

    public static void main(String args[]) {
        JFrame f = new JFrame();

        ClosableTabbedPane closableTabbedPane = new ClosableTabbedPane();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        closableTabbedPane.addTab("不带关闭按钮", p1);

        closableTabbedPane.addTabWithClose("带关闭按钮", p2);

        f.getContentPane().add(closableTabbedPane);
        f.setSize(500, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public ClosableTabbedPane() {
        super();
        addMouseListener(this);
    }

    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, null, component);
    }

    public void addTabWithClose(String title, Component component) {
        super.addTab(title, new CloseTabIcon(), component);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int tabNumber = getUI().tabForCoordinate(this, e.getX(), e.getY());
        if (tabNumber < 0) {
            return;
        }
        if (getIconAt(tabNumber) != null) {
            Rectangle rect = ((CloseTabIcon) getIconAt(tabNumber)).getBounds();
            if (rect.contains(e.getX(), e.getY())) {
                this.removeTabAt(tabNumber);
                if (closeListener != null) {
                    closeListener.onClose(tabNumber);
                }
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public interface CloseListener {
        void onClose(int index);
    }

}

class CloseTabIcon implements Icon {

    private int x_pos;
    private int y_pos;
    private final int width;
    private final int height;

    public CloseTabIcon() {
        width = 16;
        height = 16;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        this.x_pos = x;
        this.y_pos = y;
        Color col = g.getColor();
        g.setColor(Color.WHITE.darker());
        int y_p = y + 2;

        g.drawLine(x + 1, y_p, x + 12, y_p);
        g.drawLine(x + 1, y_p + 13, x + 12, y_p + 13);
        g.drawLine(x, y_p + 1, x, y_p + 12);
        g.drawLine(x + 13, y_p + 1, x + 13, y_p + 12);
        //以上四条是Icon的边框
        g.drawLine(x + 3, y_p + 3, x + 10, y_p + 10);
        g.drawLine(x + 3, y_p + 4, x + 9, y_p + 10);
        g.drawLine(x + 4, y_p + 3, x + 10, y_p + 9);
        g.drawLine(x + 10, y_p + 3, x + 3, y_p + 10);
        g.drawLine(x + 10, y_p + 4, x + 4, y_p + 10);
        g.drawLine(x + 9, y_p + 3, x + 3, y_p + 9);
        g.setColor(col);
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x_pos, y_pos, width, height);
    }
}

