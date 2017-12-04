package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.horses.dbmanage.Config;
import com.horses.dbmanage.ConnectionManager;
import com.horses.dbmanage.SchemaCreator;
import com.horses.dbmanage.TableExporter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
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
		frmSystemAdminInterface.setBounds(100, 100, 304, 265);
		frmSystemAdminInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSystemAdminInterface.getContentPane().setLayout(null);
		
		JButton btnImportDatabase = new JButton("IMPORT DATABASE");
		btnImportDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSystemAdminInterface.dispose();
				ViewSAImport window = new ViewSAImport();
				window.frmSetPathFor.setVisible(true);
			}
		});
		btnImportDatabase.setBounds(12, 12, 261, 23);
		frmSystemAdminInterface.getContentPane().add(btnImportDatabase);
		
		JButton btnExportDatabase = new JButton("EXPORT DATABASE");
		btnExportDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableExporter.exportDB();
			}
		});
		btnExportDatabase.setBounds(12, 46, 261, 23);
		frmSystemAdminInterface.getContentPane().add(btnExportDatabase);
		
		JButton btnDeleteDatabase = new JButton("DELETE DATABASE");
		btnDeleteDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the database?", "", JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION) {
			          JOptionPane.showMessageDialog(null, "DATABASE DELETED");
			          
			          //TODO DELETE DATABASE
			          ConnectionManager cm = new ConnectionManager(Config.connection_url);
			          SchemaCreator sc = new SchemaCreator(cm);
			          try {
						sc.setDropDbFile("stored-procedures-create-schema/drop-db.sql");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			          sc.dropSchema();
			          cm.closeConnection();
			        }
			}
		});
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
		btnBack.setBounds(184, 183, 89, 23);
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
		
		JButton btnEditTables = new JButton("EDIT TABLES - QUERIES");
		btnEditTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSystemAdminInterface.dispose();
				ViewR1UserGUI window = new ViewR1UserGUI();
				window.frmPleaseChooseA.setVisible(true);
			}
		});
		btnEditTables.setBounds(12, 149, 261, 23);
		frmSystemAdminInterface.getContentPane().add(btnEditTables);
	}
}
