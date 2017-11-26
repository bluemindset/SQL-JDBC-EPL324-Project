package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
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
				 //setVisible(false);
		         new ViewQ1HorseList();
			}
		});
		btnQViewHorse.setBounds(10, 36, 326, 23);
		frmQueries.getContentPane().add(btnQViewHorse);
		
		JButton btnQViewHorse_1 = new JButton("Q2: View Horse Race results of a Meeting");
		btnQViewHorse_1.setBounds(10, 70, 326, 23);
		frmQueries.getContentPane().add(btnQViewHorse_1);
		
		JButton btnQViewHorses = new JButton("Q3: View Horses based on Age");
		btnQViewHorses.setBounds(10, 103, 326, 23);
		frmQueries.getContentPane().add(btnQViewHorses);
		
		JButton btnQView = new JButton("Q4: View Horses based on an Owner's Family");
		btnQView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnQView.setBounds(10, 139, 326, 23);
		frmQueries.getContentPane().add(btnQView);
		
		JButton btnQViewA = new JButton("Q5: View a List of Trainers who lost");
		btnQViewA.setBounds(10, 173, 326, 23);
		frmQueries.getContentPane().add(btnQViewA);
		
		JButton btnQViewHorse_2 = new JButton("Q6: View Horse list which attended all meetings");
		btnQViewHorse_2.setBounds(10, 207, 326, 23);
		frmQueries.getContentPane().add(btnQViewHorse_2);
		
		JButton btnQViewAll = new JButton("Q7: View all successful Horses");
		btnQViewAll.setBounds(10, 241, 326, 23);
		frmQueries.getContentPane().add(btnQViewAll);
		
		JButton btnQViewClassification = new JButton("Q8: View Classification Reports");
		btnQViewClassification.setBounds(10, 275, 326, 23);
		frmQueries.getContentPane().add(btnQViewClassification);
		
		JButton btnQViewProgress = new JButton("Q9: View Progress Reports");
		btnQViewProgress.setBounds(10, 309, 326, 23);
		frmQueries.getContentPane().add(btnQViewProgress);
		
		JButton btnQViewUser = new JButton("Q10: View User Report");
		btnQViewUser.setBounds(10, 343, 326, 23);
		frmQueries.getContentPane().add(btnQViewUser);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBounds(247, 377, 89, 23);
		frmQueries.getContentPane().add(btnBack);
	}

}
