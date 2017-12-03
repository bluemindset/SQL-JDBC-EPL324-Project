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
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;

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
        table.setModel(tableModel);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);      
        
        //************************ TIMESTAMP*************************
        table.getColumnModel().getColumn(2).setCellRenderer(new TimeStampCellRenderer());
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
            table.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        
            
          //************************ TIMESTAMP*************************
            table.getColumnModel().getColumn(2).setCellRenderer(new TimeStampCellRenderer());
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
        	String cstmtString = "{call Query9_b_oneHorse(?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, year);
            table.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);     
            
          //************************ TIMESTAMP*************************
            table.getColumnModel().getColumn(2).setCellRenderer(new TimeStampCellRenderer());
        }
    }
	
	
	private void loadRecords_c(String date, String time) throws SQLException  {

        if(date == null || time == null){
    		initialLoadRecords();
    	}
        else{
        	String cstmtString = "{call Query9_c_oneHorse(?,?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, date, time);
            table.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);     
            
          //************************ TIMESTAMP*************************
            table.getColumnModel().getColumn(2).setCellRenderer(new TimeStampCellRenderer());
        }
    }
	
	
	
	
	/**
	 * Create the dialog.
	 */
	public ViewQ9ProgressReports() {
		setTitle("Progress Reports");
		setBounds(100, 100, 519, 602);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 482, 509);
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
		scrollPane.setBounds(10, 156, 457, 314);
		panel_Horses.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblPleaseSelectA_3 = new JLabel("Please select a meeting date:");
		lblPleaseSelectA_3.setBounds(10, 66, 208, 14);
		panel_Horses.add(lblPleaseSelectA_3);
		
		JComboBox comboBox_date = new JComboBox();
		comboBox_date.insertItemAt("", 0);
		try {
		   	 String sql_stmt = "SELECT * FROM [dbo].[MEETING];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBox_date.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_date.setBounds(228, 63, 239, 20);
		panel_Horses.add(comboBox_date);
		
		JLabel lblPleaseSelectA_4 = new JLabel("Please select a race time:");
		lblPleaseSelectA_4.setBounds(10, 91, 196, 14);
		panel_Horses.add(lblPleaseSelectA_4);
		
		JComboBox comboBox_time = new JComboBox();
		comboBox_time.insertItemAt("", 0);
		try {
		   	 String sql_stmt = "SELECT * FROM [dbo].[RACE];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBox_time.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_time.setBounds(228, 88, 239, 20);
		panel_Horses.add(comboBox_time);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setBounds(68, 36, 46, 14);
		panel_Horses.add(lblOr);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					loadRecords_c(comboBox_date.getSelectedItem().toString(), comboBox_time.getSelectedItem().toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		btnOk.setBounds(227, 122, 89, 23);
		panel_Horses.add(btnOk);
		
		JPanel panel_Trainers = new JPanel();
		tabbedPane.addTab("TRAINERS", null, panel_Trainers, null);
		panel_Trainers.setLayout(null);
		
		JLabel lblPleaseSelectA_1 = new JLabel("Please select a trainer:");
		lblPleaseSelectA_1.setBounds(10, 11, 171, 14);
		panel_Trainers.add(lblPleaseSelectA_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(167, 8, 300, 20);
		panel_Trainers.add(comboBox_1);
		
		table_1 = new JTable();
		table_1.setBounds(10, 36, 457, 314);
		panel_Trainers.add(table_1);
		
		JPanel panel_Owners = new JPanel();
		tabbedPane.addTab("OWNERS", null, panel_Owners, null);
		panel_Owners.setLayout(null);
		
		JLabel lblPleaseSelectAn = new JLabel("Please select an owner:");
		lblPleaseSelectAn.setBounds(10, 11, 140, 14);
		panel_Owners.add(lblPleaseSelectAn);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(169, 8, 298, 20);
		panel_Owners.add(comboBox_2);
		
		table_2 = new JTable();
		table_2.setBounds(10, 36, 457, 314);
		panel_Owners.add(table_2);
		
		JPanel panel_OwnerFamilies = new JPanel();
		tabbedPane.addTab("OWNER FAMILIES", null, panel_OwnerFamilies, null);
		panel_OwnerFamilies.setLayout(null);
		
		JLabel lblPleaseSelectA_2 = new JLabel("Please select a family:");
		lblPleaseSelectA_2.setBounds(10, 11, 145, 14);
		panel_OwnerFamilies.add(lblPleaseSelectA_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(166, 8, 301, 20);
		panel_OwnerFamilies.add(comboBox_3);
		
		table_3 = new JTable();
		table_3.setBounds(10, 36, 457, 314);
		panel_OwnerFamilies.add(table_3);
		
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
