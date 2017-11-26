package epl342_UI_Horses;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class ViewQ4HorsesOfOwnerFamily extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewQ4HorsesOfOwnerFamily dialog = new ViewQ4HorsesOfOwnerFamily();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewQ4HorsesOfOwnerFamily() {
		setTitle("Horse List with horses of a specific Owner Famliy");
		setBounds(100, 100, 459, 426);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			table = new JTable();
			table.setBounds(10, 66, 423, 306);
			contentPanel.add(table);
		}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(139, 23, 205, 20);
		contentPanel.add(comboBox);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a family:");
		lblPleaseSelectA.setBounds(10, 26, 179, 14);
		contentPanel.add(lblPleaseSelectA);
		{
			JButton cancelButton = new JButton("BACK");
			cancelButton.setBounds(374, 22, 59, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
	}
}
