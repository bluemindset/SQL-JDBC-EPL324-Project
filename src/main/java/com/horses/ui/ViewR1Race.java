package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewR1Race {

	private JFrame frmRace;
	private JTable table;
	private JTextField textFieldRaceTime;
	private JTextField textFieldPrize1;
	private JTextField textFieldPrize2;
	private JTextField textFieldPrize3;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1Race window = new ViewR1Race();
					window.getFrmRace().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Race() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmRace(new JFrame());
		getFrmRace().setTitle("RACE");
		getFrmRace().setBounds(100, 100, 503, 567);
		getFrmRace().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmRace().getContentPane().setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 454, 162);
		getFrmRace().getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Race Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 182, 454, 301);
		getFrmRace().getContentPane().add(panel);
		panel.setLayout(null);
		
		textFieldRaceTime = new JTextField();
		textFieldRaceTime.setBounds(120, 40, 86, 20);
		panel.add(textFieldRaceTime);
		textFieldRaceTime.setColumns(10);
		
		JLabel lblRaceTime = new JLabel("Race Time:");
		lblRaceTime.setBounds(10, 43, 73, 14);
		panel.add(lblRaceTime);
		
		JLabel lblNewLabel = new JLabel("Race Distance:");
		lblNewLabel.setBounds(10, 84, 86, 14);
		panel.add(lblNewLabel);
		
		JComboBox comboBoxDistance = new JComboBox();
		comboBoxDistance.setBounds(120, 81, 86, 20);
		panel.add(comboBoxDistance);
		
		JLabel lblPrize1 = new JLabel("Prize 1:");
		lblPrize1.setBounds(230, 43, 46, 14);
		panel.add(lblPrize1);
		
		textFieldPrize1 = new JTextField();
		textFieldPrize1.setBounds(334, 40, 86, 20);
		panel.add(textFieldPrize1);
		textFieldPrize1.setColumns(10);
		
		JLabel lblPrize2 = new JLabel("Prize 2:");
		lblPrize2.setBounds(230, 84, 46, 14);
		panel.add(lblPrize2);
		
		textFieldPrize2 = new JTextField();
		textFieldPrize2.setBounds(334, 81, 86, 20);
		panel.add(textFieldPrize2);
		textFieldPrize2.setColumns(10);
		
		JLabel lblPrize3 = new JLabel("Prize 3:");
		lblPrize3.setBounds(230, 128, 46, 14);
		panel.add(lblPrize3);
		
		textFieldPrize3 = new JTextField();
		textFieldPrize3.setBounds(334, 125, 86, 20);
		panel.add(textFieldPrize3);
		textFieldPrize3.setColumns(10);
		
		JLabel lblWinnings = new JLabel("Total Winnings:");
		lblWinnings.setBounds(230, 177, 94, 14);
		panel.add(lblWinnings);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(334, 174, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblRaceType = new JLabel("Race Type:");
		lblRaceType.setBounds(10, 128, 62, 14);
		panel.add(lblRaceType);
		
		JComboBox comboBoxRaceType = new JComboBox();
		comboBoxRaceType.setBounds(120, 125, 86, 20);
		panel.add(comboBoxRaceType);
		
		JLabel lblFieldType = new JLabel("Field Type:");
		lblFieldType.setBounds(10, 177, 62, 14);
		panel.add(lblFieldType);
		
		JComboBox comboBoxFieldType = new JComboBox();
		comboBoxFieldType.setBounds(120, 174, 86, 20);
		panel.add(comboBoxFieldType);
		
		JLabel lblMeetingDate = new JLabel("Meeting Date:");
		lblMeetingDate.setBounds(10, 219, 86, 14);
		panel.add(lblMeetingDate);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(120, 216, 86, 20);
		panel.add(comboBox);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/////////?????????????????????????????????
			}
		});
		btnAddNew.setBounds(10, 259, 89, 31);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??????????????????????????????????
			}
		});
		btnUpdate.setBounds(107, 259, 89, 31);
		panel.add(btnUpdate);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmRace().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
				
				
			}
		});
		btnBack.setBounds(375, 489, 89, 29);
		getFrmRace().getContentPane().add(btnBack);
	}

	public JFrame getFrmRace() {
		return frmRace;
	}

	public void setFrmRace(JFrame frmRace) {
		this.frmRace = frmRace;
	}
}
