package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewR1Breeder {

	private JFrame frmBreeder;
	private JTable table;
	private JTextField textFieldId;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1Breeder window = new ViewR1Breeder();
					window.getFrmBreeder().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Breeder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmBreeder(new JFrame());
		getFrmBreeder().setTitle("BREEDER");
		getFrmBreeder().setBounds(100, 100, 415, 479);
		getFrmBreeder().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmBreeder().getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 379, 159);
		getFrmBreeder().getContentPane().add(scrollPane);
				
	
		table = new JTable();
		scrollPane.setViewportView(table);
		
	
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Breeder Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 181, 369, 196);
		getFrmBreeder().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 42, 46, 14);
		panel.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(94, 39, 86, 20);
		panel.add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name: ");
		lblFirstName.setBounds(10, 78, 72, 14);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setBounds(10, 118, 57, 14);
		panel.add(lblLastName);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(94, 75, 86, 20);
		panel.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setText("");
		textFieldLastName.setBounds(94, 115, 86, 20);
		panel.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.setBounds(10, 162, 89, 23);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBounds(109, 162, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmBreeder().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnBack.setBounds(300, 394, 89, 36);
		getFrmBreeder().getContentPane().add(btnBack);
	}

	public JFrame getFrmBreeder() {
		return frmBreeder;
	}

	public void setFrmBreeder(JFrame frmBreeder) {
		this.frmBreeder = frmBreeder;
	}

}
