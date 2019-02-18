package com.Arduino.main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;

import org.sintef.jarduino.DigitalPin;
import org.sintef.jarduino.JArduino;
import org.sintef.jarduino.PinMode;

public class RCCar extends JArduino {
	public static final DigitalPin backInput1 = DigitalPin.PIN_5, backInput2 = DigitalPin.PIN_6,
			frontInput1 = DigitalPin.PIN_9, frontInput2 = DigitalPin.PIN_10;
	
	public static final DigitalPin backEnable = DigitalPin.PIN_3, frontEnable = DigitalPin.PIN_11;
	
	public Motion motion;
	
	public static boolean pressed = false;

	public RCCar(String port) {
		super(port);
		motion = new Motion(this);
		JFrame frame = new JFrame("test");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.requestFocus();
		frame.addKeyListener(new KeyboardInput(this));
	}

	@Override
	protected void setup() {
		
		pinMode(backEnable, PinMode.OUTPUT);
		pinMode(frontEnable, PinMode.OUTPUT);
		pinMode(backInput1, PinMode.OUTPUT);
		pinMode(backInput2, PinMode.OUTPUT);
		pinMode(frontInput1, PinMode.OUTPUT);
		pinMode(frontInput2, PinMode.OUTPUT);
		
	}

	@Override
	protected void loop() {
	
	}

	public static void main(String[] args) {
		executePost("http://192.168.137.50/W", "");//Use Arduino's IP Set Before
		JArduino arduino = new RCCar("COM3");
		arduino.runArduinoProcess();
	}
	public static String executePost(String targetURL, String urlParameters) {
		  HttpURLConnection connection = null;

		  try {
		    //Create connection
		    URL url = new URL(targetURL);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", 
		        "application/x-www-form-urlencoded");

		    connection.setRequestProperty("Content-Length", 
		        Integer.toString(urlParameters.getBytes().length));
		    connection.setRequestProperty("Content-Language", "en-US");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    wr.writeBytes(urlParameters);
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    return response.toString();
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  } finally {
		    if (connection != null) {
		      connection.disconnect();
		    }
		  }
		}

}
