package com.lib.listeners.keyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	ActionExecutor ae;
	
	public KeyHandler(ActionExecutor ae) {
		this.ae = ae;
	}
	
	public KeyHandler () {
		
	}
	
	public void setActionExecutor (ActionExecutor ae) {
		this.ae = ae;
	}

	@Override public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_ENTER:
			ae.exec(Action.PLAY);
			break;
			
		case KeyEvent.VK_DELETE:
				ae.exec(Action.RESET);
			break;
			
		case KeyEvent.VK_ESCAPE:
			ae.exec(Action.BACK);
			break;
			
		}
	}

	@Override public void keyReleased(KeyEvent e) {}
	

}
