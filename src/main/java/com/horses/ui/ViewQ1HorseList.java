package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewQ1HorseList {

	public JFrame frmHorseNameList;
	private JTextField textField;
	private JTable tableHorses;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewQ1HorseList window = new ViewQ1HorseList();
					window.frmHorseNameList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	  
    private void loadRecords(String stringHorse) throws SQLException  {
    	
    	String SearchhorseName = stringHorse;
    	System.out.println(SearchhorseName);
    	
        String sql_stmt = "SELECT * FROM [dbo].[HORSE];";
        
        ResultSetTableModel tableModel = new ResultSetTableModel(sql_stmt);
        tableHorses.setModel(tableModel);
        tableHorses.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            try {
                if (tableHorses.getSelectedRow() >= 0) {
                	
                    Object id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 0);
                    Object name = tableHorses.getValueAt(tableHorses.getSelectedRow(), 1);
                    Object compressed_name = tableHorses.getValueAt(tableHorses.getSelectedRow(), 2);
                    Object cur_weight = tableHorses.getValueAt(tableHorses.getSelectedRow(), 3);
                    Object date_of_birth = tableHorses.getValueAt(tableHorses.getSelectedRow(), 4);
                    Object age = tableHorses.getValueAt(tableHorses.getSelectedRow(), 5);                    
                    Object sex = tableHorses.getValueAt(tableHorses.getSelectedRow(), 6);
                 
                    Object is_purebred = tableHorses.getValueAt(tableHorses.getSelectedRow(), 7);
                    Object record = tableHorses.getValueAt(tableHorses.getSelectedRow(), 8);
                    Object origin_country = tableHorses.getValueAt(tableHorses.getSelectedRow(), 9);
                    Object mama_id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 10);
                    Object dad_id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 11); 
                    Object jockey_id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 12);
                    Object breeder_id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 13);
                    Object color_name = tableHorses.getValueAt(tableHorses.getSelectedRow(), 14);
                    Object trainer_id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 15);
                    Object owner_id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 16);
                    
                    
//                    
//                    textFieldID.setText(id.toString());
//					textFieldName.setText(name.toString());
//					textFieldWeight.setText(cur_weight.toString());
//					formattedTextField.setText(date_of_birth.toString());
//			    	textFieldAge.setText(age.toString());
//					comboBoxSex.setSelectedItem(sex.toString());
//					try {
//						checkBoxIsPurebred.setSelected(Boolean.parseBoolean(is_purebred.toString()));
//					} catch ( NullPointerException e) {
//						checkBoxIsPurebred.setSelected(false);
//					}
//					try {
//						comboBoxColor.setSelectedItem(color_name.toString());
//					} catch ( NullPointerException e) {
//						comboBoxColor.setSelectedItem(null);
//					}
//					try {
//						comboBoxTrainer.setSelectedItem(trainer_id.toString());
//					} catch ( NullPointerException e) {
//						comboBoxTrainer.setSelectedItem(null);
//					}
//					try {
//						comboBoxOwner.setSelectedItem(owner_id.toString());						
//					} catch ( NullPointerException e) {
//						comboBoxOwner.setSelectedItem(null);
//					}
//					try {
//						comboBoxBreeder.setSelectedItem(breeder_id.toString());
//					} catch ( NullPointerException e) {
//						comboBoxBreeder.setSelectedItem(null);
//					}
//					try {						
//						comboBoxDad.setSelectedItem(dad_id.toString());
//					} catch ( NullPointerException e) {
//						comboBoxDad.setSelectedItem(null);
//					}
//					try {
//						comboBoxMom.setSelectedItem(mama_id.toString());						
//					} catch ( NullPointerException e) {
//						comboBoxMom.setSelectedItem(null);
//					}
//					try {
//						textFieldOriginCountry.setText(origin_country.toString());
//					} catch ( NullPointerException e) {
//						textFieldOriginCountry.setText(null);
//					}
//					try {
//						textFieldRecord.setText(record.toString());
//					} catch (NullPointerException e) {
//						textFieldRecord.setText(null);	
//					} 
//
//
//			    	
			    	

                    
                }
            } catch (Exception ex) {
            	ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        });
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        tableHorses.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        tableHorses.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

	
	
	
	
	/**
	 * Create the application.
	 */
	public ViewQ1HorseList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHorseNameList = new JFrame();
		frmHorseNameList.setTitle("Horse Name List");
		frmHorseNameList.setBounds(100, 100, 442, 492);
		frmHorseNameList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHorseNameList.getContentPane().setLayout(null);
		
		JLabel lblPleaseEnterA = new JLabel("Please enter a horse name:");
		lblPleaseEnterA.setBounds(10, 11, 414, 22);
		frmHorseNameList.getContentPane().add(lblPleaseEnterA);
		
		textField = new JTextField();
		textField.setBounds(10, 40, 186, 22);
		frmHorseNameList.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("SEARCH");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					loadRecords(textField.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOk.setBounds(218, 40, 89, 23);
		frmHorseNameList.getContentPane().add(btnOk);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHorseNameList.dispose();
				ViewMainQView window = new ViewMainQView();
				window.frmQueries.setVisible(true);
			}
		});
		btnBack.setBounds(317, 40, 89, 23);
		frmHorseNameList.getContentPane().add(btnBack);
		
		tableHorses = new JTable();
		tableHorses.setBounds(10, 98, 406, 338);
		frmHorseNameList.getContentPane().add(tableHorses);
		

	}
}
