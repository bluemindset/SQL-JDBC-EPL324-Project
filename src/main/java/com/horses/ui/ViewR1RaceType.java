package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewR1RaceType {

	
	private JFrame frmRaceType;
	private JTable table;
	private JTextField textFieldRaceType;

	
	boolean addRecord = false;
	
	private void clearInputBoxesHorses() {
		textFieldRaceType.setText("");
    	
    }

	public JFrame getFrame() {
		return frmRaceType;
	}

	public void setFrame(JFrame frame) {
		this.frmRaceType = frame;
		frmRaceType.setTitle("RACE TYPE");
	}
	
	private void addNew() throws SQLException {
    	
	       String sql_stmt = "INSERT INTO [dbo].[RACE_TYPE] ([type])";
	       sql_stmt += " VALUES ('" +  	textFieldRaceType.getText() + "')";
	       
			CurrentUserData.executeSetUserId();
	       DBUtilities dbUtilities = new DBUtilities();
	       dbUtilities.ExecuteSQLStatement(sql_stmt);
			loadRecords();
	   }
	
	
private void loadRecords() throws SQLException  {
		
	    String sql_stmt = "SELECT * FROM [dbo].[RACE_TYPE];";

	    ResultSetTableModel tableModel = new ResultSetTableModel(sql_stmt);
	    table.setModel(tableModel);
	  
	    table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
         try {
             if (table.getSelectedRow() >= 0) {
             
                     	
                 Object type = table.getValueAt(table.getSelectedRow(), 0);
    
                 textFieldRaceType.setText(type.toString());    	
     
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
	        String sql_stmt = "DELETE FROM [dbo].[RACE_TYPE] WHERE [type] = '" + textFieldRaceType.getText() + "'";
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
					ViewR1RaceType window = new ViewR1RaceType();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1RaceType() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 415, 465);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 6, 377, 222);
		getFrame().getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Race Type Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 239, 377, 135);
		getFrame().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRaceType = new JLabel("Race Type:");
		lblRaceType.setBounds(10, 35, 74, 14);
		panel.add(lblRaceType);
		
		textFieldRaceType = new JTextField();
		textFieldRaceType.setBounds(95, 32, 86, 20);
		panel.add(textFieldRaceType);
		textFieldRaceType.setColumns(10);
		
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
				textFieldRaceType.requestFocus();
			}
		});
		btnAddNew.setBounds(10, 87, 89, 32);
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
		btnDelete.setBounds(109, 87, 89, 28);
		panel.add(btnDelete);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrame().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnBack.setBounds(298, 385, 89, 30);
		getFrame().getContentPane().add(btnBack);
		
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	

}
