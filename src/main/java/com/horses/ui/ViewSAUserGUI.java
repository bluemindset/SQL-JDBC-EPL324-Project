package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewSAUserGUI {

	public JFrame frmSystemAdminInterface;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSAUserGUI window = new ViewSAUserGUI();
					window.frmSystemAdminInterface.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewSAUserGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSystemAdminInterface = new JFrame();
		frmSystemAdminInterface.setTitle("System Admin Interface");
		frmSystemAdminInterface.setBounds(100, 100, 304, 330);
		frmSystemAdminInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSystemAdminInterface.getContentPane().setLayout(null);
		
		JButton btnImportDatabase = new JButton("IMPORT DATABASE");
		btnImportDatabase.setBounds(12, 12, 261, 23);
		frmSystemAdminInterface.getContentPane().add(btnImportDatabase);
		
		JButton btnExportDatabase = new JButton("EXPORT DATABASE");
		btnExportDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExportDatabase.setBounds(12, 46, 261, 23);
		frmSystemAdminInterface.getContentPane().add(btnExportDatabase);
		
		JButton btnDeleteDatabase = new JButton("DELETE DATABASE");
		btnDeleteDatabase.setBounds(12, 80, 261, 23);
		frmSystemAdminInterface.getContentPane().add(btnDeleteDatabase);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSystemAdminInterface.dispose();
				ViewChooseUser window = new ViewChooseUser();
				window.frmChooseAUser.setVisible(true);
			}
		});
		btnBack.setBounds(184, 237, 89, 23);
		frmSystemAdminInterface.getContentPane().add(btnBack);
		
		JButton btnAddNewUser = new JButton("ADD NEW USER");
		btnAddNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSystemAdminInterface.dispose();
				CreateUser window = new CreateUser();
				window.frmCreateUser.setVisible(true);
			}
		});
		btnAddNewUser.setBounds(12, 115, 261, 23);
		frmSystemAdminInterface.getContentPane().add(btnAddNewUser);
		
		JButton btnBackup = new JButton("BACKUP");
		btnBackup.setBounds(12, 149, 261, 23);
		frmSystemAdminInterface.getContentPane().add(btnBackup);
		
		JButton btnRestore = new JButton("RESTORE");
		btnRestore.setBounds(12, 183, 261, 23);
		frmSystemAdminInterface.getContentPane().add(btnRestore);
	}
}
