package org.unibl.etf.server;

import java.io.IOException;
import java.net.ServerSocket;

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import org.unibl.etf.util.PropertyManager;

public class SocketServer extends Thread {
	
	@Override
	public void run() {
		System.out.println("Socket server start");
		int socketPort = PropertyManager.getInstance().getSocketPort();
		SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		try {
			System.out.println("Socket server pre");
			ServerSocket socket = factory.createServerSocket(socketPort);
			System.out.println("Socket server post");
			while(true) {
				SSLSocket s = (SSLSocket) socket.accept();
				new ServerThread(s).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
