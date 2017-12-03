package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class ViewR1HorseColor {

	private JFrame frmColorName;
	private JTextField textFieldColorName;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1HorseColor window = new ViewR1HorseColor();
					window.frmColorName.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1HorseColor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmColorName = new JFrame();
		frmColorName.setTitle("COLOR NAME");
		frmColorName.setBounds(100, 100, 416, 454);
		frmColorName.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmColorName.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				frmColorName.dispose();
					ViewR1UserGUI window = new ViewR1UserGUI();
					window.frmPleaseChooseA.setVisible(true);
			
			}
		});
		btnBack.setBounds(266, 350, 99, 36);
		frmColorName.getContentPane().add(btnBack);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//???????????????????????????????
			}
		});
		btnAddNew.setBounds(27, 350, 89, 36);
		frmColorName.getContentPane().add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//???????????????????????????????????????
			}
		});
		btnUpdate.setBounds(126, 350, 89, 36);
		frmColorName.getContentPane().add(btnUpdate);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 377, 240);
		frmColorName.getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Color Name Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 253, 375, 84);
		frmColorName.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel LblColorName = new JLabel("Color Name:");
		LblColorName.setBounds(0, 38, 82, 14);
		panel.add(LblColorName);
		
		textFieldColorName = new JTextField();
		textFieldColorName.setBounds(92, 35, 86, 20);
		panel.add(textFieldColorName);
		textFieldColorName.setColumns(10);
	}
}
