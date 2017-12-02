package com.horses.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewQ8ClassificationReports extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewQ8ClassificationReports dialog = new ViewQ8ClassificationReports();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadRecords(String year) throws SQLException  {
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
    		JOptionPane.showMessageDialog(contentPanel, "Error! Incorrect input!",
       			 "Error", JOptionPane.ERROR_MESSAGE);
    	}
        else{
        	String cstmtString = "{call query8_a(?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, year);
            table.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            
            String cstmtString1 = "{call query8_b(?)}";
            ResultSetTableModel tableModel1 = new ResultSetTableModel(cstmtString1, year);
            table_1.setModel(tableModel1);
            DefaultTableCellRenderer rightRenderer1 = new DefaultTableCellRenderer();
            rightRenderer1.setHorizontalAlignment(SwingConstants.LEFT);
            table_1.getColumnModel().getColumn(0).setCellRenderer(rightRenderer1);
            table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
    }
	
	/**
	 * Create the dialog.
	 */
	public ViewQ8ClassificationReports() {
		setTitle("Classification Reports");
		setBounds(100, 100, 700, 696);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPleaseEnterA = new JLabel("Please enter a specific year:");
			lblPleaseEnterA.setBounds(10, 11, 172, 14);
			contentPanel.add(lblPleaseEnterA);
		}
		
			textField = new JTextField();
			textField.setBounds(189, 8, 86, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 29, 513, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("SEARCH");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							loadRecords(textField.getText());
						} catch (SQLException e1) {
							e1.printStackTrace();
						} 
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("BACK");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						ViewMainQView window = new ViewMainQView();
						window.frmQueries.setVisible(true);						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JLabel lblHorses = new JLabel("a) HORSES:");
			lblHorses.setBounds(10, 73, 96, 14);
			contentPanel.add(lblHorses);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 98, 664, 113);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JLabel lblRiders = new JLabel("b) RIDERS:");
			lblRiders.setBounds(10, 222, 86, 14);
			contentPanel.add(lblRiders);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 243, 664, 113);
			contentPanel.add(scrollPane);
			{
				table_1 = new JTable();
				scrollPane.setViewportView(table_1);
			}
		}
		{
			JLabel lblNewLabel = new JLabel("c) HORSE FATHERS:");
			lblNewLabel.setBounds(10, 367, 141, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 392, 664, 113);
			contentPanel.add(scrollPane);
			{
				table_2 = new JTable();
				scrollPane.setViewportView(table_2);
			}
		}
		{
			JLabel lblDHorseGrandfathers = new JLabel("d) HORSE GRANDFATHERS:");
			lblDHorseGrandfathers.setBounds(10, 516, 172, 14);
			contentPanel.add(lblDHorseGrandfathers);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 536, 664, 113);
			contentPanel.add(scrollPane);
			{
				table_3 = new JTable();
				scrollPane.setViewportView(table_3);
			}
		}
	}

}
