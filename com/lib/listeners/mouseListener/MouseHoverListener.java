package com.lib.listeners.mouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHoverListener implements MouseListener {
    private final JComponent parent;
    private Border originalBorder;
    private boolean mouseInside;
    private final Border HOVER_BORDER = BorderFactory.createLineBorder(Color.BLUE, 3);

    public MouseHoverListener (JComponent parent) {
        this.parent = parent;
        originalBorder = parent.getBorder();
        mouseInside = false;
    }

    @Override public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        parent.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        parent.setBorder(mouseInside?HOVER_BORDER:originalBorder);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    	originalBorder = parent.getBorder();
        parent.setBorder(HOVER_BORDER);
        mouseInside = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        parent.setBorder(originalBorder);
        mouseInside = false;
    }

}
