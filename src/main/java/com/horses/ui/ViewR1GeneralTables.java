package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewR1GeneralTables {

	public JFrame frmTables;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1GeneralTables window = new ViewR1GeneralTables();
					window.getFrmTables().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1GeneralTables() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmTables(new JFrame());
		getFrmTables().setTitle("TABLES");
		getFrmTables().setBounds(100, 100, 345, 542);
		getFrmTables().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmTables().getContentPane().setLayout(null);
		
		JButton btnHorse = new JButton("HORSE");
		btnHorse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1UserEditTables window = new ViewR1UserEditTables();
				window.frmEditTables.setVisible(true);
			}
		});
		btnHorse.setBounds(10, 11, 131, 34);
		getFrmTables().getContentPane().add(btnHorse);
		
		JButton btnHorseColor = new JButton("HORSE COLOR");
		btnHorseColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1HorseColor window = new ViewR1HorseColor();
				window.getFrmColorName().setVisible(true);
			}
		});
		btnHorseColor.setBounds(10, 77, 131, 34);
		getFrmTables().getContentPane().add(btnHorseColor);
		
		JButton btnTrainer = new JButton("TRAINER");
		btnTrainer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1Trainer window = new ViewR1Trainer();
				window.getFrmTrainer().setVisible(true);
			}
		});
		btnTrainer.setBounds(10, 269, 131, 34);
		getFrmTables().getContentPane().add(btnTrainer);
		
		JButton btnBreeder = new JButton("BREEDER");
		btnBreeder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1Breeder window = new ViewR1Breeder();
				window.getFrmBreeder().setVisible(true);
			}
		});
		btnBreeder.setBounds(10, 207, 131, 34);
		getFrmTables().getContentPane().add(btnBreeder);
		
		JButton btnOwner = new JButton("OWNER");
		btnOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1Owner window = new ViewR1Owner();
				window.getFrmOwner().setVisible(true);
			}
		});
		btnOwner.setBounds(10, 334, 131, 34);
		getFrmTables().getContentPane().add(btnOwner);
		
		JButton btnRace = new JButton("RACE");
		btnRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1Race window = new ViewR1Race();
				window.getFrmRace().setVisible(true);
				
			}
		});
		btnRace.setBounds(178, 11, 131, 34);
		getFrmTables().getContentPane().add(btnRace);
		
		JButton btnRaceType = new JButton("RACE TYPE");
		btnRaceType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1RaceType window = new ViewR1RaceType();
				window.getFrame().setVisible(true);
			}
		});
		btnRaceType.setBounds(178, 77, 131, 34);
		getFrmTables().getContentPane().add(btnRaceType);
		
		JButton btnFamily = new JButton("FAMILY");
		btnFamily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1Family window = new ViewR1Family();
				window.getFrmFamily().setVisible(true);
			}
		});
		btnFamily.setBounds(10, 387, 131, 34);
		getFrmTables().getContentPane().add(btnFamily);
		
		JButton btnSystemUser = new JButton("SYSTEM USER");
		btnSystemUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1SystemUser window = new ViewR1SystemUser();
				window.getFrmSystemUser().setVisible(true);
			}
		});
		btnSystemUser.setBounds(178, 334, 131, 34);
		getFrmTables().getContentPane().add(btnSystemUser);
		
		JButton btnParticipation = new JButton("PARTICIPATION");
		btnParticipation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1Participation window = new ViewR1Participation();
				window.getFrmParticipation().setVisible(true);
			}
		});
		btnParticipation.setBounds(178, 387, 131, 34);
		getFrmTables().getContentPane().add(btnParticipation);
		
		JButton btnFieldType = new JButton("FIELD TYPE");
		btnFieldType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1FieldType window = new ViewR1FieldType();
				window.getFrmFieldType().setVisible(true);
			}
		});
		btnFieldType.setBounds(178, 207, 131, 34);
		getFrmTables().getContentPane().add(btnFieldType);
		
		JButton btnRaceDistance = new JButton("RACE DISTANCE");
		btnRaceDistance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1Distance window = new ViewR1Distance();
				window.getFrmRaceDistance().setVisible(true);
			}
		});
		btnRaceDistance.setBounds(178, 141, 131, 34);
		getFrmTables().getContentPane().add(btnRaceDistance);
		
		JButton btnJockey = new JButton("JOCKEY");
		btnJockey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1Jockey window = new ViewR1Jockey();
				window.getFrmJockey().setVisible(true);
			}
		});
		btnJockey.setBounds(10, 141, 131, 34);
		getFrmTables().getContentPane().add(btnJockey);
		
		JButton btnMeeting = new JButton("MEETING");
		btnMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmTables().dispose();
				ViewR1Meeting window = new ViewR1Meeting();
				window.getFrmMeeting().setVisible(true);
			}
		});
		btnMeeting.setBounds(176, 269, 133, 34);
		getFrmTables().getContentPane().add(btnMeeting);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmTables.dispose();
				ViewR1UserGUI window = new ViewR1UserGUI();
				window.frmPleaseChooseA.setVisible(true);
			}
		});
		btnBack.setBounds(220, 459, 89, 34);
		getFrmTables().getContentPane().add(btnBack);
	}

	public JFrame getFrmTables() {
		return frmTables;
	}

	public void setFrmTables(JFrame frmTables) {
		this.frmTables = frmTables;
	}

}
