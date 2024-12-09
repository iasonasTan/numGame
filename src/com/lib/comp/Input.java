package com.lib.comp;

import com.lib.listeners.mouseListener.MouseHoverListener;
import com.main.Values;

import javax.swing.*;
import java.awt.*;

public class Input extends JTextField {
    private String HINT;

    public Input() {
        config();
    }

    public Input(String hint, String text) {
        this.HINT = hint;
        super.setText(text);
        config();
    }

    public Input(String hint) {
        this.HINT = hint;
        config();
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty()&&HINT!=null) {
            g.setColor(Color.WHITE);
            g.drawString(HINT, 5, 15);
        }
    }

    private void config () {
        addMouseListener(new MouseHoverListener(this));
        setPreferredSize(Values.Dimensions.INPUT_SIZE);
        setForeground(Values.Colors.INPUT_FOREGROUND);
        setBackground(Values.Colors.INPUT_BACKGROUND);
        setForeground(Values.Colors.INPUT_FOREGROUND);
    }

}
