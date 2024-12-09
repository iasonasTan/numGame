package com.main;

import com.lib.comp.Frame;

import javax.swing.*;
import java.awt.*;

public class Main {
	static GamePanel gp;
	static Frame f;

	public static void main(String[] args) {

		f = new Frame(Values.App.APP_NAME+" "+Values.App.APP_VERSION);

		setContentPane(new GetSettingsPanel());

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 500);
		f.setVisible(true);

	}

	public static void setContentPane (GamePanel gp) {
		Main.gp = gp;
		f.setContentPane(Main.gp);
	}

}
