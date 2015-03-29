import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class NewUserDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5784685866789243511L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfLogin;
	private JPasswordField passwordField;
	private MainWindow main_window;

	/**
	 * Create the dialog.
	 */
	public NewUserDialog(MainWindow main_window) {
		setResizable(false);
		this.main_window=main_window;
		setTitle("Create new user");
		setBounds(100, 100, 280, 140);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{70, 184, 0};
		gbl_contentPanel.rowHeights = new int[]{30, 24, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblLogin = new JLabel("Login");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.WEST;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		contentPanel.add(lblLogin, gbc_lblLogin);
		
		tfLogin = new JTextField();
		tfLogin.setColumns(10);
		GridBagConstraints gbc_tfLogin = new GridBagConstraints();
		gbc_tfLogin.fill = GridBagConstraints.BOTH;
		gbc_tfLogin.insets = new Insets(0, 0, 5, 0);
		gbc_tfLogin.gridx = 1;
		gbc_tfLogin.gridy = 0;
		contentPanel.add(tfLogin, gbc_tfLogin);
		
		JLabel lblNewLabel = new JLabel("Password");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		contentPanel.add(passwordField, gbc_passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String login = tfLogin.getText();
						String pass = new String(passwordField.getPassword());
						NewUserDialog.this.main_window.registerUser(new User(login, pass));
						NewUserDialog.this.setVisible(false);
						NewUserDialog.this.dispatchEvent(new WindowEvent(
								NewUserDialog.this, WindowEvent.WINDOW_CLOSING));
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						NewUserDialog.this.setVisible(false);
						NewUserDialog.this.dispatchEvent(new WindowEvent(
								NewUserDialog.this, WindowEvent.WINDOW_CLOSING));
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
