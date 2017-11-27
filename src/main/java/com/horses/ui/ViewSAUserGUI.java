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
		frmSystemAdminInterface.setBounds(100, 100, 301, 229);
		frmSystemAdminInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSystemAdminInterface.getContentPane().setLayout(null);
		
		JButton btnImportDatabase = new JButton("IMPORT DATABASE");
		btnImportDatabase.setBounds(10, 27, 261, 23);
		frmSystemAdminInterface.getContentPane().add(btnImportDatabase);
		
		JButton btnExportDatabase = new JButton("EXPORT DATABASE");
		btnExportDatabase.setBounds(10, 61, 261, 23);
		frmSystemAdminInterface.getContentPane().add(btnExportDatabase);
		
		JButton btnDeleteDatabase = new JButton("DELETE DATABASE");
		btnDeleteDatabase.setBounds(10, 95, 261, 23);
		frmSystemAdminInterface.getContentPane().add(btnDeleteDatabase);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSystemAdminInterface.dispose();
				ViewSAUserLogin window = new ViewSAUserLogin();
				window.frmSystemAdminLogin.setVisible(true);
			}
		});
		btnBack.setBounds(182, 151, 89, 23);
		frmSystemAdminInterface.getContentPane().add(btnBack);
	}

}
