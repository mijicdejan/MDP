package org.unibl.etf.view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class MonitoringForm extends JDialog {

	private JPanel contentPane;
	
	private JLabel lblImage;
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String username;
	private boolean monitoring;

	/**
	 * Create the dialog.
	 */
	public MonitoringForm(ObjectInputStream in, ObjectOutputStream out, String username) {
		this.in = in;
		this.out = out;
		this.username = username;
		monitoring = true;
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MonitoringForm.this.windowClosing();
			}
		});
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		setTitle("Monitoring");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		lblImage = new JLabel();
		lblImage.setBounds(0, 0, getWidth(), getHeight());
		lblImage.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(lblImage);
		
		startMonitoring();
	}
	
	private void startMonitoring() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					out.writeObject("MONITORING");
					out.writeObject(username);
					out.flush();
					while(monitoring) {
						try {
							byte[] bytes = (byte[]) in.readObject();
							ImageIcon imageIcon = new ImageIcon(bytes);
							lblImage.setIcon(imageIcon);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void windowClosing() {
		try {
			monitoring = false;
			out.writeObject("END_MONITORING");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
