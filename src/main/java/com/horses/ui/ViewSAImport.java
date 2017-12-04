package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ViewSAImport {

	private JFrame frame;
	private JFileChooser breederChooser;

	private String breederFileName;
	private JButton btnChoosebreeder;
	private JButton btnFamilies;
	private JButton btnHorses;
	private JButton btnHorsecolors;
	private JButton btnJockey;
	private JButton btnHorseSex;
	private JLabel lblHorsColors;
	private JLabel lblHorses;
	private JLabel lblBreeder;
	private JButton btnBreedermeetings;
	private JButton btnOwner;
	private JButton button_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSAImport window = new ViewSAImport();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewSAImport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 595, 825);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnImport = new JButton("IMPORT");
		btnImport.setBounds(217, 788, 117, 25);
		frame.getContentPane().add(btnImport);
		

		
		btnChoosebreeder = new JButton("BREEDER");
		btnChoosebreeder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 	breederChooser = new JFileChooser();
				int responce = breederChooser.showOpenDialog(frame);
				if(responce == JFileChooser.APPROVE_OPTION) {
					breederFileName = breederChooser.getSelectedFile().toString();
					lblBreeder.setText();
				} else {
					
				}
				System.out.println("The file that you chose is " + breederFileName);

			}
		});
		btnChoosebreeder.setBounds(26, 22, 211, 25);
		frame.getContentPane().add(btnChoosebreeder);
		
		btnFamilies = new JButton("FAMILIES");
		btnFamilies.setBounds(26, 81, 211, 25);
		frame.getContentPane().add(btnFamilies);
		
		btnHorses = new JButton("HORSES");
		btnHorses.setBounds(26, 130, 211, 25);
		frame.getContentPane().add(btnHorses);
		
		btnHorsecolors = new JButton("HORSE COLORS");
		btnHorsecolors.setBounds(26, 179, 211, 25);
		frame.getContentPane().add(btnHorsecolors);
		
		btnJockey = new JButton("JOCKEY");
		btnJockey.setBounds(26, 291, 211, 25);
		frame.getContentPane().add(btnJockey);
		
		btnHorseSex = new JButton("HORSE SEX");
		btnHorseSex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHorseSex.setBounds(26, 231, 211, 25);
		frame.getContentPane().add(btnHorseSex);
		
		lblBreeder = new JLabel("New label");
		lblBreeder.setBounds(284, 32, 170, 15);
		frame.getContentPane().add(lblBreeder);
		
		lblHorses = new JLabel("New label");
		lblHorses.setBounds(284, 135, 170, 15);
		frame.getContentPane().add(lblHorses);
		
		lblHorsColors = new JLabel("New label");
		lblHorsColors.setBounds(284, 184, 170, 15);
		frame.getContentPane().add(lblHorsColors);
		
		JLabel lblFamilies = new JLabel("New label");
		lblFamilies.setBounds(284, 86, 170, 15);
		frame.getContentPane().add(lblFamilies);
		
		JLabel label_3 = new JLabel("New label");
		label_3.setBounds(284, 236, 170, 15);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("New label");
		label_4.setBounds(284, 296, 170, 15);
		frame.getContentPane().add(label_4);
		
		btnBreedermeetings = new JButton("MEETINGS");
		btnBreedermeetings.setBounds(26, 352, 211, 25);
		frame.getContentPane().add(btnBreedermeetings);
		
		btnOwner = new JButton("OWNER");
		btnOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOwner.setBounds(26, 413, 211, 25);
		frame.getContentPane().add(btnOwner);
		
		button_2 = new JButton("BREEDER");
		button_2.setBounds(26, 460, 211, 25);
		frame.getContentPane().add(button_2);
	}
}
