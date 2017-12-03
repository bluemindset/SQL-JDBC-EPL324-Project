package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewR1Race {

	private JFrame frmRace;
	private JTable table;
	private JTextField textFieldRaceTime;
	private JTextField textFieldPrize1;
	private JTextField textFieldPrize2;
	private JTextField textFieldPrize3;
	private JTextField textFieldTotalWinnings;
	private JComboBox comboBoxDistance;
	private JComboBox comboBoxRaceType;
	private JComboBox comboBoxFieldType;
	private JComboBox comboBoxMeeting;

	boolean addRecord = false;
	
	public JFrame getFrmRace() {
		return frmRace;
	}

	public void setFrmRace(JFrame frmRace) {
		this.frmRace = frmRace;
	}
	
    private void clearInputBoxesHorses() {
    	textFieldRaceTime.setText("");
    	textFieldPrize1.setText("");    	
    	textFieldPrize2.setText("");
    	textFieldPrize3.setText("");
    	textFieldTotalWinnings.setText("");
    	comboBoxDistance.setSelectedItem("");
    	comboBoxRaceType.setSelectedItem("");
    	comboBoxFieldType.setSelectedItem("");
    	comboBoxMeeting.setSelectedItem("");
    }
    
//    private void addNew() throws SQLException {
//		Date date1 = null;
//		try {
//			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(formattedTextField.getText());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//		String finDate = format1.format(date1).toString();
//
//		String trainer ="";
//		String owner ="";
//		String breeder ="";
//		String dad ="";
//		String mom ="";
//		String jockey ="";
//
//		try {
//			trainer = comboBoxTrainer.getSelectedItem().toString();
//		} catch ( NullPointerException e) {
//			trainer ="";
//		}
//		try {
//			owner = comboBoxOwner.getSelectedItem().toString();
//		} catch ( NullPointerException e) {
//			owner ="";
//		}
//		try {
//			breeder =comboBoxBreeder.getSelectedItem().toString();
//		} catch ( NullPointerException e) {
//			breeder ="";
//		}
//		try {
//			dad = comboBoxDad.getSelectedItem().toString();
//		} catch ( NullPointerException e) {
//			dad ="";
//		}
//		try {
//			mom =comboBoxMom.getSelectedItem().toString();
//		} catch ( NullPointerException e) {
//			mom ="";
//		}
//		try {
//			jockey =comboBoxJockey.getSelectedItem().toString();
//		} catch ( NullPointerException e) {
//			jockey ="";
//		}
//
//        String sql_stmt = "INSERT INTO [dbo].[HORSE] ([id],[name],[is_purebred],[sex],[color_name],[date_of_birth],[cur_weight],[trainer_id],[owner_id],[breeder_id],[dad_id],[mama_id],[jockey_id])";
//        sql_stmt += " VALUES ('" +  textFieldID.getText() + "','" +
//        							textFieldName.getText() + "','"+
//									checkBoxIsPurebred.isSelected() + "','" +
//									comboBoxSex.getSelectedItem().toString() + "','" +
//        							comboBoxColor.getSelectedItem().toString() + "','" +
//									finDate + "','" +
//        							textFieldWeight.getText() + "','" + 
//        							trainer + "','" +
//        							owner + "','" +
//        							breeder + "','" +
//        							dad + "','" +
//									mom + "','" +
//									jockey + "')";
//
//
//		CurrentUserData.executeSetUserId();
//        DBUtilities dbUtilities = new DBUtilities();
//        dbUtilities.ExecuteSQLStatement(sql_stmt);
//		loadRecords();
//    }
//
//    private void updateRecord() throws SQLException {
//    	
//        Date date1 = null;
//        try {
//			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(formattedTextField.getText());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//		String finDate = format1.format(date1).toString();
//
//        String trainer ="";
//        String owner ="";
//        String breeder ="";
//        String dad ="";
//        String mom ="";
//        String jockey ="";
//        
//		try {
//			trainer = comboBoxTrainer.getSelectedItem().toString();
//		} catch ( NullPointerException e) {
//			trainer ="";
//		}
//		try {
//			owner = comboBoxOwner.getSelectedItem().toString();					
//		} catch ( NullPointerException e) {
//			owner ="";
//		}
//		try {
//			breeder =comboBoxBreeder.getSelectedItem().toString();		
//		} catch ( NullPointerException e) {
//			breeder ="";
//		}
//		try {						
//			dad = comboBoxDad.getSelectedItem().toString();	
//		} catch ( NullPointerException e) {
//			dad ="";
//		}
//		try {
//			mom =comboBoxMom.getSelectedItem().toString();							
//		} catch ( NullPointerException e) {
//			mom ="";
//		}
//		try {
//			jockey =comboBoxJockey.getSelectedItem().toString();					
//		} catch ( NullPointerException e) {
//			jockey ="";
//		}
//        
//        String sql_stmt = "UPDATE [dbo].[HORSE] SET [name] = '" + textFieldName.getText() + "'";
//        sql_stmt += ",[cur_weight] = '" + textFieldWeight.getText() + "'";
//        sql_stmt += ",[date_of_birth] = CONVERT(DATE, '" + finDate + "')";
//        sql_stmt += ",[sex] = '" + comboBoxSex.getSelectedItem().toString() + "'";
//        sql_stmt += ",[is_purebred] = '" + checkBoxIsPurebred.isSelected() + "'";
//        sql_stmt += ",[record] = '" + textFieldRecord.getText() + "'";
//        sql_stmt += ",[origin_country] = '" + textFieldOriginCountry.getText() + "'";
//        sql_stmt += ",[mama_id] = '" + mom + "'";
//        sql_stmt += ",[dad_id] = '" + dad + "'";
//        sql_stmt += ",[jockey_id] = '" + jockey + "'";
//        sql_stmt += ",[breeder_id] = '" + breeder + "'";
//        sql_stmt += ",[color_name] = '" + comboBoxColor.getSelectedItem().toString() + "'";
//        sql_stmt += ",[trainer_id] = '" + trainer + "'";
//        sql_stmt += ",[owner_id] = '" + owner + "'";
//        sql_stmt += " WHERE id = '" + textFieldID.getText() + "'";
//
//		CurrentUserData.executeSetUserId();
//
//        DBUtilities dbUtilities = new DBUtilities();
//        dbUtilities.ExecuteSQLStatement(sql_stmt);
//        
//        loadRecords();
//    }


    private void loadRecords() throws SQLException  {
    	
        String sql_stmt = "SELECT * FROM [dbo].[RACE];";

        ResultSetTableModel tableModel = new ResultSetTableModel(sql_stmt);
        table.setModel(tableModel);
//        table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
//            try {
//                if (table.getSelectedRow() >= 0) {
//                	
//                    Object id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 0);
//                    Object name = tableHorses.getValueAt(tableHorses.getSelectedRow(), 1);
//                    Object compressed_name = tableHorses.getValueAt(tableHorses.getSelectedRow(), 2);
//                    Object cur_weight = tableHorses.getValueAt(tableHorses.getSelectedRow(), 3);
//                    Object date_of_birth = tableHorses.getValueAt(tableHorses.getSelectedRow(), 4);
//                    Object age = tableHorses.getValueAt(tableHorses.getSelectedRow(), 5);                    
//                    Object sex = tableHorses.getValueAt(tableHorses.getSelectedRow(), 6);
//                    Object is_purebred = tableHorses.getValueAt(tableHorses.getSelectedRow(), 7);
//                    Object record = tableHorses.getValueAt(tableHorses.getSelectedRow(), 8);
//                    Object origin_country = tableHorses.getValueAt(tableHorses.getSelectedRow(), 9);
//                    Object mama_id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 10);
//                    Object dad_id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 11); 
//                    Object jockey_id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 12);
//                    Object breeder_id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 13);
//                    Object color_name = tableHorses.getValueAt(tableHorses.getSelectedRow(), 14);
//                    Object trainer_id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 15);
//                    Object owner_id = tableHorses.getValueAt(tableHorses.getSelectedRow(), 16);
//                    
//                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//                    SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
//                    Date date = format1.parse(date_of_birth.toString());
//                    String finDate = format2.format(date).toString();
//                    
//                    
//                    textFieldID.setText(id.toString());
//					textFieldName.setText(name.toString());
//					textFieldWeight.setText(cur_weight.toString());
//					formattedTextField.setText(finDate);
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
//					try {
//						comboBoxJockey.setSelectedItem(jockey_id.toString());						
//					} catch ( NullPointerException e) {
//						comboBoxJockey.setSelectedItem(null);
//					}
//                }
//            } catch (Exception ex) {
//            	ex.printStackTrace();
//                System.out.println(ex.getMessage());
//            }
//        });
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1Race window = new ViewR1Race();
					window.getFrmRace().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Race() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmRace(new JFrame());
		getFrmRace().setTitle("RACE");
		getFrmRace().setBounds(100, 100, 503, 567);
		getFrmRace().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmRace().getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 454, 162);
		getFrmRace().getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Race Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 182, 454, 301);
		getFrmRace().getContentPane().add(panel);
		panel.setLayout(null);
		
		textFieldRaceTime = new JTextField();
		textFieldRaceTime.setBounds(120, 40, 86, 20);
		panel.add(textFieldRaceTime);
		textFieldRaceTime.setColumns(10);
		
		JLabel lblRaceTime = new JLabel("Race Time:");
		lblRaceTime.setBounds(10, 43, 73, 14);
		panel.add(lblRaceTime);
		
		JLabel lblNewLabel = new JLabel("Race Distance:");
		lblNewLabel.setBounds(10, 84, 86, 14);
		panel.add(lblNewLabel);
		
		comboBoxDistance = new JComboBox();
		comboBoxDistance.insertItemAt("", 0);
		try {
		   	 String sql_stmt_mom = "SELECT * FROM [dbo].[RACE_DISTANCE];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt_mom);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBoxDistance.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxDistance.setBounds(120, 81, 86, 20);
		panel.add(comboBoxDistance);
		
		JLabel lblPrize1 = new JLabel("Prize 1:");
		lblPrize1.setBounds(230, 43, 46, 14);
		panel.add(lblPrize1);
		
		textFieldPrize1 = new JTextField();
		textFieldPrize1.setBounds(334, 40, 86, 20);
		panel.add(textFieldPrize1);
		textFieldPrize1.setColumns(10);
		
		JLabel lblPrize2 = new JLabel("Prize 2:");
		lblPrize2.setBounds(230, 84, 46, 14);
		panel.add(lblPrize2);
		
		textFieldPrize2 = new JTextField();
		textFieldPrize2.setBounds(334, 81, 86, 20);
		panel.add(textFieldPrize2);
		textFieldPrize2.setColumns(10);
		
		JLabel lblPrize3 = new JLabel("Prize 3:");
		lblPrize3.setBounds(230, 128, 46, 14);
		panel.add(lblPrize3);
		
		textFieldPrize3 = new JTextField();
		textFieldPrize3.setBounds(334, 125, 86, 20);
		panel.add(textFieldPrize3);
		textFieldPrize3.setColumns(10);
		
		JLabel lblWinnings = new JLabel("Total Winnings:");
		lblWinnings.setBounds(230, 177, 94, 14);
		panel.add(lblWinnings);
		
		textFieldTotalWinnings = new JTextField();
		textFieldTotalWinnings.setEditable(false);
		textFieldTotalWinnings.setBounds(334, 174, 86, 20);
		panel.add(textFieldTotalWinnings);
		textFieldTotalWinnings.setColumns(10);
		
		JLabel lblRaceType = new JLabel("Race Type:");
		lblRaceType.setBounds(10, 128, 62, 14);
		panel.add(lblRaceType);
		
		comboBoxRaceType = new JComboBox();
		comboBoxRaceType.insertItemAt("", 0);
		try {
		   	 String sql_stmt_mom = "SELECT * FROM [dbo].[RACE_TYPE];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt_mom);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBoxRaceType.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxRaceType.setBounds(120, 125, 86, 20);
		panel.add(comboBoxRaceType);
		
		JLabel lblFieldType = new JLabel("Field Type:");
		lblFieldType.setBounds(10, 177, 62, 14);
		panel.add(lblFieldType);
		
		comboBoxFieldType = new JComboBox();
		comboBoxRaceType.insertItemAt("", 0);
		try {
		   	 String sql_stmt_mom = "SELECT * FROM [dbo].[FIELD_TYPE];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt_mom);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBoxFieldType.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxFieldType.setBounds(120, 174, 86, 20);
		panel.add(comboBoxFieldType);
		
		JLabel lblMeetingDate = new JLabel("Meeting Date:");
		lblMeetingDate.setBounds(10, 219, 86, 14);
		panel.add(lblMeetingDate);
		
		comboBoxMeeting = new JComboBox();
		comboBoxMeeting.setBounds(120, 216, 86, 20);
		panel.add(comboBoxMeeting);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/////////?????????????????????????????????
			}
		});
		btnAddNew.setBounds(10, 259, 89, 31);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??????????????????????????????????
			}
		});
		btnUpdate.setBounds(107, 259, 89, 31);
		panel.add(btnUpdate);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmRace().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
				
				
			}
		});
		btnBack.setBounds(375, 489, 89, 29);
		getFrmRace().getContentPane().add(btnBack);
		
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
