package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class ViewR1HorseColor {

	private JFrame frmColorName;
	private JTextField textFieldColorName;
	private JTable table;
	
	boolean addRecord = false;
	
	private void clearInputBoxesHorses() {
		
		textFieldColorName.setText("");
	    	
	    }
	
	public JFrame getFrmColorName() {
		return frmColorName;
	}

	public void setFrmColorName(JFrame frmColorName) {
		this.frmColorName = frmColorName;
	}
	
	private void loadRecords() throws SQLException  {
		
	    String sql_stmt = "SELECT * FROM [dbo].[HORSE_COLOR];";

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
					ViewR1HorseColor window = new ViewR1HorseColor();
					window.getFrmColorName().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1HorseColor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmColorName(new JFrame());
		getFrmColorName().setTitle("COLOR NAME");
		getFrmColorName().setBounds(100, 100, 416, 459);
		getFrmColorName().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmColorName().getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
					getFrmColorName().dispose();
					ViewR1GeneralTables window = new ViewR1GeneralTables();
					window.getFrmTables().setVisible(true);
			
			}
		});
		btnBack.setBounds(288, 373, 99, 36);
		getFrmColorName().getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 377, 240);	
		getFrmColorName().getContentPane().add(scrollPane);
				
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Color Name Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 253, 375, 120);
		getFrmColorName().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel LblColorName = new JLabel("Color Name:");
		LblColorName.setBounds(10, 38, 82, 14);
		panel.add(LblColorName);
		
		textFieldColorName = new JTextField();
		textFieldColorName.setBounds(92, 35, 86, 20);
		panel.add(textFieldColorName);
		textFieldColorName.setColumns(10);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.setBounds(10, 80, 89, 31);
		panel.add(btnAddNew);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBounds(109, 80, 89, 31);
		panel.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//???????????????????????????????????????
			}
		});
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//???????????????????????????????
			}
		});
		
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
}
