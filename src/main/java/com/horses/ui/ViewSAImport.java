package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import com.horses.dbmanage.Config;
import com.horses.dbmanage.ConnectionManager;
import com.horses.dbmanage.RecordInserter;

public class ViewSAImport {

	public JFrame frmSetPathFor;

	
	private JButton btnChoosebreeder;
	private JButton btnFamilies;
	private JButton btnHorses;
	private JButton btnHorsecolors;
	private JButton btnJockey;
	private JButton btnHorseSex;
	private JLabel lblHorsColors;
	private JLabel lblHorses;
	private JLabel lblBreeder;
	private JButton btnBreedermeetings;
	private JButton btnOwner;
	private JButton buttonRaces;
	private JLabel lblFamilies;
	private JLabel labelHorseSex;
	private JLabel labelJockey;
	private JLabel labelMeetings;
	private JLabel labelOwner;
	private JLabel labelRaces;
	private JLabel labelRaceDistances;
	private JLabel labelRaceFieldTypes;
	private JLabel labelRaceTypes;
	private JLabel labelRaceResults;
	private JLabel labelTrainer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSAImport window = new ViewSAImport();
					window.frmSetPathFor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewSAImport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSetPathFor = new JFrame();
		frmSetPathFor.setTitle("SET PATH FOR EACH TABLE");
		frmSetPathFor.setBounds(100, 100, 699, 862);
		frmSetPathFor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSetPathFor.getContentPane().setLayout(null);
		

		
		btnChoosebreeder = new JButton("BREEDER");
		btnChoosebreeder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser breederChooser = new JFileChooser();
				int responce = breederChooser.showOpenDialog(frmSetPathFor);
				String breederFileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					breederFileName = breederChooser.getSelectedFile().toString();
					lblBreeder.setText(breederFileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + breederFileName);

			}
		});
		btnChoosebreeder.setBounds(26, 22, 211, 25);
		frmSetPathFor.getContentPane().add(btnChoosebreeder);
		
		btnFamilies = new JButton("FAMILIES");
		btnFamilies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser familyChooser = new JFileChooser();
				int responce = familyChooser.showOpenDialog(frmSetPathFor);
				String familyFileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					familyFileName = familyChooser.getSelectedFile().toString();
					lblFamilies.setText(familyFileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + familyFileName);
			}
		});
		btnFamilies.setBounds(26, 72, 211, 25);
		frmSetPathFor.getContentPane().add(btnFamilies);
		
		btnHorses = new JButton("HORSES");
		btnHorses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser = new JFileChooser();
				int responce = Chooser.showOpenDialog(frmSetPathFor);
				String FileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					FileName = Chooser.getSelectedFile().toString();
					lblHorses.setText(FileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + FileName);
			}
		});
		btnHorses.setBounds(26, 121, 211, 25);
		frmSetPathFor.getContentPane().add(btnHorses);
		
		btnHorsecolors = new JButton("HORSE COLORS");
		btnHorsecolors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser = new JFileChooser();
				int responce = Chooser.showOpenDialog(frmSetPathFor);
				String FileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					FileName = Chooser.getSelectedFile().toString();
					lblHorsColors.setText(FileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + FileName);
			}
		});
		btnHorsecolors.setBounds(26, 170, 211, 25);
		frmSetPathFor.getContentPane().add(btnHorsecolors);
		
		btnJockey = new JButton("JOCKEY");
		btnJockey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser = new JFileChooser();
				int responce = Chooser.showOpenDialog(frmSetPathFor);
				String FileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					FileName = Chooser.getSelectedFile().toString();
					labelJockey.setText(FileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + FileName);
			}
		});
		btnJockey.setBounds(26, 268, 211, 25);
		frmSetPathFor.getContentPane().add(btnJockey);
		
		btnHorseSex = new JButton("HORSE SEX");
		btnHorseSex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser = new JFileChooser();
				int responce = Chooser.showOpenDialog(frmSetPathFor);
				String FileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					FileName = Chooser.getSelectedFile().toString();
					labelHorseSex.setText(FileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + FileName);
			}
		});
		btnHorseSex.setBounds(26, 220, 211, 25);
		frmSetPathFor.getContentPane().add(btnHorseSex);
		
		lblBreeder = new JLabel("");
		lblBreeder.setBounds(266, 13, 392, 43);
		frmSetPathFor.getContentPane().add(lblBreeder);
		
		lblHorses = new JLabel("");
		lblHorses.setBounds(266, 112, 392, 43);
		frmSetPathFor.getContentPane().add(lblHorses);
		
		lblHorsColors = new JLabel("");
		lblHorsColors.setBounds(266, 161, 392, 43);
		frmSetPathFor.getContentPane().add(lblHorsColors);
		
		lblFamilies = new JLabel("");
		lblFamilies.setBounds(266, 63, 392, 43);
		frmSetPathFor.getContentPane().add(lblFamilies);
		
		labelHorseSex = new JLabel("");
		labelHorseSex.setBounds(266, 211, 392, 43);
		frmSetPathFor.getContentPane().add(labelHorseSex);
		
		labelJockey = new JLabel("");
		labelJockey.setBounds(266, 259, 392, 43);
		frmSetPathFor.getContentPane().add(labelJockey);
		
		btnBreedermeetings = new JButton("MEETINGS");
		btnBreedermeetings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser = new JFileChooser();
				int responce = Chooser.showOpenDialog(frmSetPathFor);
				String FileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					FileName = Chooser.getSelectedFile().toString();
					labelMeetings.setText(FileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + FileName);
			}
		});
		btnBreedermeetings.setBounds(26, 320, 211, 25);
		frmSetPathFor.getContentPane().add(btnBreedermeetings);
		
		btnOwner = new JButton("OWNER");
		btnOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser = new JFileChooser();
				int responce = Chooser.showOpenDialog(frmSetPathFor);
				String FileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					FileName = Chooser.getSelectedFile().toString();
					labelOwner.setText(FileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + FileName);
			}
		});
		btnOwner.setBounds(26, 368, 211, 25);
		frmSetPathFor.getContentPane().add(btnOwner);
		
		buttonRaces = new JButton("RACES");
		buttonRaces.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser = new JFileChooser();
				int responce = Chooser.showOpenDialog(frmSetPathFor);
				String FileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					FileName = Chooser.getSelectedFile().toString();
					labelRaces.setText(FileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + FileName);
			}
		});
		buttonRaces.setBounds(26, 421, 211, 25);
		frmSetPathFor.getContentPane().add(buttonRaces);
		
		labelMeetings = new JLabel("");
		labelMeetings.setBounds(266, 313, 392, 43);
		frmSetPathFor.getContentPane().add(labelMeetings);
		
		labelOwner = new JLabel("");
		labelOwner.setBounds(266, 356, 392, 43);
		frmSetPathFor.getContentPane().add(labelOwner);
		
		labelRaces = new JLabel("");
		labelRaces.setBounds(266, 410, 392, 43);
		frmSetPathFor.getContentPane().add(labelRaces);
		
		JButton btnRaceDistances = new JButton("RACE DISTANCES");
		btnRaceDistances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser = new JFileChooser();
				int responce = Chooser.showOpenDialog(frmSetPathFor);
				String FileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					FileName = Chooser.getSelectedFile().toString();
					labelRaceDistances.setText(FileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + FileName);
			}
		});
		btnRaceDistances.setBounds(26, 478, 211, 23);
		frmSetPathFor.getContentPane().add(btnRaceDistances);
		
		JButton btnRaceFieldTypes = new JButton("RACE FIELD TYPES");
		btnRaceFieldTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser = new JFileChooser();
				int responce = Chooser.showOpenDialog(frmSetPathFor);
				String FileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					FileName = Chooser.getSelectedFile().toString();
					labelRaceFieldTypes.setText(FileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + FileName);
			}
		});
		btnRaceFieldTypes.setBounds(26, 531, 211, 23);
		frmSetPathFor.getContentPane().add(btnRaceFieldTypes);
		
		JButton btnRaceTypes = new JButton("RACE TYPES");
		btnRaceTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser = new JFileChooser();
				int responce = Chooser.showOpenDialog(frmSetPathFor);
				String FileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					FileName = Chooser.getSelectedFile().toString();
					labelRaceTypes.setText(FileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + FileName);
			}
		});
		btnRaceTypes.setBounds(26, 587, 211, 23);
		frmSetPathFor.getContentPane().add(btnRaceTypes);
		
		JButton btnRaceResults = new JButton("RACE RESULTS");
		btnRaceResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser = new JFileChooser();
				int responce = Chooser.showOpenDialog(frmSetPathFor);
				String FileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					FileName = Chooser.getSelectedFile().toString();
					labelRaceResults.setText(FileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + FileName);
			}
		});
		btnRaceResults.setBounds(26, 643, 211, 23);
		frmSetPathFor.getContentPane().add(btnRaceResults);
		
		JButton btnTrainer = new JButton("TRAINER");
		btnTrainer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser = new JFileChooser();
				int responce = Chooser.showOpenDialog(frmSetPathFor);
				String FileName ="";
				if(responce == JFileChooser.APPROVE_OPTION) {
					FileName = Chooser.getSelectedFile().toString();
					labelTrainer.setText(FileName);
				} else {
					
				}
				System.out.println("The file that you chose is " + FileName);
			}
		});
		btnTrainer.setBounds(26, 696, 211, 23);
		frmSetPathFor.getContentPane().add(btnTrainer);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSetPathFor.dispose();
				ViewSAUserGUI window = new ViewSAUserGUI();
				window.frmSystemAdminInterface.setVisible(true);
			}
		});
		btnBack.setBounds(497, 770, 130, 23);
		frmSetPathFor.getContentPane().add(btnBack);
		
		labelRaceDistances = new JLabel("");
		labelRaceDistances.setBounds(266, 464, 392, 43);
		frmSetPathFor.getContentPane().add(labelRaceDistances);
		
		labelRaceFieldTypes = new JLabel("");
		labelRaceFieldTypes.setBounds(266, 518, 392, 43);
		frmSetPathFor.getContentPane().add(labelRaceFieldTypes);
		
		labelRaceTypes = new JLabel("");
		labelRaceTypes.setBounds(266, 572, 392, 43);
		frmSetPathFor.getContentPane().add(labelRaceTypes);
		
		labelRaceResults = new JLabel("");
		labelRaceResults.setBounds(266, 633, 392, 43);
		frmSetPathFor.getContentPane().add(labelRaceResults);
		
		labelTrainer = new JLabel("");
		labelTrainer.setBounds(266, 687, 392, 43);
		frmSetPathFor.getContentPane().add(labelTrainer);
		
		JButton btnImport_1 = new JButton("IMPORT");
		btnImport_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ConnectionManager cm = new ConnectionManager(Config.connection_url);
				RecordInserter ri = new RecordInserter(cm);
				ri.fileHorsesColor = lblHorsColors.getText();
				ri.fileHorses = lblHorses.getText();
				ri.fileBreeders = lblBreeder.getText();
				ri.fileFamilies = lblFamilies.getText();
				ri.fileHorsesSex = labelHorseSex.getText();
				ri.fileJockeys = labelJockey.getText();
				ri.fileMeetings = labelMeetings.getText();
				ri.fileOwners = labelOwner.getText();
				ri.fileRaces = labelRaces.getText();
				ri.fileRacesDistances = labelRaceDistances.getText();
				ri.fileRacesTypes = labelRaceTypes.getText();
				ri.fileTrainers = labelTrainer.getText();		
				ri.fileFieldTypes = labelRaceFieldTypes.getText();
				ri.fileRaceResults =	labelRaceResults.getText();
				
				try {
					ri.insert();
				} catch (SQLException | ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cm.closeConnection();
			}
		});
		btnImport_1.setBounds(26, 758, 343, 47);
		frmSetPathFor.getContentPane().add(btnImport_1);
	}
}
