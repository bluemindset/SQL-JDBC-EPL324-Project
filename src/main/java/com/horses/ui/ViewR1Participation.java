package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewR1Participation {

	private JFrame frmParticipation;
	private JTable table;
	private JTextField textFieldRaceTime;
	private JTextField textFieldMeetingDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1Participation window = new ViewR1Participation();
					window.getFrmParticipation().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Participation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmParticipation(new JFrame());
		getFrmParticipation().setTitle("PARTICIPATION");
		getFrmParticipation().setBounds(100, 100, 533, 477);
		getFrmParticipation().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmParticipation().getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 497, 148);
		getFrmParticipation().getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Participation Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 172, 497, 216);
		getFrmParticipation().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRaceTime = new JLabel("Race Time:");
		lblRaceTime.setBounds(10, 44, 89, 14);
		panel.add(lblRaceTime);
		
		textFieldRaceTime = new JTextField();
		textFieldRaceTime.setBounds(128, 41, 86, 20);
		panel.add(textFieldRaceTime);
		textFieldRaceTime.setColumns(10);
		
		JLabel lblMeetingDate = new JLabel("Meeting Date:");
		lblMeetingDate.setBounds(10, 88, 108, 14);
		panel.add(lblMeetingDate);
		
		textFieldMeetingDate = new JTextField();
		textFieldMeetingDate.setBounds(128, 85, 86, 20);
		panel.add(textFieldMeetingDate);
		textFieldMeetingDate.setColumns(10);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.setBounds(10, 182, 89, 23);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBounds(125, 182, 89, 23);
		panel.add(btnUpdate);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 140, 46, 14);
		panel.add(lblNewLabel);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmParticipation().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnBack.setBounds(418, 399, 89, 29);
		getFrmParticipation().getContentPane().add(btnBack);
	}

	public JFrame getFrmParticipation() {
		return frmParticipation;
	}

	public void setFrmParticipation(JFrame frmParticipation) {
		this.frmParticipation = frmParticipation;
	}

}
