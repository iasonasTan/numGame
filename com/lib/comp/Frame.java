package com.lib.comp;

import com.lib.standard.GamePanel;

import java.awt.*;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private String title = "";

	public Frame() {
	}

	public Frame(GraphicsConfiguration gc) {
		super(gc);
	}

	public Frame(String title) {
		super(title);
		this.title = " - "+title;
	}

	public Frame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		this.title = " - "+title;
	}

	public void setContentPane (GamePanel gp) {

		if (gp instanceof Container c) { // auto case and define
            super.setContentPane(c);

			String n = c.getName();
			super.setTitle((n!=null)?n+title:"Window");

			Dimension cS = c.getSize();
			if (cS.width+cS.height>40)
				super.setSize(c.getSize());

		}

	}

}
