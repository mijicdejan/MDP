package org.unibl.etf.monitoring;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

public class MonitoringThread extends Thread {
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String username;
	
	private boolean loggedIn;
	private boolean monitoring;
	
	public MonitoringThread(ObjectInputStream in, ObjectOutputStream out, String username) {
		this.in = in;
		this.out = out;
		this.username = username;
		loggedIn = true;
		monitoring = false;
	}
	
	@Override
	public void run() {
		try {
			out.writeObject(username);
			while(loggedIn) {
				String responseType = (String) in.readObject();
				if("END".equals(responseType)) {
					loggedIn = false;
				} else if("MONITORING".equals(responseType)) {
					try {
						Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
						Robot robot = new Robot();
						BufferedImage image;
						monitoring = true;
						listenForEnd();
						while(monitoring) {
							image = robot.createScreenCapture(screenSize);
							if(image != null) {
								ByteArrayOutputStream baos = new ByteArrayOutputStream();
								ImageIO.write(image, "png", baos);
								baos.flush();
								byte[] bytes = baos.toByteArray();
								out.writeObject(bytes);
								baos.close();
							}
						}
					} catch (AWTException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void listenForEnd() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(!"END_MONITORING".equals((String) in.readObject()));
					monitoring = false;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
