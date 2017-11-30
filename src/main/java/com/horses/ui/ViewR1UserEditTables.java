package com.horses.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ViewR1UserEditTables {

	public JFrame frmEditTables;
	private JTable tableHorses;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldWeight;
	private JTextField textFieldAge;
	private JTextField textFieldOriginCountry;
	private JTextField textFieldRecord;
	private JFormattedTextField formattedTextField;
	private JComboBox comboBoxSex;
	private JCheckBox checkBoxIsPurebred;
	private JComboBox comboBoxColor;
	private JComboBox comboBoxMom ;
	private JComboBox comboBoxDad ;
	private JComboBox comboBoxJockey ;
	private JComboBox comboBoxBreeder;
	private JComboBox comboBoxTrainer;
	private JComboBox comboBoxOwner;
	
	boolean addRecord = false;

    private void clearInputBoxesHorses() {
    	textFieldID.setText("");
    	textFieldName.setText("");    	
    	textFieldWeight.setText("");
    	textFieldAge.setText("");
    	textFieldOriginCountry.setText("");
    	textFieldRecord.setText("");
    	comboBoxSex.setSelectedItem("");
    	checkBoxIsPurebred.setSelected(false);
    	comboBoxColor.setSelectedItem("");
    	comboBoxMom.setSelectedItem("");
    	comboBoxDad.setSelectedItem("");
    	comboBoxJockey.setSelectedItem("");
    	comboBoxBreeder.setSelectedItem("");
    	comboBoxTrainer.setSelectedItem("");
    	comboBoxOwner.setSelectedItem("");
    	formattedTextField.setText("");
    }

    private void setComboBoxes() throws SQLException {

//             DBUtilities dbUtilities = new DBUtilities();
//             dbUtilities.ExecuteSQLStatement(sql_stmt);
    }
    
    
    private void addNew() throws SQLException {
        String sql_stmt = "INSERT INTO [dbo].[HORSE] ([id],[name],[sex],[color_name],[date_of_birth],[cur_weight],[trainer_id],[owner_id],[breeder_id],[dad_id],[mama_id])";
        sql_stmt += " VALUES ('" +  textFieldID.getText() + "','" +
        							textFieldName.getText() + "','"+
        							comboBoxSex.getSelectedItem().toString() + "','" + 
        							comboBoxColor.getSelectedItem().toString() + "','" + 
        							formattedTextField.getText() + "','" + 
        							textFieldWeight.getText() + "','" + 
        							comboBoxTrainer.getSelectedItem().toString() + "','" + 
        							comboBoxOwner.getSelectedItem().toString() + "','" + 
        							comboBoxBreeder.getSelectedItem().toString() + "','" + 
        							comboBoxDad.getSelectedItem().toString() + "','" + 
        							comboBoxMom.getSelectedItem().toString() + "')";

        DBUtilities dbUtilities = new DBUtilities();

        dbUtilities.ExecuteSQLStatement(sql_stmt);
    }

    private void updateRecord() throws SQLException {
    	
        Date date1 = null;
        try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(formattedTextField.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}  
    	
        String sql_stmt = "UPDATE [dbo].[HORSE] SET [name] = '" + textFieldName.getText() + "'";
        sql_stmt += ",[cur_weight] = '" + textFieldWeight.getText() + "'";
        sql_stmt += ",[date_of_birth] = '" + date1.getTime() + "'";
        sql_stmt += ",[sex] = '" + comboBoxSex.getSelectedItem().toString() + "'";
        sql_stmt += ",[is_purebred] = '" + checkBoxIsPurebred.isSelected() + "'";
        sql_stmt += ",[record] = '" + textFieldRecord.getText() + "'";
        sql_stmt += ",[origin_country] = '" + textFieldOriginCountry.getText() + "'";
        sql_stmt += ",[mama_id] = '" + comboBoxMom.getSelectedItem().toString() + "'";
        sql_stmt += ",[dad_id] = '" + comboBoxDad.getSelectedItem().toString() + "'";
        sql_stmt += ",[jockey_id] = '" + comboBoxJockey.getSelectedItem().toString() + "'";
        sql_stmt += ",[breeder_id] = '" + comboBoxBreeder.getSelectedItem().toString() + "'";
        sql_stmt += ",[color_name] = '" + comboBoxColor.getSelectedItem().toString() + "'";
        sql_stmt += ",[trainer_id] = '" + comboBoxTrainer.getSelectedItem().toString() + "'";
        sql_stmt += ",[owner_id] = '" + comboBoxOwner.getSelectedItem().toString() + "'";
        sql_stmt += " WHERE id = '" + textFieldID.getText() + "'";

        DBUtilities dbUtilities = new DBUtilities();
        dbUtilities.ExecuteSQLStatement(sql_stmt);
    }

    
    private void deleteRecord() throws SQLException {
        String sql_stmt = "DELETE FROM [dbo].[HORSE] WHERE [id] = '" + textFieldID.getText() + "'";
        DBUtilities dbUtilities = new DBUtilities();
        dbUtilities.ExecuteSQLStatement(sql_stmt);
    }

    
    
    private void loadRecords() throws SQLException  {
    	
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
                    
                    textFieldID.setText(id.toString());
					textFieldName.setText(name.toString());
					textFieldWeight.setText(cur_weight.toString());
					formattedTextField.setText(date_of_birth.toString());
			    	textFieldAge.setText(age.toString());
					comboBoxSex.setSelectedItem(sex.toString());
					try {
						checkBoxIsPurebred.setSelected(Boolean.parseBoolean(is_purebred.toString()));
					} catch ( NullPointerException e) {
						checkBoxIsPurebred.setSelected(false);
					}
					try {
						comboBoxColor.setSelectedItem(color_name.toString());
					} catch ( NullPointerException e) {
						comboBoxColor.setSelectedItem(null);
					}
					try {
						comboBoxTrainer.setSelectedItem(trainer_id.toString());
					} catch ( NullPointerException e) {
						comboBoxTrainer.setSelectedItem(null);
					}
					try {
						comboBoxOwner.setSelectedItem(owner_id.toString());						
					} catch ( NullPointerException e) {
						comboBoxOwner.setSelectedItem(null);
					}
					try {
						comboBoxBreeder.setSelectedItem(breeder_id.toString());
					} catch ( NullPointerException e) {
						comboBoxBreeder.setSelectedItem(null);
					}
					try {						
						comboBoxDad.setSelectedItem(dad_id.toString());
					} catch ( NullPointerException e) {
						comboBoxDad.setSelectedItem(null);
					}
					try {
						comboBoxMom.setSelectedItem(mama_id.toString());						
					} catch ( NullPointerException e) {
						comboBoxMom.setSelectedItem(null);
					}
					try {
						textFieldOriginCountry.setText(origin_country.toString());
					} catch ( NullPointerException e) {
						textFieldOriginCountry.setText(null);
					}
					try {
						textFieldRecord.setText(record.toString());
					} catch (NullPointerException e) {
						textFieldRecord.setText(null);	
					} 
					try {
						comboBoxJockey.setSelectedItem(jockey_id.toString());						
					} catch ( NullPointerException e) {
						comboBoxJockey.setSelectedItem(null);
					}
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewR1UserEditTables window = new ViewR1UserEditTables();

					window.frmEditTables.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewR1UserEditTables() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditTables = new JFrame();
		frmEditTables.setTitle("EDIT TABLES");
		frmEditTables.setBounds(100, 100, 777, 648);
		frmEditTables.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditTables.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 741, 554);
		frmEditTables.getContentPane().add(tabbedPane);
		
		JPanel panelHorses = new JPanel();
		tabbedPane.addTab("Horses", null, panelHorses, null);
		panelHorses.setLayout(null);

		
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 11, 716, 176);
		panelHorses.add(scrollPane);
		
		tableHorses = new JTable();
		scrollPane.setViewportView(tableHorses);
		
		JPanel panelHorseEditor = new JPanel();
		panelHorseEditor.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Horses Record Editor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelHorseEditor.setBounds(10, 203, 716, 262);
		panelHorses.add(panelHorseEditor);
		panelHorseEditor.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 26, 46, 14);
		panelHorseEditor.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(95, 23, 86, 20);
		panelHorseEditor.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 57, 46, 14);
		panelHorseEditor.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(95, 54, 86, 20);
		panelHorseEditor.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(10, 88, 46, 14);
		panelHorseEditor.add(lblWeight);
		
		textFieldWeight = new JTextField();
		textFieldWeight.setBounds(95, 85, 86, 20);
		panelHorseEditor.add(textFieldWeight);
		textFieldWeight.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth:");
		lblDateOfBirth.setBounds(10, 119, 100, 14);
		panelHorseEditor.add(lblDateOfBirth);
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		formattedTextField = new JFormattedTextField(format);
		formattedTextField.setBounds(95, 116, 86, 20);
		panelHorseEditor.add(formattedTextField);
		
		JLabel label = new JLabel("format: (dd/MM/yyyy)");
		label.setBounds(188, 119, 146, 14);
		panelHorseEditor.add(label);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(344, 119, 46, 14);
		panelHorseEditor.add(lblAge);
		
		textFieldAge = new JTextField();
		textFieldAge.setEditable(false);
		textFieldAge.setBounds(378, 116, 86, 20);
		panelHorseEditor.add(textFieldAge);
		textFieldAge.setColumns(10);
		
		JLabel lblSex = new JLabel("Sex:");
		lblSex.setBounds(10, 145, 46, 14);
		panelHorseEditor.add(lblSex);
		
		// SET HORSE SEX COMBO BOX
		comboBoxSex = new JComboBox();
		try {
	    	 String sql_stmt = "SELECT * FROM [dbo].[HORSE_SEX];";
	    	 ResultSetTableModel sexCombo = new ResultSetTableModel(sql_stmt);
	    	 for(int i=0; i< sexCombo.getRowCount(); i++){
	    		 String s = (sexCombo.getValueAt(i, 0).toString());
	    		 comboBoxSex.addItem(s);
	    	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxSex.setBounds(95, 144, 86, 20);
		panelHorseEditor.add(comboBoxSex);
		
		JLabel lblIsPurebred = new JLabel("Is Purebred:");
		lblIsPurebred.setBounds(10, 170, 79, 14);
		panelHorseEditor.add(lblIsPurebred);
		
		checkBoxIsPurebred = new JCheckBox("");
		checkBoxIsPurebred.setBounds(95, 168, 97, 23);
		panelHorseEditor.add(checkBoxIsPurebred);
		
		JLabel lblOriginCountry = new JLabel("Origin Country:");
		lblOriginCountry.setBounds(10, 201, 100, 14);
		panelHorseEditor.add(lblOriginCountry);
		
		textFieldOriginCountry = new JTextField();
		textFieldOriginCountry.setBounds(95, 198, 86, 20);
		panelHorseEditor.add(textFieldOriginCountry);
		textFieldOriginCountry.setColumns(10);
		
		JLabel lblMomId = new JLabel("Mom ID:");
		lblMomId.setBounds(350, 26, 60, 14);
		panelHorseEditor.add(lblMomId);
		
		JLabel lblDadId = new JLabel("Dad ID: ");
		lblDadId.setBounds(350, 57, 60, 14);
		panelHorseEditor.add(lblDadId);
		
		JLabel lblJockeyId = new JLabel("Jockey ID: ");
		lblJockeyId.setBounds(350, 88, 79, 14);
		panelHorseEditor.add(lblJockeyId);
		
		// SET MOM ID COMBO BOX
		comboBoxMom = new JComboBox();
		comboBoxMom.insertItemAt("", 0);
		try {
		   	 String sql_stmt_mom = "SELECT * FROM [dbo].[HORSE];";
		   	 ResultSetTableModel momCombo = new ResultSetTableModel(sql_stmt_mom);
		   	 for(int i=0; i< momCombo.getRowCount(); i++){
		   		 String s = (momCombo.getValueAt(i, 0).toString());
		   		 comboBoxMom.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxMom.setBounds(413, 23, 127, 20);
		panelHorseEditor.add(comboBoxMom);
		
		// SET DAD ID COMBO BOX
		comboBoxDad = new JComboBox();
		comboBoxDad.insertItemAt("", 0);
		try {
		   	 String sql_stmt_dad = "SELECT * FROM [dbo].[HORSE];";
		   	 ResultSetTableModel dadCombo = new ResultSetTableModel(sql_stmt_dad);
		   	 for(int i=0; i< dadCombo.getRowCount(); i++){
		   		 String s = (dadCombo.getValueAt(i, 0).toString());
		   		comboBoxDad.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxDad.setBounds(413, 54, 127, 20);
		panelHorseEditor.add(comboBoxDad);
		
		// SET JOCKEY ID COMBO BOX
		comboBoxJockey = new JComboBox();
		comboBoxJockey.insertItemAt("", 0);
		try {
		   	 String sql_stmt_jockey = "SELECT * FROM [dbo].[JOCKEY];";
		   	 ResultSetTableModel jockeyCombo = new ResultSetTableModel(sql_stmt_jockey);
		   	 for(int i=0; i< jockeyCombo.getRowCount(); i++){
		   		 String s = (jockeyCombo.getValueAt(i, 0).toString());
		   		comboBoxJockey.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxJockey.setBounds(413, 85, 127, 20);
		panelHorseEditor.add(comboBoxJockey);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(10, 226, 46, 14);
		panelHorseEditor.add(lblColor);
		
		comboBoxColor = new JComboBox();
		try {
		   	 String sql_stmt_HORSE_COLOR = "SELECT * FROM [dbo].[HORSE_COLOR];";
		   	 ResultSetTableModel HorseColorCombo = new ResultSetTableModel(sql_stmt_HORSE_COLOR);
		   	 for(int i=0; i< HorseColorCombo.getRowCount(); i++){
		   		 String s = (HorseColorCombo.getValueAt(i, 0).toString());
		   		comboBoxColor.addItem(s);
		   	 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxColor.setBounds(95, 226, 86, 25);
		panelHorseEditor.add(comboBoxColor);
		
		JLabel lblBreederId = new JLabel("Breeder ID:");
		lblBreederId.setBounds(344, 150, 75, 14);
		panelHorseEditor.add(lblBreederId);
		
		JLabel lblTrainerId = new JLabel("Trainer ID:");
		lblTrainerId.setBounds(344, 177, 85, 14);
		panelHorseEditor.add(lblTrainerId);
		
		JLabel lblOwnerId = new JLabel("Owner ID:");
		lblOwnerId.setBounds(344, 204, 66, 14);
		panelHorseEditor.add(lblOwnerId);
		
		comboBoxBreeder = new JComboBox<>();
		comboBoxBreeder.insertItemAt("", 0);
		try {
		   	 String sql_stmt_BREEDER = "SELECT * FROM [dbo].[BREEDER];";
		   	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt_BREEDER);
		   	 for(int i=0; i< Combo.getRowCount(); i++){
		   		 String s = (Combo.getValueAt(i, 0).toString());
		   		comboBoxBreeder.addItem(s);
		   	 }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		comboBoxBreeder.setBounds(413, 147, 127, 20);
		panelHorseEditor.add(comboBoxBreeder);
		
		comboBoxTrainer = new JComboBox<>();
		comboBoxTrainer.insertItemAt("", 0);
		try {
	    	 String sql_stmt_Trainer = "SELECT * FROM [dbo].[TRAINER];";
	    	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt_Trainer);
	    	 for(int i=0; i< Combo.getRowCount(); i++){
	    		 String s = (Combo.getValueAt(i, 0).toString());
	    		 comboBoxTrainer.addItem(s);
	    	 }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		comboBoxTrainer.setBounds(413, 174, 127, 17);
		panelHorseEditor.add(comboBoxTrainer);
		
		comboBoxOwner = new JComboBox<>();
		comboBoxOwner.insertItemAt("", 0);
		try {
	    	 String sql_stmt_Owner = "SELECT * FROM [dbo].[OWNER];";
	    	 ResultSetTableModel Combo = new ResultSetTableModel(sql_stmt_Owner);
	    	 for(int i=0; i< Combo.getRowCount(); i++){
	    		 String s = (Combo.getValueAt(i, 0).toString());
	    		 comboBoxOwner.addItem(s);
	    	 }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		comboBoxOwner.setBounds(413, 198, 127, 17);
		panelHorseEditor.add(comboBoxOwner);
		
		JLabel lblRecord = new JLabel("Record:");
		lblRecord.setBounds(344, 231, 66, 14);
		panelHorseEditor.add(lblRecord);
		
		textFieldRecord = new JTextField();
		textFieldRecord.setBounds(413, 228, 293, 23);
		panelHorseEditor.add(textFieldRecord);
		textFieldRecord.setColumns(10);
		
		JButton btnAddNew = new JButton("ADD NEW");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
				addRecord = true;
				clearInputBoxesHorses();
				textFieldID.requestFocus();
			}
		});
		btnAddNew.setBounds(10, 476, 89, 23);
		panelHorses.add(btnAddNew);
		
		JButton btnEdit = new JButton("UPDATE");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					updateRecord();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEdit.setBounds(109, 476, 89, 23);
		panelHorses.add(btnEdit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm Delete Record?", JOptionPane.YES_NO_OPTION);

			        if (dialogResult == JOptionPane.YES_OPTION) {
			            try {
			                deleteRecord();

			                loadRecords();
			            } catch (SQLException ex) {
			                System.out.println(ex.getMessage());
			            }
			        }
			}
		});
		//
		btnDelete.setVisible(false);
		btnDelete.setBounds(208, 476, 89, 23);
		panelHorses.add(btnDelete);
		
		JPanel panelHorseColor = new JPanel();
		tabbedPane.addTab("Horse Color", null, panelHorseColor, null);
		
		JPanel panelJockey = new JPanel();
		tabbedPane.addTab("Jockey", null, panelJockey, null);
		
		JPanel panelMeeting = new JPanel();
		tabbedPane.addTab("Meeting", null, panelMeeting, null);
		
		JPanel panelRace = new JPanel();
		tabbedPane.addTab("Race", null, panelRace, null);
		
		JPanel panelRaceDistance = new JPanel();
		tabbedPane.addTab("Race Distances", null, panelRaceDistance, null);
		
		JPanel panelRaceType = new JPanel();
		tabbedPane.addTab("Race Type", null, panelRaceType, null);
		
		JPanel panelFieldType = new JPanel();
		tabbedPane.addTab("Field Type", null, panelFieldType, null);
		
		JPanel panelParticipation = new JPanel();
		tabbedPane.addTab("Participation", null, panelParticipation, null);
		
		JPanel panelTrainer = new JPanel();
		tabbedPane.addTab("Trainer", null, panelTrainer, null);
		
		JPanel panelBreeder = new JPanel();
		tabbedPane.addTab("Breeder", null, panelBreeder, null);
		
		JPanel panelOwner = new JPanel();
		tabbedPane.addTab("Owner", null, panelOwner, null);
		
		JPanel panelFamily = new JPanel();
		tabbedPane.addTab("Family", null, panelFamily, null);
		
		JPanel panelSystemUser = new JPanel();
		tabbedPane.addTab("System User", null, panelSystemUser, null);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEditTables.dispose();
				ViewR1UserGUI window = new ViewR1UserGUI();
				window.frmPleaseChooseA.setVisible(true);
			}
		});
		btnBack.setBounds(662, 576, 89, 23);
		frmEditTables.getContentPane().add(btnBack);
		try {
			loadRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
