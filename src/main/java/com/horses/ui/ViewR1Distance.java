package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewR1Distance {

	private JFrame frmRaceDistance;
	private JTable table;
	private JTextField textFieldRaceDistance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1Distance window = new ViewR1Distance();
					window.frmRaceDistance.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Distance() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRaceDistance = new JFrame();
		frmRaceDistance.setTitle("RACE DISTANCE");
		frmRaceDistance.setBounds(100, 100, 428, 485);
		frmRaceDistance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRaceDistance.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 392, 233);
		frmRaceDistance.getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Distance Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(20, 255, 382, 135);
		frmRaceDistance.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRaceDistance = new JLabel("Race Distance:");
		lblRaceDistance.setBounds(10, 39, 90, 14);
		panel.add(lblRaceDistance);
		
		textFieldRaceDistance = new JTextField();
		textFieldRaceDistance.setBounds(105, 39, 86, 20);
		panel.add(textFieldRaceDistance);
		textFieldRaceDistance.setColumns(10);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.setBounds(10, 88, 89, 33);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBounds(102, 88, 89, 33);
		panel.add(btnUpdate);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRaceDistance.dispose();
				ViewR1UserGUI window = new ViewR1UserGUI();
				window.frmPleaseChooseA.setVisible(true);
			}
		});
		btnNewButton.setBounds(313, 401, 89, 35);
		frmRaceDistance.getContentPane().add(btnNewButton);
	}

}
