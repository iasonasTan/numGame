package com.user;

import com.lib.comp.NumberInput;
import com.lib.comp.Label;
import com.lib.util.Brain;
import com.settings.Settings;

import java.awt.*;

public class User {

    private Brain b;
    private NumberInput inp;
    private Label output;
    private int id;
    private String name;
    private boolean banned;
    private int current;
    private int previous;
    private int nearest;

    public User (NumberInput inp, Label output, int ID) {
        this.inp = inp;
        this.output = output;
        id = ID;
        banned = false;
        current = 0;
        previous = 0;
        nearest = 0;
        b = new Brain();

    }

    public boolean next () {
        try {
            current = Integer.parseInt(inp.getText());
            output.setText(current+"");
            inp.setText("");
            output.setForeground(Color.GREEN);
        } catch (NumberFormatException e) {
            output.setForeground(Color.RED);
            output.setText("enter a number");
            return false;
        }

        if (current>Settings.maxVal) {
            output.setForeground(Color.RED);
            output.setText("val must be less than "+Settings.maxVal);
            return false;
        }

        if (current<0) {
            output.setForeground(Color.RED);
            output.setText("val must be more than 0");
            return false;
        }

        nearest = b.getValue(nearest, current, Settings.secretNum);
        output.setText(output.getText()+" n:"+nearest);
        output.setText(output.getText()+" m:"+(current>Settings.secretNum));
        return true;
    }

    public int getValue () {
        return current;
    }

    public int getNearest () {
        return nearest;
    }

    public int getID () {
        return id;
    }

}
