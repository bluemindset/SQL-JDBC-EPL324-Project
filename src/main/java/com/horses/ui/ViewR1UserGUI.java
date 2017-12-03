package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewR1UserGUI {

	public JFrame frmPleaseChooseA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1UserGUI window = new ViewR1UserGUI();
					window.frmPleaseChooseA.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1UserGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPleaseChooseA = new JFrame();
		frmPleaseChooseA.setTitle("PLEASE CHOOSE A SELECTION");
		frmPleaseChooseA.setBounds(100, 100, 419, 178);
		frmPleaseChooseA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPleaseChooseA.getContentPane().setLayout(null);
		
		JButton btnEditTables = new JButton("EDIT TABLES");
		btnEditTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPleaseChooseA.dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.frmTables.setVisible(true);
			}
		});
		btnEditTables.setBounds(80, 29, 246, 23);
		frmPleaseChooseA.getContentPane().add(btnEditTables);
		
		JButton btnViewQueries = new JButton("VIEW QUERIES / REPORTS");
		btnViewQueries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPleaseChooseA.dispose();
				ViewMainQView window = new ViewMainQView();
				window.frmQueries.setVisible(true);
			}
		});
		btnViewQueries.setBounds(80, 63, 246, 23);
		frmPleaseChooseA.getContentPane().add(btnViewQueries);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPleaseChooseA.dispose();
				ViewR1UserLogin window = new ViewR1UserLogin();
				window.frmSystemUserSign.setVisible(true);
			}
		});
		btnBack.setBounds(237, 97, 89, 23);
		frmPleaseChooseA.getContentPane().add(btnBack);
	}

}
