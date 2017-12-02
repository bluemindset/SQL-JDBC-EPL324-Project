package com.horses.ui;

import com.horses.dbmanage.Config;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class CreateUser {

	public JFrame frmCreateUser;
	private JTextField txtUsernameSignUp;
	private JTextField txtUserIdSignUp;
	private JTextField txtPasswordSignUp;
	private JTextField txtConfirmPasswordSignUp;
	private Connection connection;
	private JTextField lblFirstNameSignUp;
	private JTextField txtLastNameSignUp;
	private final int ID_LIMIT = 8,USERNAME_LIMIT = 25, PASSWORD_LIMIT =50, FIRSTNAME_LIMIT =25, LASTNAME_LIMIT = 25;
	private final int DUPLICATE_PK = 1, DUPLICATE_USERNAME = 2, NO_ERROR = 0;
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
           	String firstName = lblFirstNameSignUp.getText();
           	String lastName = txtLastNameSignUp.getText();
           	// @id char(6), @username NVARCHAR(25), @firstName NVARCHAR(25), @lastName NVARCHAR(25)
           	try(CallableStatement cstmt = connection.prepareCall("{call insertR1User(?,?,?,?,?,?)}")) {
           		//ERROR PARAMETER IF 1 duplicate id , if 2 duplicate PK if 0 all ok
           		cstmt.registerOutParameter(6, Types.INTEGER);
				cstmt.setString(1, id);
				cstmt.setString(2, username);
				cstmt.setString(3, password);
				cstmt.setString(4, firstName);
				cstmt.setString(5, lastName);
				cstmt.execute();
				if(cstmt.getInt(6) == NO_ERROR) {
					System.out.println("No error");
					frmCreateUser.dispose();
					ViewR1UserLogin window = new ViewR1UserLogin();
					window.frmSystemUserSign.setVisible(true);
				}
				else if (cstmt.getInt(6) == DUPLICATE_PK) {
					System.out.println("DUPLICATE_PK");
				}
				else if ( cstmt.getInt(6) == DUPLICATE_USERNAME) {
					System.out.println("DUPLICATE_USERNAME");
				}
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
		label.setBounds(57, 14, 94, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("User Id:");
		label_1.setBounds(57, 81, 94, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Password:");
		label_2.setBounds(57, 205, 94, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("ConfirmPassword:");
		label_3.setBounds(57, 237, 129, 14);
		panel.add(label_3);
		
		txtUsernameSignUp = new JTextField();
		txtUsernameSignUp.setColumns(10);
		txtUsernameSignUp.setBounds(209, 12, 184, 20);
		txtUsernameSignUp.setDocument( new JTextFieldLimit(USERNAME_LIMIT));
		panel.add(txtUsernameSignUp);
		
		txtUserIdSignUp = new JTextField();
		txtUserIdSignUp.setColumns(10);
		txtUserIdSignUp.setBounds(209, 79, 184, 20);
		txtUserIdSignUp.setDocument(new JTextFieldLimit(ID_LIMIT));
		panel.add(txtUserIdSignUp);
		
		txtPasswordSignUp = new JTextField();
		txtPasswordSignUp.setColumns(10);
		txtPasswordSignUp.setBounds(209, 203, 184, 20);
		txtPasswordSignUp.setDocument(new JTextFieldLimit(PASSWORD_LIMIT));
		panel.add(txtPasswordSignUp);
		
		txtConfirmPasswordSignUp = new JTextField();
		txtConfirmPasswordSignUp.setColumns(10);
		txtConfirmPasswordSignUp.setBounds(209, 235, 184, 20);
		txtConfirmPasswordSignUp.setDocument(new JTextFieldLimit(PASSWORD_LIMIT));
		panel.add(txtConfirmPasswordSignUp);
		
		JButton button = new JButton("Sign Up");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUser();
			}
		});
		button.setBounds(57, 272, 336, 25);
		panel.add(button);
		
		lblFirstNameSignUp = new JTextField();
		lblFirstNameSignUp.setColumns(10);
		lblFirstNameSignUp.setBounds(209, 173, 184, 20);
		lblFirstNameSignUp.setDocument(new JTextFieldLimit(FIRSTNAME_LIMIT));
		panel.add(lblFirstNameSignUp);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(57, 175, 94, 14);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(57, 131, 94, 14);
		panel.add(lblLastName);
		
		txtLastNameSignUp = new JTextField();
		txtLastNameSignUp.setColumns(10);
		txtLastNameSignUp.setBounds(209, 129, 184, 20);
		txtLastNameSignUp.setDocument(new JTextFieldLimit(LASTNAME_LIMIT));
		panel.add(txtLastNameSignUp);
	}
}
