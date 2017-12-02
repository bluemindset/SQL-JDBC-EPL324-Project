package com.horses.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewQ3HorseAgeList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewQ3HorseAgeList dialog = new ViewQ3HorseAgeList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 
    private void loadRecords(String min, String max) throws SQLException  {
    	boolean err = false;
    	int minimum = 0;
    	int maximum = 0;
        try { 
        	minimum =  Integer.parseInt(min); 
        } catch(NumberFormatException e) { 
       	 	err=true;
        } catch(NullPointerException e) {
       	 	err=true;
        }
        
        try { 
        	maximum = Integer.parseInt(max); 
        } catch(NumberFormatException e) { 
       	 	err=true;
        } catch(NullPointerException e) {
        	err=true;	
        }
            
    	if(err==true && minimum==0){
    		JOptionPane.showMessageDialog(contentPanel, "Error! Incorrect input!",
       			 "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	else if(maximum < minimum && maximum!=0){
   		 	JOptionPane.showMessageDialog(contentPanel, "Error! The minimum number must be smaller than the maximum!", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	else if(minimum!=0 && maximum==0){
    		System.out.println(min +" , "+ 500);
            String cstmtString = "{call countHorsesByAgeProc(?,?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, minimum, 500);
            table.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	}
    	else{
    		System.out.println(min +" , "+ max);
            String cstmtString = "{call countHorsesByAgeProc(?,?)}";
            ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, minimum, maximum);
            table.setModel(tableModel);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	}
    	
    }
	
	/**
	 * Create the dialog.
	 */
	public ViewQ3HorseAgeList() {
		setTitle("Horse List Based On Age");
		setBounds(100, 100, 453, 401);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 76, 437, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("SEARCH");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							loadRecords(textField.getText(), textField_1.getText());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
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
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 120, 414, 230);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JLabel lblOrEnterA = new JLabel("Enter a specific bound:");
			lblOrEnterA.setBounds(10, 11, 163, 14);
			contentPanel.add(lblOrEnterA);
		}
		{
			JLabel lblMinimum = new JLabel("MINIMUM:");
			lblMinimum.setBounds(143, 11, 66, 14);
			contentPanel.add(lblMinimum);
		}
		{
			JLabel lblMaximum = new JLabel("MAXIMUM:");
			lblMaximum.setBounds(143, 51, 75, 14);
			contentPanel.add(lblMaximum);
		}
		
			textField = new JTextField();
			textField.setBounds(219, 8, 86, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		
			textField_1 = new JTextField();
			textField_1.setBounds(219, 48, 86, 20);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
	}
}
