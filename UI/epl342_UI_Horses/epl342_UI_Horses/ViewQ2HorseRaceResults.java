package epl342_UI_Horses;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;

public class ViewQ2HorseRaceResults extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewQ2HorseRaceResults dialog = new ViewQ2HorseRaceResults();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewQ2HorseRaceResults() {
		setTitle("Horse Race Results From A Meeting");
		setBounds(100, 100, 523, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPleaseEnter = new JLabel("Please enter a Meeting Date:");
		lblPleaseEnter.setBounds(29, 14, 169, 14);
		contentPanel.add(lblPleaseEnter);
		{
			DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
			JFormattedTextField formattedTextField = new JFormattedTextField(format);
			formattedTextField.setBounds(208, 8, 133, 26);
			contentPanel.add(formattedTextField);
		}
		{
			JLabel lblFormatddmmyyyy = new JLabel("format: (DD/MM/YYYY)");
			lblFormatddmmyyyy.setBounds(351, 14, 146, 14);
			contentPanel.add(lblFormatddmmyyyy);
		}
		{
			table = new JTable();
			table.setBounds(10, 86, 487, 235);
			contentPanel.add(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(-10, 39, 507, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton btnSearch = new JButton("SEARCH");
				buttonPane.add(btnSearch);
			}
			{
				JButton cancelButton = new JButton("BACK");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
