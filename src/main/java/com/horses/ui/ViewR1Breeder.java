package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ViewR1Breeder {

	private JFrame frmBreeder;
	private JTable table;
	private JTextField textFieldId;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	
	
	boolean addRecord = false;
	
	
	private void clearInputBoxesHorses() {
		textFieldId.setText("");
		textFieldFirstName.setText("");
		textFieldLastName.setText("");
    	
    }
	
	public JFrame getFrmBreeder() {
		return frmBreeder;
	}

	public void setFrmBreeder(JFrame frmBreeder) {
		this.frmBreeder = frmBreeder;
	}
	
	
	private void addNew() throws SQLException {
    	
		String sql_stmt = "INSERT INTO [dbo].[BREEDER] ([id],[first_name],[last_name])";
        sql_stmt += " VALUES ('" +  textFieldId.getText() + "','" +
        						textFieldFirstName.getText() + "','"+						
        						textFieldLastName.getText()  
        					+ "')";

			CurrentUserData.executeSetUserId();
	       DBUtilities dbUtilities = new DBUtilities();
	       dbUtilities.ExecuteSQLStatement(sql_stmt);
			loadRecords();
	   }
	
	 private void updateRecord() throws SQLException {
	    	
	       
	        
	        String sql_stmt = "UPDATE [dbo].[BREEDER] SET [first_name] = '" + textFieldFirstName.getText() + "'";
	        sql_stmt += ",[last_name] = '" + textFieldLastName.getText() + "'";
	        sql_stmt += " WHERE id = '" + textFieldId.getText() + "'";


	        DBUtilities dbUtilities = new DBUtilities();
	        dbUtilities.ExecuteSQLStatement(sql_stmt);
	        
	        loadRecords();
	    }
	
	 private void loadRecords() throws SQLException  {
			
		    String sql_stmt = "SELECT * FROM [dbo].[BREEDER];";

		    ResultSetTableModel tableModel = new ResultSetTableModel(sql_stmt);
		    table.setModel(tableModel);
		  
		    table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
	         try {
	             if (table.getSelectedRow() >= 0) {
	             
	                     	
	                 Object id = table.getValueAt(table.getSelectedRow(), 0);
	                 Object First_name = table.getValueAt(table.getSelectedRow(), 1);
	                    Object Last_name = table.getValueAt(table.getSelectedRow(), 2);
	    
	                 textFieldId.setText(id.toString());  
	                 textFieldFirstName.setText(First_name.toString()); 
	                 textFieldLastName.setText(Last_name.toString()); 
	     
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
					ViewR1Breeder window = new ViewR1Breeder();
					window.getFrmBreeder().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Breeder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmBreeder(new JFrame());
		getFrmBreeder().setTitle("BREEDER");
		getFrmBreeder().setBounds(100, 100, 415, 479);
		getFrmBreeder().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmBreeder().getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 379, 159);
		getFrmBreeder().getContentPane().add(scrollPane);
				
	
		table = new JTable();
		scrollPane.setViewportView(table);
		
	
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Breeder Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 181, 369, 196);
		getFrmBreeder().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 42, 46, 14);
		panel.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(94, 39, 86, 20);
		panel.add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name: ");
		lblFirstName.setBounds(10, 78, 72, 14);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setBounds(10, 118, 57, 14);
		panel.add(lblLastName);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(94, 75, 86, 20);
		panel.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setText("");
		textFieldLastName.setBounds(94, 115, 86, 20);
		panel.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				addRecord = true;
				try {
					addNew();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clearInputBoxesHorses();
				textFieldId.requestFocus();
				
			}
		});
		btnAddNew.setBounds(10, 162, 89, 23);
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
		btnUpdate.setBounds(109, 162, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmBreeder().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnBack.setBounds(300, 394, 89, 36);
		getFrmBreeder().getContentPane().add(btnBack);
		
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	

}
