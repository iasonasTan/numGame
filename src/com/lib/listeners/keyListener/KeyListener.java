package com.lib.listeners.keyListener;

import java.awt.event.KeyEvent;
import java.security.Key;

public class KeyListener implements java.awt.event.KeyListener {
    private final ActionExecutor ae;

    public KeyListener (ActionExecutor ae) {
        this.ae = ae;
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        ae.exec(Action.ENTER);
    }

}
