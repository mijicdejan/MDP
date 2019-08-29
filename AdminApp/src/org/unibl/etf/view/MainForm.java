package org.unibl.etf.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.MulticastSocket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;
import org.unibl.etf.model.Session;
import org.unibl.etf.model.User;
import org.unibl.etf.util.PropertyManager;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class MainForm extends JFrame {
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private String BASE_URL;
	private String DATE_TIME_FORMAT;
	private String SESSION_DURATION_FORMAT;
	private int PORT;
	private String ADDRESS;
	
	private HashMap<JButton, User> buttons;
	private ArrayList<User> users;
	private JButton pressedButton;

	private JPanel contentPane;
	
	private JPanel accountPane;
	private JPanel messagePane;
	private JPanel usersPane;
	private JScrollPane usersScroll;
	private JPanel dataPane;
	
	private JButton btnNewAccount;
	private JButton btnBlockUnblockAccount;
	
	private JTextArea taMessage;
	private JScrollPane messageScroll;
	private JButton btnSend;
	
	private JTable tblData;
	private DefaultTableModel dtm;
	private JScrollPane dataScroll;
	
	private JButton btnMonitoring;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		BASE_URL = PropertyManager.getInstance().getUsersBaseURL();
		DATE_TIME_FORMAT = PropertyManager.getInstance().getDateTimeFormat();
		SESSION_DURATION_FORMAT = PropertyManager.getInstance().getSessionDurationFormat();
		PORT = PropertyManager.getInstance().getMulticastPort();
		ADDRESS = PropertyManager.getInstance().getMulticastAddress();
		
		String keyStorePath = PropertyManager.getInstance().getKeyStorePath();
		String keyStorePassword = PropertyManager.getInstance().getKeyStorePassword();
		String trustStorePath = PropertyManager.getInstance().getTrustStorePath();
		String trustStorePassword = PropertyManager.getInstance().getTrustStorePassword();
		
		System.setProperty("javax.net.ssl.keyStore", keyStorePath);
		System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
		System.setProperty("javax.net.ssl.trustStore", trustStorePath);
		System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
		
		initConnection();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainForm.this.windowClosing();
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1000, 700);
		setLocationRelativeTo(null);
		setTitle("Početna");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		buttons = new HashMap<>();
		
		initComponents();
	}
	
	private void initConnection() {
		int port = PropertyManager.getInstance().getMonitoringPort();
		try {
			InetAddress address = InetAddress.getByName(PropertyManager.getInstance().getMonitoringAddress());
			SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket socket = (SSLSocket) factory.createSocket(address, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initComponents() {
		initAccountPane();
		initMessagesPane();
		initDataPane();
		initUsersPane();
		
		initButtonListeners();
	}
	
	private void initAccountPane() {
		btnNewAccount = new JButton("Kreiraj nalog");
		btnNewAccount.setBackground(Color.WHITE);
		btnNewAccount.setBounds(20, 15, 160, 30);
		btnNewAccount.setBorder(new LineBorder(Color.BLACK));
		
		btnBlockUnblockAccount = new JButton("Promijeni stanje naloga");
		btnBlockUnblockAccount.setBackground(Color.WHITE);
		btnBlockUnblockAccount.setBounds(20, 55, 160, 30);
		btnBlockUnblockAccount.setBorder(new LineBorder(Color.BLACK));
		
		accountPane = new JPanel();
		accountPane.setBackground(Color.WHITE);
		accountPane.setBounds(0, 0, 200, 100);
		accountPane.setBorder(new LineBorder(Color.BLACK));
		accountPane.setLayout(null);
		
		accountPane.add(btnNewAccount);
		accountPane.add(btnBlockUnblockAccount);
		contentPane.add(accountPane);
	}
	
	private void initMessagesPane() {
		taMessage = new JTextArea();
		taMessage.setWrapStyleWord(true);
		taMessage.setLineWrap(true);
		taMessage.setBounds(10, 10, 654, 80);
		
		messageScroll = new JScrollPane(taMessage);
		messageScroll.setBounds(10, 10, 654, 80);
		messageScroll.setBorder(new LineBorder(Color.BLACK));
		
		btnSend = new JButton("Pošalji");
		btnSend.setBackground(Color.WHITE);
		btnSend.setBounds(674, 35, 100, 30);
		btnSend.setBorder(new LineBorder(Color.BLACK));
		
		messagePane = new JPanel();
		messagePane.setBackground(Color.WHITE);
		messagePane.setBounds(200, 0, 784, 100);
		messagePane.setBorder(new LineBorder(Color.BLACK));
		messagePane.setLayout(null);
		
		messagePane.add(messageScroll);
		messagePane.add(btnSend);
		contentPane.add(messagePane);
	}
	
	@SuppressWarnings("unchecked")
	public void initUsersPane() {
		try {
			contentPane.remove(usersScroll);
			contentPane.repaint();
			contentPane.revalidate();
		} catch (Exception e) {}
		
		usersPane = new JPanel();
		usersPane.setBackground(Color.WHITE);
		usersPane.setBounds(0, 100, 200, 562);
		usersPane.setLayout(null);
		
		users = new ArrayList<>();
		try {
			InputStream is = new URL(BASE_URL).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONArray jsonArray = new JSONArray(jsonText);
			for(int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				User user = new Gson().fromJson(jsonObject.toString(), User.class);
				users.add(user);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		users.sort(Comparator.comparing(User::getFirstName).thenComparing(User::getLastName).thenComparing(User::getUsername));
		int size = users.size();
		int x = 20;
		if(size > 13) {
			x = 10;
		}
		int y = 10;
		for(int i = 0; i < size; i++) {
			User user = users.get(i);
			JButton btn = new JButton(user.getFirstName() + " " + user.getLastName());
			btn.setBorder(new LineBorder(Color.BLACK));
			btn.setBackground(Color.WHITE);
			btn.setToolTipText(user.getFirstName() + " " + user.getLastName() + "(" + user.getUsername() + ")");
			if(i == 0) {
				btn.setBackground(Color.LIGHT_GRAY);
				pressedButton = btn;
				try {
					InputStream is = new URL(BASE_URL + "/" + user.getUsername()).openStream();
					BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
					String jsonText = readAll(rd);
					JSONObject jsonObject = new JSONObject(jsonText);
					Map<String, String> sessions = new Gson().fromJson(jsonObject.toString(), Map.class);
					ArrayList<Session> sessionsList = new ArrayList<>();
					for(Map.Entry<String, String> entry : sessions.entrySet()) {
						sessionsList.add(new Session(entry.getValue(), entry.getKey()));
					}
					user.setSessions(sessionsList.toArray(new Session[sessionsList.size()]));
					initDataTable(user);
					boolean enable = false;
					for(Session s : sessionsList) {
						if("0".equals(s.getLogoutTime())) {
							enable = true;
						}
					}
					btnMonitoring.setEnabled(enable);
				} catch (MalformedURLException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			btn.setBounds(x, y, 160, 30);
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					btnClickedAction(e);
				}
			});
			buttons.put(btn, user);
			usersPane.add(btn);
			y += 40;
		}
		usersPane.setPreferredSize(new Dimension(180, y));
		
		usersScroll = new JScrollPane(usersPane);
		usersScroll.setBounds(0, 100, 200, 562);
		usersScroll.setBorder(new LineBorder(Color.BLACK));
		usersScroll.repaint();
		usersScroll.revalidate();
		contentPane.add(usersScroll);
	}
	
	private void initDataPane() {
		btnMonitoring = new JButton("Monitoring");
		btnMonitoring.setBackground(Color.WHITE);
		btnMonitoring.setBounds(674, 522, 100, 30);
		btnMonitoring.setBorder(new LineBorder(Color.BLACK));
		
		dataPane = new JPanel();
		dataPane.setBackground(Color.WHITE);
		dataPane.setBounds(200, 100, 784, 562);
		dataPane.setBorder(new LineBorder(Color.BLACK));
		dataPane.setLayout(null);
		
		dataPane.add(btnMonitoring);
		contentPane.add(dataPane);
	}
	
	@SuppressWarnings("unchecked")
	private void btnClickedAction(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		pressedButton.setBackground(Color.WHITE);
		btn.setBackground(Color.LIGHT_GRAY);
		pressedButton = btn;
		User user = buttons.get(btn);
		try {
			InputStream is = new URL(BASE_URL + "/" + user.getUsername()).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject jsonObject = new JSONObject(jsonText);
			Map<String, String> sessions = new Gson().fromJson(jsonObject.toString(), Map.class);
			ArrayList<Session> sessionsList = new ArrayList<>();
			for(Map.Entry<String, String> entry : sessions.entrySet()) {
				sessionsList.add(new Session(entry.getValue(), entry.getKey()));
			}
			user.setSessions(sessionsList.toArray(new Session[sessionsList.size()]));
			initDataTable(user);
			boolean enable = false;
			for(Session s : sessionsList) {
				if("0".equals(s.getLogoutTime())) {
					enable = true;
				}
			}
			btnMonitoring.setEnabled(enable);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void initDataTable(User user) {
		dtm = new DefaultTableModel();
		dtm.addColumn("Vrijeme prijave");
		dtm.addColumn("Vrijeme odjave");
		dtm.addColumn("Trajanje sesije");
		
		Session[] sessions = user.getSessions();
		ArrayList<Session> list = new ArrayList<>();
		for(Session s : sessions) {
			list.add(s);
		}
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
		Collections.sort(list, new Comparator<Session>() {
			public int compare(Session o1, Session o2) {
				LocalDateTime ldt1 = LocalDateTime.parse(o1.getLoginTime(), formatter);
				LocalDateTime ldt2 = LocalDateTime.parse(o2.getLoginTime(), formatter);
				Long seconds1 = ChronoUnit.SECONDS.between(ldt1, now);
				Long seconds2 = ChronoUnit.SECONDS.between(ldt2, now);
				return (seconds2).compareTo(seconds1);
			}
		});
		
		for(Session s : list) {
			String displayValue = "-";
			LocalDateTime end = LocalDateTime.now();
			if(!"0".equals(s.getLogoutTime())) {
				end = LocalDateTime.parse(s.getLogoutTime(), formatter);
				displayValue = s.getLogoutTime();
			}
			LocalDateTime start = LocalDateTime.parse(s.getLoginTime(), formatter);
			long seconds = ChronoUnit.SECONDS.between(start, end);
			// TODO Popraviti ispis
			int hour = (int) seconds / 3600;
			int minute = (int) (seconds % 3600) / 60;
			int second = (int) (seconds % 3600) % 60;
			LocalDateTime session = LocalDateTime.of(2019, 1, 1, hour, minute, second);
			DateTimeFormatter sessionFormatter = DateTimeFormatter.ofPattern(SESSION_DURATION_FORMAT);
			String displaySession = session.format(sessionFormatter);
			Object[] rowData = { s.getLoginTime(), displayValue, displaySession };
			dtm.addRow(rowData);
		}
		
		tblData = new JTable(dtm);
		tblData.setAutoCreateRowSorter(true);
		tblData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblData.setBackground(Color.WHITE);
		
		dataScroll = new JScrollPane(tblData);
		dataScroll.setBounds(10, 10, 764, 502);
		dataPane.add(dataScroll);
	}
	
	private void initButtonListeners() {
		btnNewAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewUserForm nuf = new NewUserForm(MainForm.this);
				nuf.setVisible(true);
			}
		});
		
		btnBlockUnblockAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BlockUnblockForm buf = new BlockUnblockForm(users, MainForm.this);
				buf.setVisible(true);
			}
		});
		
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSendClicked();
			}
		});
		
		btnMonitoring.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnMonitoringClicked();
			}
		});
	}
	
	private void btnSendClicked() {
		MulticastSocket socket = null;
		try {
			socket = new MulticastSocket();
			InetAddress address = InetAddress.getByName(ADDRESS);
			socket.joinGroup(address);
			String message = taMessage.getText();
			byte[] bytes = message.getBytes();
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
			socket.send(packet);
			taMessage.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void btnMonitoringClicked() {
		// TODO napraviti novu formu sa jednim labelom koji popunjava kompletnu formu
		// u label ce se stavljati svaka nova slika
		// prilikom kreiranja poslati monitoring poruku
		// konstruktor prima in i out
		// na close treba da se posalje end_monitoring
		
		User user = buttons.get(pressedButton);
		MonitoringForm mf = new MonitoringForm(in, out, user.getUsername());
		mf.setVisible(true);
		
		// TODO potrebno je i na gasenje admin aplikacije poslati end poruku
	}
	
	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	private void windowClosing() {
		try {
			out.writeObject("END");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
