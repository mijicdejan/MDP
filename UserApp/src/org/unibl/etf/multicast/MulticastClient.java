package org.unibl.etf.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import org.unibl.etf.util.PropertyManager;
import org.unibl.etf.view.MainForm;

public class MulticastClient extends Thread {
	
	private int PORT;
	private String ADDRESS;
	private MainForm mainForm;
	
	public MulticastClient(MainForm mainForm) {
		PORT = PropertyManager.getInstance().getMulticastPort();
		ADDRESS = PropertyManager.getInstance().getMulticastAddress();
		this.mainForm = mainForm;
	}
	
	@Override
	public void run() {
		MulticastSocket socket = null;
		byte[] buf = new byte[256];
		try {
			socket = new MulticastSocket(PORT);
			InetAddress address = InetAddress.getByName(ADDRESS);
			socket.joinGroup(address);
			while(true) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				String received = new String(packet.getData(), 0, packet.getLength());
				mainForm.addNotification(received);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
