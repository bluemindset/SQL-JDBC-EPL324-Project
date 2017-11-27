package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewR1UserEditTables {

	public JFrame frmEditTables;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1UserEditTables window = new ViewR1UserEditTables();
					window.frmEditTables.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1UserEditTables() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditTables = new JFrame();
		frmEditTables.setTitle("EDIT TABLES");
		frmEditTables.setBounds(100, 100, 637, 519);
		frmEditTables.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditTables.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 601, 425);
		frmEditTables.getContentPane().add(tabbedPane);
		
		JPanel panelHorses = new JPanel();
		tabbedPane.addTab("Horses", null, panelHorses, null);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEditTables.dispose();
				ViewR1UserGUI window = new ViewR1UserGUI();
				window.frmPleaseChooseA.setVisible(true);
			}
		});
		btnBack.setBounds(522, 447, 89, 23);
		frmEditTables.getContentPane().add(btnBack);
	}
}
