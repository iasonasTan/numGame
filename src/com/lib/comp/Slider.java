package com.lib.comp;

import com.lib.listeners.mouseListener.MouseHoverListener;
import com.main.GamePanel;
import com.main.Gui;
import com.main.Values;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

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
}
