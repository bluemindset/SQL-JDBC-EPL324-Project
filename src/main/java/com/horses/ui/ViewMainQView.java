package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewMainQView {

	JFrame frmQueries;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMainQView window = new ViewMainQView();
					window.frmQueries.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewMainQView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQueries = new JFrame();
		frmQueries.setTitle("QUERIES");
		frmQueries.setBounds(100, 100, 368, 456);
		frmQueries.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQueries.getContentPane().setLayout(null);
		
		JLabel lblPleaseSelectAny = new JLabel("Please select any of the following queries:");
		lblPleaseSelectAny.setBounds(10, 11, 354, 14);
		frmQueries.getContentPane().add(lblPleaseSelectAny);
		
		JButton btnQViewHorse = new JButton("Q1: View Horse List Based on Name");
		btnQViewHorse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmQueries.dispose();
				ViewQ1HorseList window = new ViewQ1HorseList();
				window.frmHorseNameList.setVisible(true);
			}
		});
		btnQViewHorse.setBounds(10, 36, 326, 23);
		frmQueries.getContentPane().add(btnQViewHorse);
		
		JButton btnQViewHorse_1 = new JButton("Q2: View Horse Race results of a Meeting");
		btnQViewHorse_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmQueries.dispose();
				ViewQ2HorseRaceResults dialog = new ViewQ2HorseRaceResults();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnQViewHorse_1.setBounds(10, 70, 326, 23);
		frmQueries.getContentPane().add(btnQViewHorse_1);
		
		JButton btnQViewHorses = new JButton("Q3: View Horses based on Age");
		btnQViewHorses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmQueries.dispose();
				ViewQ3HorseAgeList dialog = new ViewQ3HorseAgeList();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnQViewHorses.setBounds(10, 103, 326, 23);
		frmQueries.getContentPane().add(btnQViewHorses);
		
		JButton btnQView = new JButton("Q4: View Horses based on an Owner's Family");
		btnQView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmQueries.dispose();
				ViewQ4HorsesOfOwnerFamily dialog = new ViewQ4HorsesOfOwnerFamily();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnQView.setBounds(10, 139, 326, 23);
		frmQueries.getContentPane().add(btnQView);
		
		JButton btnQViewA = new JButton("Q5: View a List of Trainers who lost");
		btnQViewA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmQueries.dispose();
				ViewQ5LostTrainers dialog = new ViewQ5LostTrainers();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnQViewA.setBounds(10, 173, 326, 23);
		frmQueries.getContentPane().add(btnQViewA);
		
		JButton btnQViewHorse_2 = new JButton("Q6: View Horse list which attended all meetings");
		btnQViewHorse_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmQueries.dispose();
				ViewQ6HorsesAllMeetings dialog = new ViewQ6HorsesAllMeetings();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnQViewHorse_2.setBounds(10, 207, 326, 23);
		frmQueries.getContentPane().add(btnQViewHorse_2);
		
		JButton btnQViewAll = new JButton("Q7: View all successful Horses");
		btnQViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmQueries.dispose();
				ViewQ7HorseWinners dialog = new ViewQ7HorseWinners();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnQViewAll.setBounds(10, 241, 326, 23);
		frmQueries.getContentPane().add(btnQViewAll);
		
		JButton btnQViewClassification = new JButton("Q8: View Classification Reports");
		btnQViewClassification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmQueries.dispose();
				ViewQ8ClassificationReports dialog = new ViewQ8ClassificationReports();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnQViewClassification.setBounds(10, 275, 326, 23);
		frmQueries.getContentPane().add(btnQViewClassification);
		
		JButton btnQViewProgress = new JButton("Q9: View Progress Reports");
		btnQViewProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmQueries.dispose();
				ViewQ9ProgressReports dialog = new ViewQ9ProgressReports();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnQViewProgress.setBounds(10, 309, 326, 23);
		frmQueries.getContentPane().add(btnQViewProgress);
		
		JButton btnQViewUser = new JButton("Q10: View User Report");
		btnQViewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmQueries.dispose();
				ViewQ10UserReport dialog = new ViewQ10UserReport();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnQViewUser.setBounds(10, 343, 326, 23);
		frmQueries.getContentPane().add(btnQViewUser);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmQueries.dispose();
				ViewR1UserGUI window = new ViewR1UserGUI();
				window.frmPleaseChooseA.setVisible(true);
			}
		});
		btnBack.setBounds(247, 377, 89, 23);
		frmQueries.getContentPane().add(btnBack);
	}

}
