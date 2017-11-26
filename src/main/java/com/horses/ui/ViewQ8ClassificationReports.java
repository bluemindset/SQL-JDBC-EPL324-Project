package com.horses.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;

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

	/**
	 * Create the dialog.
	 */
	public ViewQ8ClassificationReports() {
		setTitle("Classification Reports");
		setBounds(100, 100, 529, 696);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPleaseEnterA = new JLabel("Please enter a specific year:");
			lblPleaseEnterA.setBounds(10, 11, 172, 14);
			contentPanel.add(lblPleaseEnterA);
		}
		{
			textField = new JTextField();
			textField.setBounds(189, 8, 86, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 29, 513, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("SEARCH");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("BACK");
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
			table = new JTable();
			table.setBounds(10, 98, 493, 113);
			contentPanel.add(table);
		}
		{
			JLabel lblRiders = new JLabel("b) RIDERS:");
			lblRiders.setBounds(10, 222, 86, 14);
			contentPanel.add(lblRiders);
		}
		{
			table_1 = new JTable();
			table_1.setBounds(10, 243, 493, 113);
			contentPanel.add(table_1);
		}
		{
			JLabel lblNewLabel = new JLabel("c) HORSE FATHERS:");
			lblNewLabel.setBounds(10, 367, 141, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			table_2 = new JTable();
			table_2.setBounds(10, 392, 493, 113);
			contentPanel.add(table_2);
		}
		{
			JLabel lblDHorseGrandfathers = new JLabel("d) HORSE GRANDFATHERS:");
			lblDHorseGrandfathers.setBounds(10, 516, 172, 14);
			contentPanel.add(lblDHorseGrandfathers);
		}
		{
			table_3 = new JTable();
			table_3.setBounds(10, 536, 493, 113);
			contentPanel.add(table_3);
		}
	}

}
