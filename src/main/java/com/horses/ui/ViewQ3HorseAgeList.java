package com.horses.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewQ3HorseAgeList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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

	/**
	 * Create the dialog.
	 */
	public ViewQ3HorseAgeList() {
		setTitle("Horse List Based On Age");
		setBounds(100, 100, 453, 449);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 126, 437, 33);
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
			table = new JTable();
			table.setBounds(10, 170, 414, 230);
			contentPanel.add(table);
		}
		{
			JLabel lblPleaseEnterA = new JLabel("Please enter a specific age:");
			lblPleaseEnterA.setBounds(10, 11, 204, 14);
			contentPanel.add(lblPleaseEnterA);
		}
		{
			JLabel lblOrEnterA = new JLabel("OR Enter a specific bound:");
			lblOrEnterA.setBounds(10, 76, 142, 14);
			contentPanel.add(lblOrEnterA);
		}
		{
			JLabel lblMinimum = new JLabel("MINIMUM:");
			lblMinimum.setBounds(148, 76, 66, 14);
			contentPanel.add(lblMinimum);
		}
		{
			JLabel lblMaximum = new JLabel("MAXIMUM:");
			lblMaximum.setBounds(148, 101, 75, 14);
			contentPanel.add(lblMaximum);
		}
		{
			textField = new JTextField();
			textField.setBounds(210, 73, 86, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(210, 98, 86, 20);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 29, 437, 33);
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton button = new JButton("SEARCH");
		button.setActionCommand("OK");
		panel.add(button);
		
		textField_2 = new JTextField();
		textField_2.setBounds(148, 8, 86, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
	}
}
