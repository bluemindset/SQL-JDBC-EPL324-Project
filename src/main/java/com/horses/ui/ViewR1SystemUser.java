package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewR1SystemUser {

	private JFrame frmSystemUser;
	private JTable table;
	private JTextField textFieldId;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;

	boolean addRecord = false;
	
	private void clearInputBoxesHorses() {
		
		textFieldId.setText("");
		textFieldUsername.setText("");
		textFieldPassword.setText("");
		textFieldFirstName.setText("");
		textFieldLastName.setText("");			
	  }
	
	public JFrame getFrmSystemUser() {
		return frmSystemUser;
	}

	public void setFrmSystemUser(JFrame frmSystemUser) {
		this.frmSystemUser = frmSystemUser;
	}
	
	private void loadRecords() throws SQLException  {
		
	    String sql_stmt = "SELECT * FROM [dbo].[SYSTEM_USER];";

	    ResultSetTableModel tableModel = new ResultSetTableModel(sql_stmt);
	    table.setModel(tableModel);
	    //////////////////////////////
	    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	    rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
	    table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		}

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1SystemUser window = new ViewR1SystemUser();
					window.getFrmSystemUser().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1SystemUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmSystemUser(new JFrame());
		getFrmSystemUser().setTitle("SYSTEM USER");
		getFrmSystemUser().setBounds(100, 100, 512, 469);
		getFrmSystemUser().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmSystemUser().getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 482, 126);
		getFrmSystemUser().getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "System User Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 148, 472, 233);
		getFrmSystemUser().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 40, 46, 14);
		panel.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(87, 37, 86, 20);
		panel.add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 85, 76, 14);
		panel.add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(87, 82, 86, 20);
		panel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 146, 76, 14);
		panel.add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(87, 140, 86, 20);
		panel.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(259, 40, 69, 14);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(259, 85, 69, 14);
		panel.add(lblLastName);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(362, 37, 86, 20);
		panel.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(362, 82, 86, 20);
		panel.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setBounds(259, 146, 46, 14);
		panel.add(lblRole);
		
		JComboBox comboBoxUserRole = new JComboBox();
		comboBoxUserRole.setBounds(362, 143, 86, 20);
		panel.add(comboBoxUserRole);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??????????????????????????
			}
		});
		btnAddNew.setBounds(22, 199, 89, 23);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//???????????????????????????????
			}
		});
		btnUpdate.setBounds(141, 199, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmSystemUser().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnBack.setBounds(401, 382, 89, 38);
		getFrmSystemUser().getContentPane().add(btnBack);
		
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	

}
