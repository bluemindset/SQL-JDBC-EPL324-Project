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

public class ViewR1Owner {

	private JFrame frmOwner;
	private JTable table;
	private JTextField textFieldId;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldUniform;
	private JTextField textFieldFamily;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1Owner window = new ViewR1Owner();
					window.getFrmOwner().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Owner() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmOwner(new JFrame());
		getFrmOwner().setTitle("OWNER");
		getFrmOwner().setBounds(100, 100, 412, 480);
		getFrmOwner().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmOwner().getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 376, 134);
		getFrmOwner().getContentPane().add(scrollPane);
	
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Owner Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 167, 376, 223);
		getFrmOwner().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 43, 46, 14);
		panel.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(85, 40, 86, 20);
		panel.add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 93, 72, 14);
		panel.add(lblFirstName);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(85, 90, 86, 20);
		panel.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(10, 139, 72, 14);
		panel.add(lblLastName);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(85, 136, 86, 20);
		panel.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JLabel lblUniform = new JLabel("Uniform:");
		lblUniform.setBounds(201, 43, 61, 14);
		panel.add(lblUniform);
		
		textFieldUniform = new JTextField();
		textFieldUniform.setBounds(263, 40, 86, 20);
		panel.add(textFieldUniform);
		textFieldUniform.setColumns(10);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//???????????????????????????????????????????????
			}
		});
		btnAddNew.setBounds(10, 189, 89, 23);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??????????????????????????????
			}
		});
		btnUpdate.setBounds(109, 189, 89, 23);
		panel.add(btnUpdate);
		
		JLabel lblFamily = new JLabel("Family:");
		lblFamily.setBounds(201, 93, 46, 14);
		panel.add(lblFamily);
		
		textFieldFamily = new JTextField();
		textFieldFamily.setBounds(263, 90, 86, 20);
		panel.add(textFieldFamily);
		textFieldFamily.setColumns(10);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmOwner().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnBack.setBounds(297, 401, 89, 30);
		getFrmOwner().getContentPane().add(btnBack);
	}

	public JFrame getFrmOwner() {
		return frmOwner;
	}

	public void setFrmOwner(JFrame frmOwner) {
		this.frmOwner = frmOwner;
	}

}
