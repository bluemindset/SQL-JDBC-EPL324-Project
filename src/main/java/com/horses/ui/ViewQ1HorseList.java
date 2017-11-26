package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class ViewQ1HorseList {

	private JFrame frmHorseNameList;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewQ1HorseList window = new ViewQ1HorseList();
					window.frmHorseNameList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewQ1HorseList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHorseNameList = new JFrame();
		frmHorseNameList.setTitle("Horse Name List");
		frmHorseNameList.setBounds(100, 100, 442, 492);
		frmHorseNameList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHorseNameList.getContentPane().setLayout(null);
		
		JLabel lblPleaseEnterA = new JLabel("Please enter a horse name:");
		lblPleaseEnterA.setBounds(10, 11, 414, 22);
		frmHorseNameList.getContentPane().add(lblPleaseEnterA);
		
		textField = new JTextField();
		textField.setBounds(10, 40, 186, 22);
		frmHorseNameList.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("SEARCH");
		btnOk.setBounds(218, 40, 89, 23);
		frmHorseNameList.getContentPane().add(btnOk);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBounds(317, 40, 89, 23);
		frmHorseNameList.getContentPane().add(btnBack);
		
		table = new JTable();
		table.setBounds(10, 98, 406, 338);
		frmHorseNameList.getContentPane().add(table);
	}
}
