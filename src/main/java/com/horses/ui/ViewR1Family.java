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

public class ViewR1Family {

	private JFrame frmFamily;
	private JTable table;
	private JTextField textFieldFamilyName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1Family window = new ViewR1Family();
					window.getFrmFamily().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Family() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmFamily(new JFrame());
		getFrmFamily().setTitle("FAMILY");
		getFrmFamily().setBounds(100, 100, 418, 459);
		getFrmFamily().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmFamily().getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 382, 194);
		getFrmFamily().getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Family Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 216, 372, 150);
		getFrmFamily().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFamilyName = new JLabel("Family Name:");
		lblFamilyName.setBounds(10, 57, 88, 14);
		panel.add(lblFamilyName);
		
		textFieldFamilyName = new JTextField();
		textFieldFamilyName.setBounds(102, 54, 86, 20);
		panel.add(textFieldFamilyName);
		textFieldFamilyName.setColumns(10);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??????????????????????????????
			}
		});
		btnAddNew.setBounds(9, 116, 89, 23);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//???????????????????????????????????
			}
		});
		btnUpdate.setBounds(119, 116, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmFamily().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnBack.setBounds(303, 377, 89, 30);
		getFrmFamily().getContentPane().add(btnBack);
	}

	public JFrame getFrmFamily() {
		return frmFamily;
	}

	public void setFrmFamily(JFrame frmFamily) {
		this.frmFamily = frmFamily;
	}

}
