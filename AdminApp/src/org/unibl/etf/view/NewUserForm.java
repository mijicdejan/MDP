package org.unibl.etf.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.xml.rpc.ServiceException;

import org.unibl.etf.api.AccountsApi;
import org.unibl.etf.api.AccountsApiServiceLocator;
import org.unibl.etf.model.User;

@SuppressWarnings("serial")
public class NewUserForm extends JDialog {
	
	private MainForm mainForm;

	private JPanel contentPane;
	
	private JLabel lblFirstName;
	private JTextField tfFirstName;
	private JLabel lblLastName;
	private JTextField tfLastName;
	private JButton btnSave;

	/**
	 * Create the dialog.
	 */
	public NewUserForm(MainForm mainForm) {
		this.mainForm = mainForm;
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(412, 236, 376, 228);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		setTitle("Novi korisnik");
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
		lblFirstName = new JLabel("Ime: ");
		lblFirstName.setFont(new Font(lblFirstName.getFont().getFontName(), Font.PLAIN, 16));
		lblFirstName.setBounds(30, 20, 300, 25);
		contentPane.add(lblFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setBounds(30, 45, 300, 30);
		contentPane.add(tfFirstName);
		
		lblLastName = new JLabel("Prezime: ");
		lblLastName.setFont(new Font(lblLastName.getFont().getFontName(), Font.PLAIN, 16));
		lblLastName.setBounds(30, 75, 300, 25);
		contentPane.add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setBounds(30, 100, 300, 30);
		contentPane.add(tfLastName);
		
		btnSave = new JButton("Sačuvaj");
		btnSave.setBackground(Color.WHITE);
		btnSave.setFont(new Font(btnSave.getFont().getFontName(), Font.PLAIN, 14));
		btnSave.setBounds(230, 140, 100, 30);
		btnSave.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(btnSave);
	}
	
	private void initButtonListener() {
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSaveClicked();
			}
		});
	}
	
	private void btnSaveClicked() {
		String firstName = tfFirstName.getText();
		String lastName = tfLastName.getText();
		if("".equals(firstName)) {
			JOptionPane.showMessageDialog(null, "Niste unijeli ime korisnika.");
		} else if("".equals(lastName)) {
			JOptionPane.showMessageDialog(null, "Niste unijeli prezime korisnika.");
		} else {
			AccountsApiServiceLocator loc = new AccountsApiServiceLocator();
			try {
				AccountsApi service = loc.getAccountsApi();
				User user = service.createAccount(firstName, lastName);
				NewUserInfoForm nuif = new NewUserInfoForm(user, mainForm);
				this.dispose();
				nuif.setVisible(true);
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

}
