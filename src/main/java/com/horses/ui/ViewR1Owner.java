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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ViewR1Owner {

	private JFrame frmOwner;
	private JTable table;
	private JTextField textFieldId;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldUniform;
	private JComboBox comboBoxTitle;
	private JComboBox comboBoxFamilyName ;
	
	boolean addRecord = false;
	
private void clearInputBoxesHorses() {
		
	textFieldId.setText("");
	textFieldFirstName.setText("");
	textFieldLastName.setText("");
	textFieldUniform.setText("");
	comboBoxTitle.setSelectedItem("");	
	comboBoxFamilyName.setSelectedItem("");	
	  }

public JFrame getFrmOwner() {
	return frmOwner;
}

public void setFrmOwner(JFrame frmOwner) {
	this.frmOwner = frmOwner;
}


private void addNew() throws SQLException {
	
	String title ="";

	try {
		title = comboBoxTitle.getSelectedItem().toString();
	} catch ( NullPointerException e) {
		title ="";
	}
	
	String sql_stmt = "INSERT INTO [dbo].[OWNER] ([id],[first_name],[last_name],[uniform],[title],[onwer_family])";
    sql_stmt += " VALUES ('" +  textFieldId.getText() + "','" +
    						textFieldFirstName.getText() + "','"+						
    						textFieldLastName.getText()  + "','" + 
    					textFieldUniform.getText() + "','" + 
    					comboBoxTitle.getSelectedItem().toString() +"','" +
    					comboBoxFamilyName.getSelectedItem().toString() +
    					"')";

		CurrentUserData.executeSetUserId();
       DBUtilities dbUtilities = new DBUtilities();
       dbUtilities.ExecuteSQLStatement(sql_stmt);
		loadRecords();
   }

private void updateRecord() throws SQLException {
	String title ="";

	try {
		title = comboBoxTitle.getSelectedItem().toString();
	} catch ( NullPointerException e) {
		title ="";
	}

    
    String sql_stmt = "UPDATE [dbo].[OWNER] SET [first_name] = '" + textFieldFirstName.getText() + "'";
    sql_stmt += ",[last_name] = '" + textFieldLastName.getText() + "'";
    sql_stmt += ",[uniform] = '" + textFieldUniform.getText() + "'";
    sql_stmt += ",[title] = '" + comboBoxTitle.getSelectedItem().toString() + "'";
    sql_stmt += ",[onwer_family] = '" + comboBoxFamilyName.getSelectedItem().toString() + "'";
    
    sql_stmt += " WHERE id = '" + textFieldId.getText() + "'";


    DBUtilities dbUtilities = new DBUtilities();
    dbUtilities.ExecuteSQLStatement(sql_stmt);
    
    loadRecords();
}

private void loadRecords() throws SQLException  {
	
    String sql_stmt = "SELECT * FROM [dbo].[OWNER];";

    ResultSetTableModel tableModel = new ResultSetTableModel(sql_stmt);
    table.setModel(tableModel);
  
    table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
     try {
         if (table.getSelectedRow() >= 0) {
         
                 	
             Object id = table.getValueAt(table.getSelectedRow(), 0);
             Object First_name = table.getValueAt(table.getSelectedRow(), 1);
               Object Last_name = table.getValueAt(table.getSelectedRow(), 2);
               Object uniform = table.getValueAt(table.getSelectedRow(),4 );
               Object title = table.getValueAt(table.getSelectedRow(), 5);
               Object family = table.getValueAt(table.getSelectedRow(), 6);

                textFieldId.setText(id.toString());  
                textFieldFirstName.setText(First_name.toString()); 
                textFieldLastName.setText(Last_name.toString()); 
                textFieldUniform.setText(uniform.toString());
                comboBoxTitle.setSelectedItem(title.toString());
                comboBoxFamilyName.setSelectedItem(family.toString());
 
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
		getFrmOwner().setBounds(100, 100, 412, 498);
		getFrmOwner().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmOwner().getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 376, 134);
		getFrmOwner().getContentPane().add(scrollPane);
	
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Owner Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 167, 376, 251);
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
		btnAddNew.setBounds(10, 217, 89, 23);
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
		btnUpdate.setBounds(109, 217, 89, 23);
		panel.add(btnUpdate);
		
		comboBoxTitle = new JComboBox();
		comboBoxTitle.insertItemAt("", 0);
		comboBoxTitle.addItem("Mr.");
		comboBoxTitle.addItem("Dr.");
		comboBoxTitle.addItem("Miss.");
		comboBoxTitle.addItem("Mrs.");
		comboBoxTitle.setBounds(263, 136, 86, 20);
		panel.add(comboBoxTitle);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(201, 139, 46, 14);
		panel.add(lblTitle);
		
		JLabel lblFamilyName = new JLabel("Family Name:");
		lblFamilyName.setBounds(185, 93, 77, 14);
		panel.add(lblFamilyName);
		
		 comboBoxFamilyName = new JComboBox();
		 try {
	    	 String sql_stmt = "SELECT * FROM [dbo].[FAMILY];";
	    	 ResultSetTableModel sexCombo = new ResultSetTableModel(sql_stmt);
	    	 for(int i=0; i< sexCombo.getRowCount(); i++){
	    		 String s = (sexCombo.getValueAt(i, 0).toString());
	    		 comboBoxFamilyName.addItem(s);
	    	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxFamilyName.setBounds(263, 90, 86, 20);
		panel.add(comboBoxFamilyName);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmOwner().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnBack.setBounds(297, 419, 89, 30);
		getFrmOwner().getContentPane().add(btnBack);
		
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
