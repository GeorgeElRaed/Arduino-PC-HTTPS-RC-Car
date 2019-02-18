package com.Arduino.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {
	private RCCar rcCar;

	public KeyboardInput(RCCar rcCar) {
		this.rcCar = rcCar;
	}

	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			//rcCar.motion.moveForward();
			System.out.println("W");
			break;
		case KeyEvent.VK_S:
			//rcCar.motion.moveBackward();
			System.out.println("S");
			break;
		case KeyEvent.VK_A:
			//rcCar.motion.turnLeft();
			System.out.println("A");
			break;
		case KeyEvent.VK_D:
			//rcCar.motion.turnRight();
			System.out.println("D");
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}

	}

	public void keyReleased(KeyEvent e) {
		rcCar.motion.stop();
	}
}
