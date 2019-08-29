package org.unibl.etf.view;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.unibl.etf.model.Session;
import org.unibl.etf.model.User;
import org.unibl.etf.util.PropertyManager;

@SuppressWarnings("serial")
public class DataOverviewForm extends JDialog {
	
	private String DATE_TIME_FORMAT;
	private String SESSION_DURATION_FORMAT;
	private User user;

	private JPanel contentPane;
	
	private JTable tblData;
	private DefaultTableModel dtm;
	private JScrollPane dataScroll;

	/**
	 * Create the dialog.
	 */
	public DataOverviewForm(User user) {
		this.user = user;
		DATE_TIME_FORMAT = PropertyManager.getInstance().getDateTimeFormat();
		SESSION_DURATION_FORMAT = PropertyManager.getInstance().getSessionDurationFormat();
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 800, 600);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		setTitle("Pregled korišćenja aplikacije");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		initDataTable();
	}
	
	private void initDataTable() {
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
		dataScroll.setBounds(10, 10, 764, 542);
		contentPane.add(dataScroll);
	}

}
