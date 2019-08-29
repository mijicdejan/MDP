package org.unibl.etf.monitoring;

import java.io.IOException;
import java.net.ServerSocket;

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import org.unibl.etf.util.PropertyManager;

public class MonitoringUserServer extends Thread {
	
	@Override
	public void run() {
		System.out.println("Monitoring server start");
		int monitoringPort = PropertyManager.getInstance().getMonitoringUserPort();
		SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		try {
			System.out.println("Monitoring server pre");
			ServerSocket socket = factory.createServerSocket(monitoringPort);
			System.out.println("Monitoring server post");
			while(true) {
				System.out.println("Monitoring server pre accept");
				SSLSocket s = (SSLSocket) socket.accept();
				new MonitoringUserThread(s).start();
				System.out.println("Monitoring server post accept");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
