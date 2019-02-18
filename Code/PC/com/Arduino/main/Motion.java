package com.Arduino.main;

import org.sintef.jarduino.DigitalState;
import org.sintef.jarduino.PWMPin;

public class Motion {

	private RCCar rcCar;

	public Motion(RCCar rcCar) {
		this.rcCar = rcCar;
	}

	public void moveBackward() {
		rcCar.analogWrite(PWMPin.PWM_PIN_3, (byte)(255));
		rcCar.analogWrite(PWMPin.PWM_PIN_5,(byte)255);
		rcCar.analogWrite(PWMPin.PWM_PIN_6,(byte)0);
	}

	public void moveForward() {
		rcCar.analogWrite(PWMPin.PWM_PIN_3, (byte)(255/2));
		rcCar.digitalWrite(RCCar.backInput1, DigitalState.LOW);
		rcCar.digitalWrite(RCCar.backInput2, DigitalState.HIGH);
	}

	public void turnRight() {
		rcCar.digitalWrite(RCCar.frontEnable, DigitalState.HIGH);
		rcCar.digitalWrite(RCCar.frontInput1, DigitalState.LOW);
		rcCar.digitalWrite(RCCar.frontInput2, DigitalState.HIGH);
	}

	public void turnLeft() {
		rcCar.digitalWrite(RCCar.frontEnable, DigitalState.HIGH);
		rcCar.digitalWrite(RCCar.frontInput1, DigitalState.HIGH);
		rcCar.digitalWrite(RCCar.frontInput2, DigitalState.LOW);
	}
	public void stop() {
				rcCar.digitalWrite(RCCar.backEnable, DigitalState.HIGH);
				rcCar.digitalWrite(RCCar.backInput1, DigitalState.LOW);
				rcCar.digitalWrite(RCCar.backInput2, DigitalState.LOW);
				rcCar.digitalWrite(RCCar.frontEnable, DigitalState.HIGH);
				rcCar.digitalWrite(RCCar.frontInput1, DigitalState.LOW);
				rcCar.digitalWrite(RCCar.frontInput2, DigitalState.LOW);
	}
}
