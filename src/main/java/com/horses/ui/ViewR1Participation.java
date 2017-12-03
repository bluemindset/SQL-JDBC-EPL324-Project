package com.horses.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewR1Participation {

	private JFrame frmParticipation;
	private JTable table;
	private JTextField textFieldHorseWeight;
	private JTextField textFieldHorseAge;
	private JTextField textFieldStartPos;
	private JTextField textFieldEndPos;
	private JComboBox comboBox_trainer;
	private JComboBox comboBox_meeting;
	private JComboBox comboBox_jockey;
	private JComboBox comboBox_horse;
	private JSpinner spinner ;
	
	boolean addRecord = false;
	
private void clearInputBoxesHorses() {

	comboBox_trainer.setSelectedItem("");
	comboBox_meeting.setSelectedItem("");
	comboBox_jockey.setSelectedItem("");
	comboBox_horse.setSelectedItem("");
	textFieldHorseWeight.setText("");	
	textFieldHorseAge.setText("");	
	textFieldStartPos.setText("");	
	textFieldEndPos.setText("");	
	}
	
public JFrame getFrmParticipation() {
	return frmParticipation;
}

public void setFrmParticipation(JFrame frmParticipation) {
	this.frmParticipation = frmParticipation;
}

private void addNew() throws SQLException {
	
	String trainer ="";
	String horse ="";
	String meeting ="";
	String jockey ="";

	try {
		trainer = comboBox_trainer.getSelectedItem().toString();
	} catch ( NullPointerException e) {
		trainer ="NULL";
	}
	try {
		horse = comboBox_horse.getSelectedItem().toString();
	} catch ( NullPointerException e) {
		horse ="NULL";
	}
	try {
		jockey =comboBox_jockey.getSelectedItem().toString();
	} catch ( NullPointerException e) {
		jockey ="NULL";
	}
	try {
		meeting = comboBox_meeting.getSelectedItem().toString();
	} catch ( NullPointerException e) {
		meeting = "NULL";
	}
	Date t2 = (Date) spinner.getModel().getValue();
	System.out.println(t2.getHours()+":"+t2.getMinutes()+":"+t2.getSeconds());
	String finTime = t2.getHours()+":"+t2.getMinutes()+":"+t2.getSeconds();
	
	String sql_stmt = "INSERT INTO [dbo].[PARTICIPATION] ([race_time],[meeting_date],[horse_id],[jockey_id],[trainer_id],[cur_horse_weight],[cur_horse_age],[star_pos],[end_pos])";
    sql_stmt += " VALUES ('" + finTime+  "','" +
    		comboBox_meeting.getSelectedItem().toString()   + "','" +
    						comboBox_horse.getSelectedItem().toString() + "','"+	
    						comboBox_jockey.getSelectedItem().toString() + "','"+						
    						comboBox_trainer.getSelectedItem().toString()  + "','" + 
    						textFieldHorseWeight.getText() + "','" + 
    						textFieldHorseAge.getText() + "','" + 
    						textFieldStartPos.getText() + "','" + 
    						textFieldEndPos.getText() +	"')";

		CurrentUserData.executeSetUserId();
       DBUtilities dbUtilities = new DBUtilities();
       dbUtilities.ExecuteSQLStatement(sql_stmt);
		loadRecords();
   }


private void updateRecord() throws SQLException {
	String trainer ="";
	String horse ="";
	String meeting ="";
	String jockey ="";

	try {
		trainer = comboBox_trainer.getSelectedItem().toString();
	} catch ( NullPointerException e) {
		trainer ="NULL";
	}
	try {
		horse = comboBox_horse.getSelectedItem().toString();
	} catch ( NullPointerException e) {
		horse ="NULL";
	}
	try {
		jockey =comboBox_jockey.getSelectedItem().toString();
	} catch ( NullPointerException e) {
		jockey ="NULL";
	}
	try {
		meeting = comboBox_meeting.getSelectedItem().toString();
	} catch ( NullPointerException e) {
		meeting = "NULL";
	}
	Date t2 = (Date) spinner.getModel().getValue();
	System.out.println(t2.getHours()+":"+t2.getMinutes()+":"+t2.getSeconds());
	String finTime = t2.getHours()+":"+t2.getMinutes()+":"+t2.getSeconds();
	
    
    String sql_stmt = "UPDATE [dbo].[PARTICIPATION] SET [horse_id] = '" + comboBox_horse.getSelectedItem().toString() + "'";
    sql_stmt += ",[jockey_id] = '" + comboBox_jockey.getSelectedItem().toString() + "'";
    sql_stmt += ",[trainer_id] = '" + comboBox_trainer.getSelectedItem().toString() + "'";
    sql_stmt += ",[cur_horse_weight] = '" + textFieldHorseWeight.getText() + "'";
    sql_stmt += ",[cur_horse_age] = '" + textFieldHorseAge.getText() + "'";
    sql_stmt += ",[star_pos] = '" + textFieldStartPos.getText() + "'";
    sql_stmt += ",[end_pos] = '" + textFieldEndPos.getText() + "'";
    
    sql_stmt += " WHERE race_time = '" + finTime + "' AND meeting_date = '"+comboBox_meeting.getSelectedItem().toString()+"'";


    DBUtilities dbUtilities = new DBUtilities();
    dbUtilities.ExecuteSQLStatement(sql_stmt);
    
    loadRecords();
}

private void loadRecords() throws SQLException  {
	
    String sql_stmt = "SELECT * FROM [dbo].[PARTICIPATION];";

    ResultSetTableModel tableModel = new ResultSetTableModel(sql_stmt);
    table.setModel(tableModel);
 
    
    table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
        try {
            if (table.getSelectedRow() >= 0) {
            	
                Object time = table.getValueAt(table.getSelectedRow(), 0);
                Object meetingdate = table.getValueAt(table.getSelectedRow(), 1);
                Object horse_id = table.getValueAt(table.getSelectedRow(), 2);
                Object jockey_id = table.getValueAt(table.getSelectedRow(), 3);
                Object trainer_id = table.getValueAt(table.getSelectedRow(), 4);
                Object horse_weight = table.getValueAt(table.getSelectedRow(), 5);   
                Object horse_age = table.getValueAt(table.getSelectedRow(), 6); 
                Object start_pos = table.getValueAt(table.getSelectedRow(), 7);
                Object end_pos = table.getValueAt(table.getSelectedRow(), 8);
                Object winnings = table.getValueAt(table.getSelectedRow(), 9);
                
              
                comboBox_meeting.setSelectedItem(meetingdate.toString());
                comboBox_horse.setSelectedItem(horse_id.toString());
                comboBox_jockey.setSelectedItem(jockey_id.toString());
                comboBox_trainer.setSelectedItem(trainer_id.toString());
                
                textFieldHorseWeight.setText(horse_weight.toString());
                textFieldHorseAge.setText(horse_age.toString());
                textFieldStartPos.setText(start_pos.toString());
                textFieldEndPos.setText(end_pos.toString());
                
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                //SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
                Date date = format1.parse(meetingdate.toString());
                String finDate = format1.format(date).toString();
                
                SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
                Date time2 = formatTime.parse(time.toString());

            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    });
    
    
    
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
					ViewR1Participation window = new ViewR1Participation();
					window.getFrmParticipation().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1Participation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmParticipation(new JFrame());
		getFrmParticipation().setTitle("PARTICIPATION");
		getFrmParticipation().setBounds(100, 100, 541, 688);
		getFrmParticipation().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmParticipation().getContentPane().setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 509, 229);
		getFrmParticipation().getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Participation Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 251, 509, 348);
		getFrmParticipation().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRaceTime = new JLabel("Race Time:");
		lblRaceTime.setBounds(10, 44, 89, 14);
		panel.add(lblRaceTime);
		
		JLabel lblMeetingDate = new JLabel("Meeting Date:");
		lblMeetingDate.setBounds(10, 88, 108, 14);
		panel.add(lblMeetingDate);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
				addRecord = true;
				try {
					addNew();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clearInputBoxesHorses();
				//textFieldId.requestFocus();
				
				
			}
		});
		btnAddNew.setBounds(10, 314, 89, 23);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					updateRecord();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(125, 314, 89, 23);
		panel.add(btnUpdate);
		
		JLabel lblHorseId = new JLabel("Horse ID:");
		lblHorseId.setBounds(10, 140, 89, 14);
		panel.add(lblHorseId);
		
		JLabel lblJockeyId = new JLabel("Jockey ID:");
		lblJockeyId.setBounds(10, 190, 89, 14);
		panel.add(lblJockeyId);
		
		JLabel lblTrainerId = new JLabel("Trainer ID:");
		lblTrainerId.setBounds(10, 241, 89, 14);
		panel.add(lblTrainerId);
		
		JLabel lblHorseWeight = new JLabel("Horse Weight:");
		lblHorseWeight.setBounds(276, 44, 86, 14);
		panel.add(lblHorseWeight);
		
		textFieldHorseWeight = new JTextField();
		textFieldHorseWeight.setEditable(false);
		textFieldHorseWeight.setBounds(387, 41, 86, 20);
		panel.add(textFieldHorseWeight);
		textFieldHorseWeight.setColumns(10);
		
		JLabel lblHorseAge = new JLabel("Horse Age:");
		lblHorseAge.setBounds(276, 88, 66, 14);
		panel.add(lblHorseAge);
		
		textFieldHorseAge = new JTextField();
		textFieldHorseAge.setEditable(false);
		textFieldHorseAge.setBounds(387, 85, 86, 20);
		panel.add(textFieldHorseAge);
		textFieldHorseAge.setColumns(10);
		
		JLabel lblStartPosition = new JLabel("Start Position:");
		lblStartPosition.setBounds(276, 140, 86, 14);
		panel.add(lblStartPosition);
		
		textFieldStartPos = new JTextField();
		textFieldStartPos.setEditable(false);
		textFieldStartPos.setBounds(387, 134, 86, 20);
		panel.add(textFieldStartPos);
		textFieldStartPos.setColumns(10);
		
		JLabel lblEndPosition = new JLabel("End Position:");
		lblEndPosition.setBounds(276, 190, 66, 14);
		panel.add(lblEndPosition);
		
		textFieldEndPos = new JTextField();
		textFieldEndPos.setEditable(false);
		textFieldEndPos.setBounds(387, 187, 86, 20);
		panel.add(textFieldEndPos);
		textFieldEndPos.setColumns(10);
		
		spinner = new JSpinner( new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
		spinner.setEditor(timeEditor);
		spinner.setValue(new Date());
		spinner.setBounds(109, 41, 86, 20);
		panel.add(spinner);
		
		 comboBox_meeting = new JComboBox();
		comboBox_meeting.insertItemAt("", 0);
		try {
		   	 String sql_stmt_mom = "SELECT * FROM [dbo].[MEETING];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt_mom);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBox_meeting.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_meeting.setBounds(109, 84, 120, 23);
		panel.add(comboBox_meeting);
		
		comboBox_horse = new JComboBox();
		comboBox_horse.insertItemAt("", 0);
		try {
		   	 String sql_stmt_mom = "SELECT * FROM [dbo].[HORSE];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt_mom);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBox_horse.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_horse.setBounds(109, 136, 120, 23);
		panel.add(comboBox_horse);
		
		 comboBox_jockey = new JComboBox();
		comboBox_jockey.insertItemAt("", 0);
		try {
		   	 String sql_stmt_mom = "SELECT * FROM [dbo].[JOCKEY];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt_mom);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBox_jockey.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_jockey.setBounds(109, 187, 120, 23);
		panel.add(comboBox_jockey);
		
		comboBox_trainer = new JComboBox();
		comboBox_trainer.insertItemAt("", 0);
		try {
		   	 String sql_stmt_mom = "SELECT * FROM [dbo].[TRAINER];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt_mom);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBox_trainer.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_trainer.setBounds(109, 238, 120, 23);
		panel.add(comboBox_trainer);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmParticipation().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnBack.setBounds(426, 610, 89, 29);
		getFrmParticipation().getContentPane().add(btnBack);
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
