package com.lib.comp;

import com.lib.listeners.mouseListener.MouseHoverListener;

import javax.swing.*;

public class CheckBox extends JCheckBox {

    public CheckBox (String txt) {
        super(txt);

        addMouseListener(new MouseHoverListener(this));
    }

    public CheckBox (String txt, boolean selected) {
        super(txt, selected);

        addMouseListener(new MouseHoverListener(this));
    }

}
