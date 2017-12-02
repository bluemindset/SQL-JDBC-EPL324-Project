package com.horses.ui;

import com.horses.dbmanage.Config;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ViewSAUserLogin {

	public JFrame frmSystemAdminLogin;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSAUserLogin window = new ViewSAUserLogin();
					window.frmSystemAdminLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewSAUserLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSystemAdminLogin = new JFrame();
		frmSystemAdminLogin.setTitle("System Admin Login");
		frmSystemAdminLogin.setBounds(100, 100, 299, 198);
		frmSystemAdminLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSystemAdminLogin.getContentPane().setLayout(null);
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the following:");
		lblPleaseEnterThe.setBounds(10, 11, 267, 14);
		frmSystemAdminLogin.getContentPane().add(lblPleaseEnterThe);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(56, 36, 90, 14);
		frmSystemAdminLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(56, 72, 100, 14);
		frmSystemAdminLogin.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(126, 36, 86, 20);
		frmSystemAdminLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(126, 69, 86, 20);
		frmSystemAdminLogin.getContentPane().add(passwordField);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//TODO LOGIN
				try {
					Connection connection = DriverManager.getConnection(Config.connection_url, Config.DATABASE_USER_ID, Config.DATABASE_PASSWORD);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}


				frmSystemAdminLogin.dispose();
				ViewSAUserGUI window = new ViewSAUserGUI();
				window.frmSystemAdminInterface.setVisible(true);
				
				
			}
		});
		btnOk.setBounds(10, 111, 89, 23);
		frmSystemAdminLogin.getContentPane().add(btnOk);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSystemAdminLogin.dispose();
				ViewChooseUser window = new ViewChooseUser();
				window.frmChooseAUser.setVisible(true);
			}
		});
		btnBack.setBounds(167, 111, 89, 23);
		frmSystemAdminLogin.getContentPane().add(btnBack);
	}

}
