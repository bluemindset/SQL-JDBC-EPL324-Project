package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewR1Meeting {

	private JFrame frmMeeting;
	private JTable table;
	private JTextField textFieldDate;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnBack;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1Meeting window = new ViewR1Meeting();
					window.frmMeeting.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Meeting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMeeting = new JFrame();
		frmMeeting.setTitle("MEETING");
		frmMeeting.setBounds(100, 100, 482, 458);
		frmMeeting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMeeting.getContentPane().setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 446, 223);
		frmMeeting.getContentPane().add(scrollPane_1);
		
		scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Meeting Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 245, 436, 116);
		frmMeeting.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setBounds(10, 28, 46, 14);
		panel.add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(66, 25, 86, 20);
		panel.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.setBounds(119, 68, 101, 37);
		panel.add(btnNewButton_1);
		
		btnNewButton = new JButton("ADD NEW");
		btnNewButton.setBounds(10, 68, 99, 37);
		panel.add(btnNewButton);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnBack = new JButton("BACK");
		btnBack.setBounds(360, 372, 96, 37);
		frmMeeting.getContentPane().add(btnBack);
	}

}
