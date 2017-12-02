package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class ViewR1Trainer {

	private JFrame frmTrainer;
	private JTable table;
	private JTextField textFieldID;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1Trainer window = new ViewR1Trainer();
					window.frmTrainer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Trainer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrainer = new JFrame();
		frmTrainer.setTitle("TRAINER");
		frmTrainer.setBounds(100, 100, 465, 424);
		frmTrainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrainer.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 429, 155);
		frmTrainer.getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Trainer Record  Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 177, 419, 135);
		frmTrainer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID: ");
		lblId.setBounds(10, 22, 46, 14);
		panel.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(105, 19, 86, 20);
		panel.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name: ");
		lblFirstName.setBounds(10, 56, 65, 14);
		panel.add(lblFirstName);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(105, 50, 86, 20);
		panel.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setBounds(10, 97, 65, 14);
		panel.add(lblLastName);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(105, 94, 86, 20);
		panel.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmTrainer.dispose();
				ViewR1UserGUI window = new ViewR1UserGUI();
				window.frmPleaseChooseA.setVisible(true);
			}
		});
		btnBack.setBounds(344, 343, 95, 32);
		frmTrainer.getContentPane().add(btnBack);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//???????????????????????????????????????
			}
		});
		btnAddNew.setBounds(10, 339, 95, 32);
		frmTrainer.getContentPane().add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//?????????????????????????????
			}
		});
		btnUpdate.setBounds(115, 339, 101, 32);
		frmTrainer.getContentPane().add(btnUpdate);
	}

}
