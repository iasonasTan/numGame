package com.lib.comp;

import com.lib.listeners.mouseListener.MouseHoverListener;
import com.main.Values;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class Button extends JButton {

	public Button() {
		config();
	}

	public Button(Icon icon) {
		super(icon);
		config();
	}

	public Button(String text) {
		super(text);
		config();
	}

	private void config () {
		addMouseListener(new MouseHoverListener(this));
		setPreferredSize(Values.Dimensions.BUTTON_SIZE);
	}

}
