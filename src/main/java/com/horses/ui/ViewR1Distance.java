package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	
	private void addNew() throws SQLException {
    	
	       String sql_stmt = "INSERT INTO [dbo].[RACE_DISTANCE] ([distance])";
	       sql_stmt += " VALUES ('" +  	textFieldRaceDistance.getText() + "')";
	       
			CurrentUserData.executeSetUserId();
	       DBUtilities dbUtilities = new DBUtilities();
	       dbUtilities.ExecuteSQLStatement(sql_stmt);
			loadRecords();
	   }
		 
	 
		 private void loadRecords() throws SQLException  {
				
			    String sql_stmt = "SELECT * FROM [dbo].[RACE_DISTANCE];";

			    ResultSetTableModel tableModel = new ResultSetTableModel(sql_stmt);
			    table.setModel(tableModel);
			    //////////////////////////////
			    table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
		            try {
		                if (table.getSelectedRow() >= 0) {
		                
		                        	
		                    Object type = table.getValueAt(table.getSelectedRow(), 0);
		       
		                    textFieldRaceDistance.setText(type.toString());    	
		        
		                }
		            } catch (Exception ex) {
		            	ex.printStackTrace();
		                System.out.println(ex.getMessage());
		            }
		        });
		        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
		        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		    }
			    
		  private void deleteRecord() throws SQLException {
		        String sql_stmt = "DELETE FROM [dbo].[RACE_DISTANCE] WHERE [distance] = '" + textFieldRaceDistance.getText() + "'";
		        DBUtilities dbUtilities = new DBUtilities();
		        dbUtilities.ExecuteSQLStatement(sql_stmt);
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
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRecord = true;
				try {
					addNew();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				clearInputBoxesHorses();
				textFieldRaceDistance.requestFocus();
			}
		
		});
		btnAddNew.setBounds(10, 87, 89, 20);
		panel.add(btnAddNew);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm Delete Record?", JOptionPane.YES_NO_OPTION);

		        if (dialogResult == JOptionPane.YES_OPTION) {
		            try {
		                deleteRecord();

		                loadRecords();
		            } catch (SQLException ex) {
		                System.out.println(ex.getMessage());
		            }
		        }
			}
		});
		btnDelete.setBounds(102, 86, 89, 23);
		panel.add(btnDelete);
		
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
