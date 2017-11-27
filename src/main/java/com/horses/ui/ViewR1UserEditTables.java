package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class ViewR1UserEditTables {

	public JFrame frmEditTables;
	private JTable table;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldWeight;
	private JTextField textFieldAge;
	private JTextField textFieldOriginCountry;
	private JTextField textFieldRecord;

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
		frmEditTables.setBounds(100, 100, 777, 648);
		frmEditTables.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditTables.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 741, 554);
		frmEditTables.getContentPane().add(tabbedPane);
		
		JPanel panelHorses = new JPanel();
		tabbedPane.addTab("Horses", null, panelHorses, null);
		panelHorses.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 11, 716, 176);
		panelHorses.add(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Horses Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 203, 716, 262);
		panelHorses.add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 26, 46, 14);
		panel.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(95, 23, 86, 20);
		panel.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 57, 46, 14);
		panel.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(95, 54, 86, 20);
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(10, 88, 46, 14);
		panel.add(lblWeight);
		
		textFieldWeight = new JTextField();
		textFieldWeight.setBounds(95, 85, 86, 20);
		panel.add(textFieldWeight);
		textFieldWeight.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth:");
		lblDateOfBirth.setBounds(10, 119, 100, 14);
		panel.add(lblDateOfBirth);
		
		DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
		JFormattedTextField formattedTextField = new JFormattedTextField(format);
		formattedTextField.setBounds(95, 116, 86, 20);
		panel.add(formattedTextField);
		
		JLabel label = new JLabel("format: (DD/MM/YYYY)");
		label.setBounds(188, 119, 146, 14);
		panel.add(label);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(344, 119, 46, 14);
		panel.add(lblAge);
		
		textFieldAge = new JTextField();
		textFieldAge.setEditable(false);
		textFieldAge.setBounds(378, 116, 86, 20);
		panel.add(textFieldAge);
		textFieldAge.setColumns(10);
		
		JLabel lblSex = new JLabel("Sex:");
		lblSex.setBounds(10, 145, 46, 14);
		panel.add(lblSex);
		
		JComboBox comboBoxSex = new JComboBox();
		comboBoxSex.setBounds(95, 144, 86, 20);
		panel.add(comboBoxSex);
		
		JLabel lblIsPurebred = new JLabel("Is Purebred:");
		lblIsPurebred.setBounds(10, 170, 79, 14);
		panel.add(lblIsPurebred);
		
		JCheckBox checkBoxIsPurebred = new JCheckBox("");
		checkBoxIsPurebred.setBounds(95, 168, 97, 23);
		panel.add(checkBoxIsPurebred);
		
		JLabel lblOriginCountry = new JLabel("Origin Country:");
		lblOriginCountry.setBounds(10, 201, 100, 14);
		panel.add(lblOriginCountry);
		
		textFieldOriginCountry = new JTextField();
		textFieldOriginCountry.setBounds(95, 198, 86, 20);
		panel.add(textFieldOriginCountry);
		textFieldOriginCountry.setColumns(10);
		
		JLabel lblMomId = new JLabel("Mom ID:");
		lblMomId.setBounds(350, 26, 60, 14);
		panel.add(lblMomId);
		
		JLabel lblDadId = new JLabel("Dad ID: ");
		lblDadId.setBounds(350, 57, 60, 14);
		panel.add(lblDadId);
		
		JLabel lblJockeyId = new JLabel("Jockey ID: ");
		lblJockeyId.setBounds(350, 88, 79, 14);
		panel.add(lblJockeyId);
		
		JComboBox comboBoxMom = new JComboBox();
		comboBoxMom.setBounds(413, 23, 127, 20);
		panel.add(comboBoxMom);
		
		JComboBox comboBoxDad = new JComboBox();
		comboBoxDad.setBounds(413, 54, 127, 20);
		panel.add(comboBoxDad);
		
		JComboBox comboBoxJockey = new JComboBox();
		comboBoxJockey.setBounds(413, 85, 127, 20);
		panel.add(comboBoxJockey);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(10, 226, 46, 14);
		panel.add(lblColor);
		
		JComboBox comboBoxColor = new JComboBox();
		comboBoxColor.setBounds(95, 226, 86, 25);
		panel.add(comboBoxColor);
		
		JLabel lblBreederId = new JLabel("Breeder ID:");
		lblBreederId.setBounds(344, 150, 75, 14);
		panel.add(lblBreederId);
		
		JLabel lblTrainerId = new JLabel("Trainer ID:");
		lblTrainerId.setBounds(344, 177, 85, 14);
		panel.add(lblTrainerId);
		
		JLabel lblOwnerId = new JLabel("Owner ID:");
		lblOwnerId.setBounds(344, 204, 66, 14);
		panel.add(lblOwnerId);
		
		JComboBox comboBoxBreeder = new JComboBox();
		comboBoxBreeder.setBounds(413, 147, 127, 20);
		panel.add(comboBoxBreeder);
		
		JComboBox comboBoxTrainer = new JComboBox();
		comboBoxTrainer.setBounds(413, 174, 127, 17);
		panel.add(comboBoxTrainer);
		
		JComboBox comboBoxOwner = new JComboBox();
		comboBoxOwner.setBounds(413, 198, 127, 17);
		panel.add(comboBoxOwner);
		
		JLabel lblRecord = new JLabel("Record:");
		lblRecord.setBounds(344, 231, 66, 14);
		panel.add(lblRecord);
		
		textFieldRecord = new JTextField();
		textFieldRecord.setBounds(413, 228, 293, 23);
		panel.add(textFieldRecord);
		textFieldRecord.setColumns(10);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.setBounds(10, 476, 89, 23);
		panelHorses.add(btnAddNew);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.setBounds(109, 476, 89, 23);
		panelHorses.add(btnEdit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(208, 476, 89, 23);
		panelHorses.add(btnDelete);
		
		JPanel panelHorseColor = new JPanel();
		tabbedPane.addTab("Horse Color", null, panelHorseColor, null);
		
		JPanel panelJockey = new JPanel();
		tabbedPane.addTab("Jockey", null, panelJockey, null);
		
		JPanel panelMeeting = new JPanel();
		tabbedPane.addTab("Meeting", null, panelMeeting, null);
		
		JPanel panelRace = new JPanel();
		tabbedPane.addTab("Race", null, panelRace, null);
		
		JPanel panelRaceDistance = new JPanel();
		tabbedPane.addTab("Race Distances", null, panelRaceDistance, null);
		
		JPanel panelRaceType = new JPanel();
		tabbedPane.addTab("Race Type", null, panelRaceType, null);
		
		JPanel panelFieldType = new JPanel();
		tabbedPane.addTab("Field Type", null, panelFieldType, null);
		
		JPanel panelParticipation = new JPanel();
		tabbedPane.addTab("Participation", null, panelParticipation, null);
		
		JPanel panelTrainer = new JPanel();
		tabbedPane.addTab("Trainer", null, panelTrainer, null);
		
		JPanel panelBreeder = new JPanel();
		tabbedPane.addTab("Breeder", null, panelBreeder, null);
		
		JPanel panelOwner = new JPanel();
		tabbedPane.addTab("Owner", null, panelOwner, null);
		
		JPanel panelFamily = new JPanel();
		tabbedPane.addTab("Family", null, panelFamily, null);
		
		JPanel panelSystemUser = new JPanel();
		tabbedPane.addTab("System User", null, panelSystemUser, null);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEditTables.dispose();
				ViewR1UserGUI window = new ViewR1UserGUI();
				window.frmPleaseChooseA.setVisible(true);
			}
		});
		btnBack.setBounds(662, 576, 89, 23);
		frmEditTables.getContentPane().add(btnBack);
	}
}
