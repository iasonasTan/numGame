package com.lib.comp;

import com.main.GamePanel;
import com.main.Values;

import java.awt.Container;
import java.awt.GraphicsConfiguration;

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

		if (gp instanceof Container) {
			Container c = (Container) gp;
			super.setContentPane(c);

			String n = c.getName();
			super.setTitle((n!=null)?n+title:"Window");
			super.pack();
		}

	}

}
