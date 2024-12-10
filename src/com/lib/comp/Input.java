package com.lib.comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import com.lib.listeners.mouseListener.MouseHoverListener;
import com.main.Values;

public class Input extends JTextField implements FocusListener {
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
        addFocusListener(this);
    }

	@Override
	public void focusGained(FocusEvent e) {
		setBorder(BorderFactory.createLineBorder(Color.GREEN));
	}

	@Override
	public void focusLost(FocusEvent e) {
		setBorder(null);
	}

}
