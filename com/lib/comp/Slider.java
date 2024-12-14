package com.lib.comp;

import com.lib.listeners.mouseListener.MouseHoverListener;
import com.lib.standard.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class Slider extends JPanel implements Gui {
    private JLabel text;
    private JSlider input;
    private String hint;

    public Slider (String hint) {
        this.hint = hint;

        initComp();
        setLSD();
        listen();
        addComp();
    }

    public void setValues (int min, int max, int value) {
        input.setMinimum(min);
        input.setMaximum(max);
        input.setValue(value);
    }

    public int getValue () {
        return input.getValue();
    }

    public void setMaximum (int max) {
        input.setMaximum(max);
    }

    @Override
    public void initComp() {
        text = new JLabel(hint);
        input = new JSlider();
    }

    @Override
    public void setLSD() {
        setLayout(new GridLayout(2,1));
    }

    @Override
    public void listen() {
        addMouseListener(new MouseHoverListener(this));
        input.addChangeListener(e -> {
            text.setText("hint: "+hint+", val: "+input.getValue());
        });
    }

    @Override
    public void addComp() {
        add(text);
        add(input);
    }

    @Override public void setCompTheme() {}

    @Override
    public void setEnabled (boolean e) {
        super.setEnabled(e);
        input.setEnabled(e);
        text.setEnabled(e);
    }

    @Override
    public void addMouseListener (MouseListener ml) {
        super.addMouseListener(ml);
        input.addMouseListener(ml);
    }

    @Override
    public void setBackground (Color c) {
        super.setBackground(c);
        if (input!=null)
            input.setBackground(c);
    }

    @Override
    public void setForeground (Color c) {
        super.setForeground(c);

        if (input!=null)
            input.setForeground(c);
        if (text!=null)
            text.setForeground(c);
    }

    public void setValue (int val) {
        input.setValue(val);
    }

}
