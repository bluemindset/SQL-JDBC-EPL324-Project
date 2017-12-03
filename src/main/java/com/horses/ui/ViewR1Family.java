package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewR1Family {

	private JFrame frmFamily;
	private JTable table;
	private JTextField textFieldFamilyName;
	
	boolean addRecord = false;
	
	private void clearInputBoxesHorses() {
		
	textFieldFamilyName.setText("");
    	
    }
	
	public JFrame getFrmFamily() {
	return frmFamily;
	}

	public void setFrmFamily(JFrame frmFamily) {
	this.frmFamily = frmFamily;
	}
	
	
	private void addNew() throws SQLException {
    	
	       String sql_stmt = "INSERT INTO [dbo].[FAMILY] ([name])";
	       sql_stmt += " VALUES ('" +  	textFieldFamilyName.getText() + "')";
	       
			CurrentUserData.executeSetUserId();
	       DBUtilities dbUtilities = new DBUtilities();
	       dbUtilities.ExecuteSQLStatement(sql_stmt);
			loadRecords();
	   }
	
	

	private void loadRecords() throws SQLException  {
		
	    String sql_stmt = "SELECT * FROM [dbo].[FAMILY];";

	    ResultSetTableModel tableModel = new ResultSetTableModel(sql_stmt);
	    table.setModel(tableModel);
	    //////////////////////////////
	    table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            try {
                if (table.getSelectedRow() >= 0) {
                
                        	
                    Object type = table.getValueAt(table.getSelectedRow(), 0);
       
                    textFieldFamilyName.setText(type.toString());    	
        
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
	        String sql_stmt = "DELETE FROM [dbo].[FAMILY] WHERE [name] = '" + textFieldFamilyName.getText() + "'";
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
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 382, 194);
		getFrmFamily().getContentPane().add(scrollPane);
				
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
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
				addRecord = true;
				try {
					addNew();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				clearInputBoxesHorses();
				textFieldFamilyName.requestFocus();
			}
		
		});
		btnAddNew.setBounds(9, 116, 89, 23);
		panel.add(btnAddNew);
		
		JButton btnDistance = new JButton("DELETE");
		btnDistance.addActionListener(new ActionListener() {
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
		btnDistance.setBounds(116, 116, 89, 23);
		panel.add(btnDistance);
		
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
		

		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
}
