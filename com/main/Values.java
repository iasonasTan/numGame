package com.main;

import java.awt.*;
import java.util.Properties;

public class Values {

	public static class Preferences {
		public static final int PREFERRED_MAXIMUM_VALUE = 300;
		public static final int MAXIMUM_USERS = 24;
	}

	public static class App {
		public static final String APP_NAME = "NumGame";
		public static final double APP_VERSION = 8.5;
	}

	public static class Colors {
		//public static final Color INPUT_BACKGROUND = new Color(70, 71, 71);
		public static final Color INPUT_BACKGROUND = Color.WHITE;
		public static final Color INPUT_FOREGROUND = Color.BLACK;
		public static final Color DARK_BACKGROUND1 = new Color(0, 0, 0);
		public static final Color LIGHT_BACKGROUND1 = new Color(58, 58, 58);
		public static final Color DARK_BACKGROUND2 = new Color(0x8C8C8C);
		public static final Color LIGHT_BACKGROUND2 = new Color(0xFFFFFF);
	}

	public static class Dimensions {
		public static final Dimension GET_SETTINGS_SIZE = new Dimension(500, 500);
		public static final Dimension INPUT_SIZE = new Dimension(200, 50);
		public static final Dimension BUTTON_SIZE = new Dimension(200, 50);
	}

}
