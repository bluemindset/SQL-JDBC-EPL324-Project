package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
	
	boolean addRecord = false;

	private void clearInputBoxesHorses() {
		
		textFieldDate.setText("");
		
	  }
	
	public JFrame getFrmMeeting() {
		return frmMeeting;
	}

	public void setFrmMeeting(JFrame frmMeeting) {
		this.frmMeeting = frmMeeting;
	}
	
	private void loadRecords() throws SQLException  {
		
	    String sql_stmt = "SELECT * FROM [dbo].[MEETING];";

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
					ViewR1Meeting window = new ViewR1Meeting();
					window.getFrmMeeting().setVisible(true);
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
		setFrmMeeting(new JFrame());
		getFrmMeeting().setTitle("MEETING");
		getFrmMeeting().setBounds(100, 100, 482, 458);
		getFrmMeeting().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmMeeting().getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 446, 223);
		getFrmMeeting().getContentPane().add(scrollPane);

		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Meeting Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 245, 436, 116);
		getFrmMeeting().getContentPane().add(panel);
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
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmMeeting().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnBack.setBounds(360, 372, 96, 37);
		getFrmMeeting().getContentPane().add(btnBack);
		
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	

}
