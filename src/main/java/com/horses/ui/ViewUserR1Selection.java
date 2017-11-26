package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewUserR1Selection {

	private JFrame frmSelectAnOption;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUserR1Selection window = new ViewUserR1Selection();
					window.frmSelectAnOption.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewUserR1Selection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSelectAnOption = new JFrame();
		frmSelectAnOption.setTitle("SELECT AN OPTION");
		frmSelectAnOption.setBounds(100, 100, 265, 214);
		frmSelectAnOption.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSelectAnOption.getContentPane().setLayout(null);
		
		JButton btnSignUp = new JButton("SIGN UP");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSignUp.setBounds(77, 27, 89, 23);
		frmSelectAnOption.getContentPane().add(btnSignUp);
		
		JButton btnLogIn = new JButton("LOG IN");
		btnLogIn.setBounds(77, 74, 89, 23);
		frmSelectAnOption.getContentPane().add(btnLogIn);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnBack.setBounds(77, 126, 89, 23);
		frmSelectAnOption.getContentPane().add(btnBack);
	}
}
