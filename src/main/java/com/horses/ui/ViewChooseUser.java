package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewChooseUser {

	public JFrame frmChooseAUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewChooseUser window = new ViewChooseUser();
					window.frmChooseAUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewChooseUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChooseAUser = new JFrame();
		frmChooseAUser.setTitle("CHOOSE A USER");
		frmChooseAUser.setBounds(100, 100, 309, 196);
		frmChooseAUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChooseAUser.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("R1 - DATA USER");
		comboBox.addItem("SA - SYSTEM ADMINISTRATOR");
		//for(int i=0; i<model.getUserTypes().size(); i++)
		//	comboBox.addItem(model.getUserTypes().get(i));
		//comboBox.setSelectedItem("HI");
		//comboBox.setSelectedItem(model.getName());
		
		
		comboBox.setBounds(20, 54, 253, 32);
		
		frmChooseAUser.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Please choose what kind of user you are:");
		lblNewLabel.setBounds(10, 11, 277, 32);
		frmChooseAUser.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex() == 0){
					frmChooseAUser.dispose();
					ViewR1UserLogin window = new ViewR1UserLogin();
					window.frmSystemUserSign.setVisible(true);
				}
				else if(comboBox.getSelectedIndex() == 1){
					frmChooseAUser.dispose();
					ViewSAUserLogin window = new ViewSAUserLogin();
					window.frmSystemAdminLogin.setVisible(true);
//TODO
				}
				//else{
				//	JOptionPane.showMessageDialog(frmChooseAUser,
				//		    "Error! Please select an option!");
				//}
				
			}
		});
		btnNewButton.setBounds(26, 115, 89, 23);
		frmChooseAUser.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(161, 115, 89, 23);
		frmChooseAUser.getContentPane().add(btnCancel);
	}
	
	public void run(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewChooseUser window = new ViewChooseUser();
					window.frmChooseAUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
