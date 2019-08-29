package org.unibl.etf.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.unibl.etf.model.User;

@SuppressWarnings("serial")
public class NewUserInfoForm extends JDialog {
	
	private User user;
	private MainForm mainForm;

	private JPanel contentPane;
	
	private JLabel lblUsername;
	private JLabel lblUsernameValue;
	private JLabel lblPassword;
	private JLabel lblPasswordValue;
	private JButton btnClose;

	/**
	 * Create the dialog.
	 */
	public NewUserInfoForm(User user, MainForm mainForm) {
		this.user = user;
		this.mainForm = mainForm;
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				NewUserInfoForm.this.windowClosing();
			}
		});
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 256, 400, 188);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		setTitle("Informacije o novom korisniku");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		initComponents();
		initButtonListener();
	}
	
	private void initComponents() {
		lblUsername = new JLabel("Korisničko ime: ");
		lblUsername.setFont(new Font(lblUsername.getFont().getFontName(), Font.PLAIN, 14));
		lblUsername.setBounds(30, 20, 300, 25);
		contentPane.add(lblUsername);
		
		lblUsernameValue = new JLabel(user.getUsername());
		lblUsernameValue.setFont(new Font(lblUsernameValue.getFont().getFontName(), Font.PLAIN, 16));
		lblUsernameValue.setBounds(160, 20, 300, 25);
		contentPane.add(lblUsernameValue);
		
		lblPassword = new JLabel("Lozinka: ");
		lblPassword.setFont(new Font(lblPassword.getFont().getFontName(), Font.PLAIN, 14));
		lblPassword.setBounds(30, 50, 300, 25);
		contentPane.add(lblPassword);
		
		lblPasswordValue = new JLabel(user.getPassword());
		lblPasswordValue.setFont(new Font(lblPasswordValue.getFont().getFontName(), Font.PLAIN, 16));
		lblPasswordValue.setBounds(160, 50, 300, 25);
		contentPane.add(lblPasswordValue);
		
		btnClose = new JButton("Zatvori");
		btnClose.setBackground(Color.WHITE);
		btnClose.setFont(new Font(btnClose.getFont().getFontName(), Font.PLAIN, 14));
		btnClose.setBounds(254, 100, 100, 30);
		btnClose.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(btnClose);
	}
	
	private void initButtonListener() {
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnCloseClicked();
			}
		});
	}
	
	private void btnCloseClicked() {
		setVisible(false);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	private void windowClosing() {
		mainForm.initUsersPane();
	}

}
