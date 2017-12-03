package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ViewR1FieldType {

	private JFrame frmFieldType;
	private JTable table;
	private JTextField textField;

	boolean addRecord = false;
	
	private void clearInputBoxesHorses() {
		
		textField.setText("");
	    	
	    }

	public JFrame getFrmFieldType() {
		return frmFieldType;
	}

	public void setFrmFieldType(JFrame frmFieldType) {
		this.frmFieldType = frmFieldType;
	}
	
	
	
	 private void addNew() throws SQLException {
	    	
       String sql_stmt = "INSERT INTO [dbo].[FIELD_TYPE] ([type])";
       sql_stmt += " VALUES ('" +  	textField.getText() + "')";
       
		CurrentUserData.executeSetUserId();
       DBUtilities dbUtilities = new DBUtilities();
       dbUtilities.ExecuteSQLStatement(sql_stmt);
		loadRecords();
   }
	 
	 private void updateRecord() throws SQLException {

        
        String sql_stmt = "UPDATE [dbo].[FIELD_TYPE] SET [type] = '" + textField.getText() + "'";
	        
			CurrentUserData.executeSetUserId();

	        DBUtilities dbUtilities = new DBUtilities();
	        dbUtilities.ExecuteSQLStatement(sql_stmt);
	        
	        loadRecords();
    }
 
	 private void loadRecords() throws SQLException  {
			
		    String sql_stmt = "SELECT * FROM [dbo].[FIELD_TYPE];";

		    ResultSetTableModel tableModel = new ResultSetTableModel(sql_stmt);
		    table.setModel(tableModel);
		    //////////////////////////////
		    table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
	            try {
	                if (table.getSelectedRow() >= 0) {
	                
	                        	
	                    Object type = table.getValueAt(table.getSelectedRow(), 0);
	       
	                    textField.setText(type.toString());    	
	                	
	                    
//						try {
//							checkBoxIsPurebred.setSelected(Boolean.parseBoolean(is_purebred.toString()));
//						} catch ( NullPointerException e) {
//							checkBoxIsPurebred.setSelected(false);
//						}
//						try {
//							comboBoxColor.setSelectedItem(color_name.toString());
//						} catch ( NullPointerException e) {
//							comboBoxColor.setSelectedItem(null);
//						}
//						try {
//							comboBoxTrainer.setSelectedItem(trainer_id.toString());
//						} catch ( NullPointerException e) {
//							comboBoxTrainer.setSelectedItem(null);
//						}
//						try {
//							comboBoxOwner.setSelectedItem(owner_id.toString());						
//						} catch ( NullPointerException e) {
//							comboBoxOwner.setSelectedItem(null);
//						}
//						try {
//							comboBoxBreeder.setSelectedItem(breeder_id.toString());
//						} catch ( NullPointerException e) {
//							comboBoxBreeder.setSelectedItem(null);
//						}
//						try {						
//							comboBoxDad.setSelectedItem(dad_id.toString());
//						} catch ( NullPointerException e) {
//							comboBoxDad.setSelectedItem(null);
//						}
//						try {
//							comboBoxMom.setSelectedItem(mama_id.toString());						
//						} catch ( NullPointerException e) {
//							comboBoxMom.setSelectedItem(null);
//						}
//						try {
//							textFieldOriginCountry.setText(origin_country.toString());
//						} catch ( NullPointerException e) {
//							textFieldOriginCountry.setText(null);
//						}
//						try {
//							textFieldRecord.setText(record.toString());
//						} catch (NullPointerException e) {
//							textFieldRecord.setText(null);	
//						} 
//						try {
//							comboBoxJockey.setSelectedItem(jockey_id.toString());						
//						} catch ( NullPointerException e) {
//							comboBoxJockey.setSelectedItem(null);
//						}
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
		    

	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1FieldType window = new ViewR1FieldType();
					window.getFrmFieldType().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1FieldType() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmFieldType(new JFrame());
		getFrmFieldType().setTitle("FIELD TYPE");
		getFrmFieldType().setBounds(100, 100, 424, 478);
		getFrmFieldType().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmFieldType().getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 376, 239);		
		getFrmFieldType().getContentPane().add(scrollPane);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Field Type Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 266, 365, 133);
		getFrmFieldType().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFieldType = new JLabel("Field Type:");
		lblFieldType.setBounds(10, 48, 65, 14);
		panel.add(lblFieldType);
		
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(85, 45, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
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
				textField.requestFocus();
			}
			
		});
		btnAddNew.setBounds(10, 91, 89, 31);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateRecord();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(106, 91, 89, 31);
		panel.add(btnUpdate);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmFieldType().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnNewButton.setBounds(297, 399, 89, 34);
		getFrmFieldType().getContentPane().add(btnNewButton);
			
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	

}
