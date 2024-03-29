package com.horses.ui;

import com.horses.dbmanage.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewR1UserLogin {

	public JFrame frmSystemUserSign;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1UserLogin window = new ViewR1UserLogin();
					window.frmSystemUserSign.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1UserLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 *
	 *
	 */
	private void initialize() {
		frmSystemUserSign = new JFrame();
		frmSystemUserSign.setTitle("System User Sign Up / Log In");
		frmSystemUserSign.setBounds(100, 100, 388, 334);
		frmSystemUserSign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSystemUserSign.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 349, 274);
		frmSystemUserSign.getContentPane().add(tabbedPane);
		
		JPanel logInPanel = new JPanel();
		tabbedPane.addTab("LOG IN", null, logInPanel, null);
		logInPanel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(84, 89, 94, 14);
		logInPanel.add(lblUsername);
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the following:");
		lblPleaseEnterThe.setBounds(10, 39, 311, 14);
		logInPanel.add(lblPleaseEnterThe);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(174, 86, 86, 20);
		logInPanel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(84, 134, 80, 14);
		logInPanel.add(lblPassword);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener( (e) -> {
				//TODO LOGIN
				Connection connection = null;
				try {
					connection = DriverManager.getConnection(Config.connection_url, Config.DATABASE_USER_ID, Config.DATABASE_PASSWORD);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				//username, password
				try(CallableStatement cstmt = connection.prepareCall("{call find_user (?,?)}"))  {
					cstmt.setString(1,textFieldUsername.getText());
					cstmt.setString(2,passwordField.getText());
					ResultSet rs = cstmt.executeQuery();
					if(rs.next()){
						String userid = rs.getString(1);
						CurrentUserData.setCurUserid(userid);
						System.out.println("User id is " +userid);
						System.out.println("The current user id is " + CurrentUserData.getCurUserid());
						frmSystemUserSign.dispose();
						ViewR1UserGUI window = new ViewR1UserGUI();
						window.frmPleaseChooseA.setVisible(true);
					}
					else {
						System.out.println("No such password username combination");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}			
		});
		btnOk.setBounds(75, 188, 89, 23);
		logInPanel.add(btnOk);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSystemUserSign.dispose();
				ViewChooseUser window = new ViewChooseUser();
				window.frmChooseAUser.setVisible(true);
			}
		});
		btnBack.setBounds(185, 188, 89, 23);
		logInPanel.add(btnBack);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 131, 86, 20);
		logInPanel.add(passwordField);
	}
}
