package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CreateUser {

	public JFrame frmCreateUser;
	private JTextField txtUsernameSignUp;
	private JTextField txtUserIdSignUp;
	private JTextField txtPasswordSignUp;
	private JTextField txtConfirmPasswordSignUp;
	private Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser window = new CreateUser();
					window.frmCreateUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void addUser() {
        try {
            connection = DriverManager.getConnection(Config.connection_url, Config.DATABASE_USER_ID, Config.DATABASE_PASSWORD);

            String id = txtUserIdSignUp.getText();
           	String username = txtUsernameSignUp.getText();
           	String password = txtPasswordSignUp.getText();
           	// @id char(6), @username NVARCHAR(25), @firstName NVARCHAR(25), @lastName NVARCHAR(25)
           	try(CallableStatement cstmt = connection.prepareCall("{call insertR1User(?,?,?,?)}")) {

			}
            
        } catch (SQLException sex) {
        	sex.printStackTrace();
            System.err.println(sex.getMessage());
        }
	}

	/**
	 * Create the application.
	 */
	public CreateUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreateUser = new JFrame();
		frmCreateUser.setBounds(100, 100, 470, 409);
		frmCreateUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateUser.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(12, 12, 442, 358);
		frmCreateUser.getContentPane().add(panel);
		
		JLabel label = new JLabel("Username:");
		label.setBounds(53, 49, 94, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("User Id:");
		label_1.setBounds(53, 116, 94, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Password:");
		label_2.setBounds(53, 186, 94, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("ConfirmPassword:");
		label_3.setBounds(53, 244, 129, 14);
		panel.add(label_3);
		
		txtUsernameSignUp = new JTextField();
		txtUsernameSignUp.setColumns(10);
		txtUsernameSignUp.setBounds(205, 47, 184, 20);
		panel.add(txtUsernameSignUp);
		
		txtUserIdSignUp = new JTextField();
		txtUserIdSignUp.setColumns(10);
		txtUserIdSignUp.setBounds(205, 114, 184, 20);
		panel.add(txtUserIdSignUp);
		
		txtPasswordSignUp = new JTextField();
		txtPasswordSignUp.setColumns(10);
		txtPasswordSignUp.setBounds(205, 184, 184, 20);
		panel.add(txtPasswordSignUp);
		
		txtConfirmPasswordSignUp = new JTextField();
		txtConfirmPasswordSignUp.setColumns(10);
		txtConfirmPasswordSignUp.setBounds(205, 242, 184, 20);
		panel.add(txtConfirmPasswordSignUp);
		
		JButton button = new JButton("Sign Up");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUser();
			}
		});
		button.setBounds(53, 307, 336, 25);
		panel.add(button);
	}
}
