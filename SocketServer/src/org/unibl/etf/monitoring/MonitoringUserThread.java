package org.unibl.etf.monitoring;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

import org.unibl.etf.main.Main;

public class MonitoringUserThread extends Thread {
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public MonitoringUserThread(SSLSocket socket) {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			String username = (String) in.readObject();
			System.out.println("MONITORING USER THREAD: " + username);
			Main.addMonitorIn(username, in);
			Main.addMonitorOut(username, out);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
