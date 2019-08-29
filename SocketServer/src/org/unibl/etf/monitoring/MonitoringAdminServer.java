package org.unibl.etf.monitoring;

import java.io.IOException;
import java.net.ServerSocket;

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import org.unibl.etf.util.PropertyManager;

public class MonitoringAdminServer extends Thread {
	
	@Override
	public void run() {
		System.out.println("Monitoring server start");
		int monitoringPort = PropertyManager.getInstance().getMonitoringAdminPort();
		SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		try {
			System.out.println("Monitoring server pre");
			ServerSocket socket = factory.createServerSocket(monitoringPort);
			System.out.println("Monitoring server post");
			while(true) {
				SSLSocket s = (SSLSocket) socket.accept();
				new MonitoringAdminThread(s).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
