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

public class ViewR1FieldType {

	private JFrame frmFieldType;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1FieldType window = new ViewR1FieldType();
					window.frmFieldType.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1FieldType() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFieldType = new JFrame();
		frmFieldType.setTitle("FIELD TYPE");
		frmFieldType.setBounds(100, 100, 424, 478);
		frmFieldType.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFieldType.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 376, 239);
		frmFieldType.getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Field Type Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 266, 365, 133);
		frmFieldType.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFieldType = new JLabel("Field Type:");
		lblFieldType.setBounds(10, 48, 65, 14);
		panel.add(lblFieldType);
		
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(85, 45, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//???????????????????????????
			}
		});
		btnAddNew.setBounds(10, 91, 89, 31);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//????????????????????????????????????
			}
		});
		btnUpdate.setBounds(106, 91, 89, 31);
		panel.add(btnUpdate);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFieldType.dispose();
				ViewR1UserGUI window = new ViewR1UserGUI();
				window.frmPleaseChooseA.setVisible(true);
			}
		});
		btnNewButton.setBounds(297, 399, 89, 34);
		frmFieldType.getContentPane().add(btnNewButton);
	}

}
