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

public class ViewR1Distance {

	private JFrame frmRaceDistance;
	private JTable table;
	private JTextField textFieldRaceDistance;

	boolean addRecord = false;
	
	
	private void clearInputBoxesHorses() {
		
		textFieldRaceDistance.setText("");
    	
    }
	
	public JFrame getFrmRaceDistance() {
		return frmRaceDistance;
	}

	public void setFrmRaceDistance(JFrame frmRaceDistance) {
		this.frmRaceDistance = frmRaceDistance;
	}
	
	
	private void loadRecords() throws SQLException  {
    	
        String sql_stmt = "SELECT * FROM [dbo].[RACE_DISTANCE];";

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
					ViewR1Distance window = new ViewR1Distance();
					window.getFrmRaceDistance().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Distance() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmRaceDistance(new JFrame());
		getFrmRaceDistance().setTitle("RACE DISTANCE");
		getFrmRaceDistance().setBounds(100, 100, 428, 469);
		getFrmRaceDistance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmRaceDistance().getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 392, 233);
		getFrmRaceDistance().getContentPane().add(scrollPane);
				
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Distance Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(20, 255, 382, 122);
		getFrmRaceDistance().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRaceDistance = new JLabel("Race Distance:");
		lblRaceDistance.setBounds(10, 39, 90, 14);
		panel.add(lblRaceDistance);
		
		textFieldRaceDistance = new JTextField();
		textFieldRaceDistance.setBounds(105, 39, 86, 20);
		panel.add(textFieldRaceDistance);
		textFieldRaceDistance.setColumns(10);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.setBounds(10, 87, 89, 20);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBounds(114, 87, 89, 20);
		panel.add(btnUpdate);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmRaceDistance().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnNewButton.setBounds(313, 388, 89, 35);
		getFrmRaceDistance().getContentPane().add(btnNewButton);
		
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	

}
