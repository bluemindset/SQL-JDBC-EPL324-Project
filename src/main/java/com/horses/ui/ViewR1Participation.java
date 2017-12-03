package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewR1Participation {

	private JFrame frmParticipation;
	private JTable table;
	private JTextField textFieldRaceTime;
	private JTextField textFieldMeetingDate;
	private JTextField textFieldHorseId;
	private JTextField textFieldJockeyID;
	private JTextField textFieldTrainerID;
	private JTextField textFieldHorseWeight;
	private JTextField textFieldHorseAge;
	private JTextField textFieldStartPos;
	private JTextField textFieldEndPos;
	
	boolean addRecord = false;
	
private void clearInputBoxesHorses() {
		
	textFieldRaceTime.setText("");
	textFieldMeetingDate.setText("");
	textFieldHorseId.setText("");
	textFieldJockeyID.setText("");
	textFieldTrainerID.setText("");	
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

private void loadRecords() throws SQLException  {
	
    String sql_stmt = "SELECT * FROM [dbo].[PARTICIPATION];";

    ResultSetTableModel tableModel = new ResultSetTableModel(sql_stmt);
    table.setModel(tableModel);
    //////////////////////////////
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
		getFrmParticipation().setBounds(100, 100, 548, 598);
		getFrmParticipation().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmParticipation().getContentPane().setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 496, 148);
		getFrmParticipation().getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Participation Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 172, 509, 348);
		getFrmParticipation().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRaceTime = new JLabel("Race Time:");
		lblRaceTime.setBounds(10, 44, 89, 14);
		panel.add(lblRaceTime);
		
		textFieldRaceTime = new JTextField();
		textFieldRaceTime.setBounds(128, 41, 86, 20);
		panel.add(textFieldRaceTime);
		textFieldRaceTime.setColumns(10);
		
		JLabel lblMeetingDate = new JLabel("Meeting Date:");
		lblMeetingDate.setBounds(10, 88, 108, 14);
		panel.add(lblMeetingDate);
		
		textFieldMeetingDate = new JTextField();
		textFieldMeetingDate.setBounds(128, 85, 86, 20);
		panel.add(textFieldMeetingDate);
		textFieldMeetingDate.setColumns(10);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.setBounds(10, 314, 89, 23);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBounds(125, 314, 89, 23);
		panel.add(btnUpdate);
		
		JLabel lblHorseId = new JLabel("Horse ID:");
		lblHorseId.setBounds(10, 140, 46, 14);
		panel.add(lblHorseId);
		
		textFieldHorseId = new JTextField();
		textFieldHorseId.setBounds(128, 137, 86, 20);
		panel.add(textFieldHorseId);
		textFieldHorseId.setColumns(10);
		
		JLabel lblJockeyId = new JLabel("Jockey ID:");
		lblJockeyId.setBounds(10, 190, 66, 14);
		panel.add(lblJockeyId);
		
		textFieldJockeyID = new JTextField();
		textFieldJockeyID.setBounds(128, 187, 86, 20);
		panel.add(textFieldJockeyID);
		textFieldJockeyID.setColumns(10);
		
		JLabel lblTrainerId = new JLabel("Trainer ID:");
		lblTrainerId.setBounds(10, 241, 65, 14);
		panel.add(lblTrainerId);
		
		textFieldTrainerID = new JTextField();
		textFieldTrainerID.setBounds(128, 238, 86, 20);
		panel.add(textFieldTrainerID);
		textFieldTrainerID.setColumns(10);
		
		JLabel lblHorseWeight = new JLabel("Horse Weight:");
		lblHorseWeight.setBounds(276, 44, 86, 14);
		panel.add(lblHorseWeight);
		
		textFieldHorseWeight = new JTextField();
		textFieldHorseWeight.setBounds(387, 41, 86, 20);
		panel.add(textFieldHorseWeight);
		textFieldHorseWeight.setColumns(10);
		
		JLabel lblHorseAge = new JLabel("Horse Age:");
		lblHorseAge.setBounds(276, 88, 66, 14);
		panel.add(lblHorseAge);
		
		textFieldHorseAge = new JTextField();
		textFieldHorseAge.setBounds(387, 85, 86, 20);
		panel.add(textFieldHorseAge);
		textFieldHorseAge.setColumns(10);
		
		JLabel lblStartPosition = new JLabel("Start Position:");
		lblStartPosition.setBounds(276, 140, 86, 14);
		panel.add(lblStartPosition);
		
		textFieldStartPos = new JTextField();
		textFieldStartPos.setBounds(387, 134, 86, 20);
		panel.add(textFieldStartPos);
		textFieldStartPos.setColumns(10);
		
		JLabel lblEndPosition = new JLabel("End Position:");
		lblEndPosition.setBounds(276, 190, 66, 14);
		panel.add(lblEndPosition);
		
		textFieldEndPos = new JTextField();
		textFieldEndPos.setBounds(387, 187, 86, 20);
		panel.add(textFieldEndPos);
		textFieldEndPos.setColumns(10);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmParticipation().dispose();
				ViewR1GeneralTables window = new ViewR1GeneralTables();
				window.getFrmTables().setVisible(true);
			}
		});
		btnBack.setBounds(430, 531, 89, 29);
		getFrmParticipation().getContentPane().add(btnBack);
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
}
