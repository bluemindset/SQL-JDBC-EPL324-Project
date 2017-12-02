package com.horses.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewQ4HorsesOfOwnerFamily extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewQ4HorsesOfOwnerFamily dialog = new ViewQ4HorsesOfOwnerFamily();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    private void loadRecords() throws SQLException  {
        String cstmtString = "{call selectHorsesGroupedByFamily}";
        ResultSetTableModel tableModel = new ResultSetTableModel(cstmtString);
        table.setModel(tableModel);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
	
	/**
	 * Create the dialog.
	 */
	public ViewQ4HorsesOfOwnerFamily() {
		setTitle("Horse List with horses of a specific Owner Famliy");
		setBounds(100, 100, 459, 426);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 423, 306);
		contentPanel.add(scrollPane);
		{
			table = new JTable();
			scrollPane.setViewportView(table);
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
			cancelButton.setBounds(365, 22, 68, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadRecords();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 	
			}
		});
		btnSearch.setBounds(266, 22, 89, 23);
		contentPanel.add(btnSearch);
	}
}
