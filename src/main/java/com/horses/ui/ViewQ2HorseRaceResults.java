package com.horses.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewQ2HorseRaceResults extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewQ2HorseRaceResults dialog = new ViewQ2HorseRaceResults();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    private void loadRecords(String stringDate) throws SQLException  {
    	
    	System.out.println(stringDate);
        String cstmtString = "{call selectRacesByMeetingDate(?)}";
        Date date1 = null;
        try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString, date1);
        
        table.setModel(tableModel);

        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        //************************ TIMESTAMP*************************
        table.getColumnModel().getColumn(1).setCellRenderer(new TimeStampCellRenderer());
        
    
    
    }

	
	
	/**
	 * Create the dialog.
	 */
	public ViewQ2HorseRaceResults() {
		setTitle("Horse Race Results From A Meeting");
		setBounds(100, 100, 523, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPleaseEnter = new JLabel("Please enter a Meeting Date:");
		lblPleaseEnter.setBounds(29, 14, 169, 14);
		contentPanel.add(lblPleaseEnter);
		
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			JFormattedTextField formattedTextField = new JFormattedTextField(format);
			formattedTextField.setBounds(208, 8, 133, 26);
			contentPanel.add(formattedTextField);
		
		{
			JLabel lblFormatddmmyyyy = new JLabel("format: (DD/MM/YYYY)");
			lblFormatddmmyyyy.setBounds(351, 14, 146, 14);
			contentPanel.add(lblFormatddmmyyyy);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 86, 487, 235);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(-10, 39, 507, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton btnSearch = new JButton("SEARCH");
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							if(formattedTextField.getText().isEmpty()){
								System.out.println("ERROR! INVALID DATE!");
							}
							else{
								loadRecords(formattedTextField.getText());
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				buttonPane.add(btnSearch);
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
	}
}
