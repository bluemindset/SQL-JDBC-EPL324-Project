package com.horses.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewQ9ProgressReports extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableHorses;
	private JTable tableTrainers;
	private JTable tableOwners;
	private JTable tableFamilies;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewQ9ProgressReports dialog = new ViewQ9ProgressReports();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialLoadRecords() throws SQLException  {
		String cstmtString = "{call Query9_a_allHorses}";
        ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString);
        tableHorses.setModel(tableModel);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        tableHorses.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        tableHorses.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);      
        //************************ TIMESTAMP*************************
        tableHorses.getColumnModel().getColumn(2).setCellRenderer(new TimeStampCellRenderer());

        
		String cstmtString1 = "{call Query9_b_allTrainers}";
        ResultSetTableModel tableModel1 = new ResultSetTableModel(cstmtString1);
        tableTrainers.setModel(tableModel1);
        DefaultTableCellRenderer rightRenderer1 = new DefaultTableCellRenderer();
        rightRenderer1.setHorizontalAlignment(SwingConstants.LEFT);
        tableTrainers.getColumnModel().getColumn(0).setCellRenderer(rightRenderer1);
        tableTrainers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);      
        //************************ TIMESTAMP*************************
        tableTrainers.getColumnModel().getColumn(3).setCellRenderer(new TimeStampCellRenderer());
        
        
		String cstmtString2 = "{call Query9_c_allOwners}";
        ResultSetTableModel tableModel2 = new ResultSetTableModel(cstmtString2);
        tableOwners.setModel(tableModel2);
        DefaultTableCellRenderer rightRenderer2 = new DefaultTableCellRenderer();
        rightRenderer2.setHorizontalAlignment(SwingConstants.LEFT);
        tableOwners.getColumnModel().getColumn(0).setCellRenderer(rightRenderer2);
        tableOwners.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);      
        //************************ TIMESTAMP*************************
        tableOwners.getColumnModel().getColumn(3).setCellRenderer(new TimeStampCellRenderer());
        
        
		String cstmtString3= "{call Query9_d_allOwnersFam}";
        ResultSetTableModel tableModel3 = new ResultSetTableModel(cstmtString3);
        tableFamilies.setModel(tableModel3);
        DefaultTableCellRenderer rightRenderer3 = new DefaultTableCellRenderer();
        rightRenderer3.setHorizontalAlignment(SwingConstants.LEFT);
        tableFamilies.getColumnModel().getColumn(0).setCellRenderer(rightRenderer3);
        tableFamilies.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);     
        //************************ TIMESTAMP*************************
        tableFamilies.getColumnModel().getColumn(3).setCellRenderer(new TimeStampCellRenderer());
	}
	
	private void loadRecords_a(String year) throws SQLException  {
		boolean err = false;
    	int sel_year = 0;
        try { 
        	sel_year =  Integer.parseInt(year); 
        } catch(NumberFormatException e) { 
       	 	err=true;
        } catch(NullPointerException e) {
       	 	err=true;
        }
        
        if(err==true || sel_year==0){
    		
    		initialLoadRecords();
    	}
        else{
        	String cstmtString = "{call Query9_a_oneHorse(?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, year);
            tableHorses.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            tableHorses.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            tableHorses.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        
            
          //************************ TIMESTAMP*************************
            tableHorses.getColumnModel().getColumn(2).setCellRenderer(new TimeStampCellRenderer());
        }
    }
	
	private void loadRecords_a_date(String date, String time) throws SQLException  {

        if(date == null || time == null){
    		initialLoadRecords();
    	}
        else{
        	String cstmtString = "{call Query9_a_Date(?,?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, date, time);
            tableHorses.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            tableHorses.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            tableHorses.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);     
            
          //************************ TIMESTAMP*************************
            tableHorses.getColumnModel().getColumn(2).setCellRenderer(new TimeStampCellRenderer());
        }
    }
	
	
	
	
	private void loadRecords_b(String year) throws SQLException  {
		boolean err = false;
    	int sel_year = 0;
        try { 
        	sel_year =  Integer.parseInt(year); 
        } catch(NumberFormatException e) { 
       	 	err=true;
        } catch(NullPointerException e) {
       	 	err=true;
        }
        
        if(err==true || sel_year==0){
    		
    		initialLoadRecords();
    	}
        else{
        	String cstmtString = "{call Query9_b_oneTrainer(?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, year);
            tableTrainers.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            tableTrainers.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            tableTrainers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);     
            
          //************************ TIMESTAMP*************************
            tableTrainers.getColumnModel().getColumn(3).setCellRenderer(new TimeStampCellRenderer());
        }
    }
	
	
	private void loadRecords_b_date(String date, String time) throws SQLException  {

        if(date == null || time == null){
    		initialLoadRecords();
    	}
        else{
        	String cstmtString = "{call Query9_b_Date(?,?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, date, time);
            tableTrainers.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            tableTrainers.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            tableTrainers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);     
            
          //************************ TIMESTAMP*************************
            tableTrainers.getColumnModel().getColumn(3).setCellRenderer(new TimeStampCellRenderer());
        }
    }
	
	
	
	private void loadRecords_c(String year) throws SQLException  {
		boolean err = false;
    	int sel_year = 0;
        try { 
        	sel_year =  Integer.parseInt(year); 
        } catch(NumberFormatException e) { 
       	 	err=true;
        } catch(NullPointerException e) {
       	 	err=true;
        }
        
        if(err==true || sel_year==0){
    		
    		initialLoadRecords();
    	}
        else{
        	String cstmtString = "{call Query9_c_oneOwner(?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, year);
            tableOwners.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            tableOwners.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            tableOwners.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);     
            
          //************************ TIMESTAMP*************************
            tableOwners.getColumnModel().getColumn(3).setCellRenderer(new TimeStampCellRenderer());
        }
    }
	
	
	private void loadRecords_c_date(String date, String time) throws SQLException  {

        if(date == null || time == null){
    		initialLoadRecords();
    	}
        else{
        	String cstmtString = "{call Query9_c_Date(?,?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, date, time);
            tableOwners.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            tableOwners.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            tableOwners.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);     
            
          //************************ TIMESTAMP*************************
            tableOwners.getColumnModel().getColumn(3).setCellRenderer(new TimeStampCellRenderer());
        }
    }
	
	
	
	private void loadRecords_d(String s) throws SQLException  {
	
        System.out.println(s);
        
        if(s == ""){
    		initialLoadRecords();
    	}
        else{
        	String cstmtString = "{call Query9_d_oneOwnerFam1(?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, s);
            tableFamilies.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            tableFamilies.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            tableFamilies.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);     
            
          //************************ TIMESTAMP*************************
          //  tableFamilies.getColumnModel().getColumn(3).setCellRenderer(new TimeStampCellRenderer());
        }
    }
	
	
	private void loadRecords_d_date(String date, String time) throws SQLException  {

        if(date == null || time == null){
    		initialLoadRecords();
    	}
        else{
        	String cstmtString = "{call Query9_d_Date(?,?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, date, time);
            tableFamilies.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            tableFamilies.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            tableFamilies.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);     
            
          //************************ TIMESTAMP*************************
            tableFamilies.getColumnModel().getColumn(3).setCellRenderer(new TimeStampCellRenderer());
        }
    }
	
	/**
	 * Create the dialog.
	 */
	public ViewQ9ProgressReports() {
		setTitle("Progress Reports");
		setBounds(100, 100, 519, 548);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 482, 467);
		contentPanel.add(tabbedPane);
		
		JPanel panel_Horses = new JPanel();
		tabbedPane.addTab("HORSES", null, panel_Horses, null);
		panel_Horses.setLayout(null);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a horse:");
		lblPleaseSelectA.setBounds(10, 11, 153, 14);
		panel_Horses.add(lblPleaseSelectA);
		
		JComboBox comboBox_horse = new JComboBox();
		comboBox_horse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					loadRecords_a(comboBox_horse.getSelectedItem().toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		comboBox_horse.insertItemAt("", 0);
		try {
		   	 String sql_stmt = "SELECT * FROM [dbo].[HORSE];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBox_horse.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_horse.setBounds(228, 8, 239, 20);
		panel_Horses.add(comboBox_horse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 109, 457, 314);
		panel_Horses.add(scrollPane);
		
		tableHorses = new JTable();
		scrollPane.setViewportView(tableHorses);
		
		JLabel lblPleaseSelectA_3 = new JLabel("Please select a meeting:");
		lblPleaseSelectA_3.setBounds(10, 66, 208, 14);
		panel_Horses.add(lblPleaseSelectA_3);
		
		
		
		JComboBox comboBox_date = new JComboBox();
		comboBox_date.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_date.getSelectedItem().toString() == ""){
					try {
						initialLoadRecords();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				else{
					String[] ss = comboBox_date.getSelectedItem().toString().split(" , "); 
					String s1 = ss[0];
					String s2 = ss[1];
					try {
						loadRecords_a_date(s1, s2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		comboBox_date.insertItemAt("", 0);
		try {
		   	 String sql_stmt = "SELECT [meeting_date],[race_time] FROM [dbo].[RACE];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString()+" , "+ Combo.getValueAt(i, 1).toString());
		   		comboBox_date.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_date.setBounds(228, 63, 239, 20);
		panel_Horses.add(comboBox_date);

		
		
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setBounds(68, 36, 46, 14);
		panel_Horses.add(lblOr);
		
		JPanel panel_Trainers = new JPanel();
		tabbedPane.addTab("TRAINERS", null, panel_Trainers, null);
		panel_Trainers.setLayout(null);
		
		JLabel lblPleaseSelectA_1 = new JLabel("Please select a trainer:");
		lblPleaseSelectA_1.setBounds(10, 11, 171, 14);
		panel_Trainers.add(lblPleaseSelectA_1);
		
		
		
		
		
		
		
		JComboBox comboBox_trainers = new JComboBox();
		comboBox_trainers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					loadRecords_b(comboBox_trainers.getSelectedItem().toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		comboBox_trainers.insertItemAt("", 0);
		try {
		   	 String sql_stmt = "SELECT * FROM [dbo].[TRAINER];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBox_trainers.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_trainers.setBounds(227, 8, 240, 20);
		panel_Trainers.add(comboBox_trainers);
		
		
		
		
		
		
		
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 114, 457, 314);
		panel_Trainers.add(scrollPane_3);
		
		tableTrainers = new JTable();
		scrollPane_3.setViewportView(tableTrainers);
		
		JLabel lblOr_1 = new JLabel("OR");
		lblOr_1.setBounds(55, 36, 46, 14);
		panel_Trainers.add(lblOr_1);
		
		JLabel lblPleaseSelectA_4 = new JLabel("Please select a meeting:");
		lblPleaseSelectA_4.setBounds(10, 61, 171, 14);
		panel_Trainers.add(lblPleaseSelectA_4);
		
		
		
		
		JComboBox comboBox_trainer_date = new JComboBox();
		comboBox_trainer_date.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_trainer_date.getSelectedItem().toString() == ""){
					try {
						initialLoadRecords();
					} catch (SQLException e) {
						e.printStackTrace();
					}	
				}
				else{
					String[] ss = comboBox_trainer_date.getSelectedItem().toString().split(" , "); 
					String s1 = ss[0];
					String s2 = ss[1];
					try {
						loadRecords_b_date(s1, s2);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		comboBox_trainer_date.insertItemAt("", 0);
		try {
		   	 String sql_stmt = "SELECT [meeting_date],[race_time] FROM [dbo].[RACE];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString()+" , "+ Combo.getValueAt(i, 1).toString());
		   		comboBox_trainer_date.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_trainer_date.setBounds(227, 58, 240, 20);
		panel_Trainers.add(comboBox_trainer_date);
		
		
		
		
		
		JPanel panel_Owners = new JPanel();
		tabbedPane.addTab("OWNERS", null, panel_Owners, null);
		panel_Owners.setLayout(null);
		
		JLabel lblPleaseSelectAn = new JLabel("Please select an owner:");
		lblPleaseSelectAn.setBounds(10, 11, 140, 14);
		panel_Owners.add(lblPleaseSelectAn);
		
		
		
		
		JComboBox comboBox_owners = new JComboBox();
		comboBox_owners.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					loadRecords_c(comboBox_owners.getSelectedItem().toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		comboBox_owners.insertItemAt("", 0);
		try {
		   	 String sql_stmt = "SELECT * FROM [dbo].[TRAINER];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBox_owners.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_owners.setBounds(225, 8, 242, 20);
		panel_Owners.add(comboBox_owners);
		
		
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 114, 457, 314);
		panel_Owners.add(scrollPane_2);
		
		tableOwners = new JTable();
		scrollPane_2.setViewportView(tableOwners);
		
		JLabel lblOr_2 = new JLabel("OR");
		lblOr_2.setBounds(60, 36, 46, 14);
		panel_Owners.add(lblOr_2);
		
		JLabel lblPleaseSelectA_5 = new JLabel("Please select a meeting:");
		lblPleaseSelectA_5.setBounds(10, 61, 194, 14);
		panel_Owners.add(lblPleaseSelectA_5);
		
		JComboBox comboBox_owner_date = new JComboBox();
		comboBox_owner_date.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_owner_date.getSelectedItem().toString() == ""){
					try {
						initialLoadRecords();
					} catch (SQLException e) {
						e.printStackTrace();
					}	
				}
				else{
					String[] ss = comboBox_owner_date.getSelectedItem().toString().split(" , "); 
					String s1 = ss[0];
					String s2 = ss[1];
					try {
						loadRecords_c_date(s1, s2);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		comboBox_owner_date.insertItemAt("", 0);
		try {
		   	 String sql_stmt = "SELECT [meeting_date],[race_time] FROM [dbo].[RACE];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString()+" , "+ Combo.getValueAt(i, 1).toString());
		   		comboBox_owner_date.addItem(s);
		   	 }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		comboBox_owner_date.setBounds(225, 58, 242, 20);
		panel_Owners.add(comboBox_owner_date);
		
		JPanel panel_OwnerFamilies = new JPanel();
		tabbedPane.addTab("OWNER FAMILIES", null, panel_OwnerFamilies, null);
		panel_OwnerFamilies.setLayout(null);
		
		JLabel lblPleaseSelectA_2 = new JLabel("Please select a family:");
		lblPleaseSelectA_2.setBounds(10, 11, 145, 14);
		panel_OwnerFamilies.add(lblPleaseSelectA_2);
		
		
		
		
		
		JComboBox comboBox_families = new JComboBox();
		comboBox_families.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					loadRecords_d(comboBox_families.getSelectedItem().toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		comboBox_families.insertItemAt("", 0);
		try {
		   	 String sql_stmt = "SELECT * FROM [dbo].[FAMILY];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBox_families.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		comboBox_families.setBounds(227, 8, 240, 20);
		panel_OwnerFamilies.add(comboBox_families);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 114, 457, 314);
		panel_OwnerFamilies.add(scrollPane_1);
		
		tableFamilies = new JTable();
		scrollPane_1.setViewportView(tableFamilies);
		
		JLabel lblOr_3 = new JLabel("OR");
		lblOr_3.setBounds(55, 36, 46, 14);
		panel_OwnerFamilies.add(lblOr_3);
		
		JLabel lblPleaseSelectA_6 = new JLabel("Please select a meeting:");
		lblPleaseSelectA_6.setBounds(10, 68, 171, 14);
		panel_OwnerFamilies.add(lblPleaseSelectA_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem().toString() == ""){
					try {
						initialLoadRecords();
					} catch (SQLException e) {
						e.printStackTrace();
					}	
				}
				else{
					String[] ss = comboBox.getSelectedItem().toString().split(" , "); 
					String s1 = ss[0];
					String s2 = ss[1];
					try {
						loadRecords_d_date(s1, s2);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		comboBox.insertItemAt("", 0);
		try {
		   	 String sql_stmt = "SELECT [meeting_date],[race_time] FROM [dbo].[RACE];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString()+" , "+ Combo.getValueAt(i, 1).toString());
		   		comboBox.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox.setBounds(227, 65, 240, 20);
		panel_OwnerFamilies.add(comboBox);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("BACK");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						ViewMainQView mv = new ViewMainQView();
						mv.frmQueries.setVisible(true);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		try {
			initialLoadRecords();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
