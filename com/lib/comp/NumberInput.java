package com.lib.comp;

import java.awt.*;

import javax.swing.JTextField;

import com.lib.listeners.mouseListener.MouseHoverListener;
import com.main.Values;

public class NumberInput extends Input {

	public NumberInput() {
	}

	public NumberInput(String hint, String text) {
		super(hint, text);
	}

	public NumberInput(String hint) {
		super(hint);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		checkInput();
	}

	public void checkInput () {
		String text = this.getText();
		StringBuilder output = new StringBuilder();
		for (char c : text.toCharArray()) {
			for (int i = 0; i < 10; i++) {
				if (c == (i+"").toCharArray()[0]) {
					output.append(c);
				}
			}
		}
		setText(output.toString());
	}

}
