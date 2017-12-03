package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewR1Jockey {

	private JFrame frmJockey;
	private JTable table;
	private JTextField textFieldId;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JLabel lblFirstName;
	
	boolean addRecord = false;
	
private void clearInputBoxesHorses() {
		
	textFieldId.setText("");
	textFirstName.setText("");
	textLastName.setText("");
  }
	
public JFrame getFrmJockey() {
	return frmJockey;
}

public void setFrmJockey(JFrame frmJockey) {
	this.frmJockey = frmJockey;
}

private void addNew() throws SQLException {
	
	String sql_stmt = "INSERT INTO [dbo].[JOCKEY] ([id],[first_name],[last_name])";
    sql_stmt += " VALUES ('" +  textFieldId.getText() + "','" +
    		textFirstName.getText() + "','"+						
    		textLastName.getText()  
    					+ "')";

		CurrentUserData.executeSetUserId();
       DBUtilities dbUtilities = new DBUtilities();
       dbUtilities.ExecuteSQLStatement(sql_stmt);
		loadRecords();
   }
private void updateRecord() throws SQLException {
	
    
    
    String sql_stmt = "UPDATE [dbo].[JOCKEY] SET [first_name] = '" + textFirstName.getText() + "'";
    sql_stmt += ",[last_name] = '" + textLastName.getText() + "'";
    sql_stmt += " WHERE id = '" + textFieldId.getText() + "'";


    DBUtilities dbUtilities = new DBUtilities();
    dbUtilities.ExecuteSQLStatement(sql_stmt);
    
    loadRecords();
}

private void loadRecords() throws SQLException  {
	
    String sql_stmt = "SELECT * FROM [dbo].[JOCKEY];";

    ResultSetTableModel tableModel = new ResultSetTableModel(sql_stmt);
    table.setModel(tableModel);
  
    table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
     try {
         if (table.getSelectedRow() >= 0) {
         
                 	
             Object id = table.getValueAt(table.getSelectedRow(), 0);
             Object First_name = table.getValueAt(table.getSelectedRow(), 1);
                Object Last_name = table.getValueAt(table.getSelectedRow(), 2);

                textFieldId.setText(id.toString());  
                textFirstName.setText(First_name.toString()); 
                textLastName.setText(Last_name.toString()); 
 
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
					ViewR1Jockey window = new ViewR1Jockey();
					window.getFrmJockey().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Jockey() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmJockey(new JFrame());
		getFrmJockey().setTitle("JOCKEY");
		getFrmJockey().setBounds(100, 100, 525, 495);
		getFrmJockey().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmJockey().getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmJockey().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
		
			}
		});
		btnBack.setBounds(382, 406, 97, 40);
		getFrmJockey().getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 471, 184);	
		getFrmJockey().getContentPane().add(scrollPane);
				
	
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Jockey Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 206, 471, 189);
		getFrmJockey().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 24, 31, 23);
		panel.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(93, 25, 86, 20);
		panel.add(textFieldId);
		textFieldId.setColumns(10);
		
		lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 58, 59, 14);
		panel.add(lblFirstName);
		
		textFirstName = new JTextField();
		textFirstName.setBounds(93, 56, 86, 20);
		panel.add(textFirstName);
		textFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setBounds(10, 93, 59, 14);
		panel.add(lblLastName);
		
		textLastName = new JTextField();
		textLastName.setBounds(93, 90, 86, 20);
		panel.add(textLastName);
		textLastName.setColumns(10);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBounds(128, 146, 97, 32);
		panel.add(btnUpdate);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.setBounds(10, 146, 97, 32);
		panel.add(btnAddNew);
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
}
