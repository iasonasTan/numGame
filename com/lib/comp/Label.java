package com.lib.comp;

public class Label extends Input {
    public Label (String text) {
        super.setText(text);
        setEditable(false);
    }
    public Label () {
        setEditable(false);
    }
}
