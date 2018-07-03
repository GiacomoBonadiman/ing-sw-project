package main.java.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import main.java.controllers.LoginController;

public class LoginView extends JFrame {
	
	private JPanel titlePanel, contentPanel;
	private JLabel titleLabel, loginFailedLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	
	protected final LoginController controller;
	
	public LoginView(LoginController controller) {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));
        this.controller = controller;
        initComponents();
        buildView();
        controller.loadUsers();
	}
	
	private void initComponents() {
		titlePanel = new JPanel(new GridBagLayout());
		contentPanel = new JPanel(new GridBagLayout());
		titleLabel = new JLabel("Log In Form", JLabel.CENTER);
		loginFailedLabel = new JLabel("Username e//o Password errati", JLabel.CENTER);
		usernameField = new JTextField(20);
		passwordField = new JPasswordField(20);
		loginButton = new JButton("Log IN");
	}
	
	private void buildView() {
		GridBagConstraints titleConstr = new GridBagConstraints();
		GridBagConstraints contentConstr = new GridBagConstraints();
		
		titleLabel.setFont(new Font("Serif", Font.PLAIN, 36));
		titleConstr.fill = GridBagConstraints.HORIZONTAL;
		titleConstr.ipady = 40;
		titleConstr.weightx = 0.0;
		titleConstr.gridwidth = 3;
		titleConstr.gridx = 0;
		titleConstr.gridy = 1;
		titleConstr.insets = new Insets(5, 0, 0, 0);
		titlePanel.add(titleLabel, titleConstr);
		
		usernameField.setToolTipText("Username");
		contentConstr.fill = GridBagConstraints.HORIZONTAL;
		contentConstr.weightx = 0.0;
		contentConstr.gridx = 1;
		contentConstr.gridy = 1;
		contentConstr.insets = new Insets(15, 0, 0, 0);
		usernameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	JPasswordField field = (JPasswordField)e.getSource();
			    	LoginView view = (LoginView)SwingUtilities.getRoot(field);
			    	if (controller.onLoginButtonClicked(view)) {
			    		view.setVisible(false);
			    	} else {
			    		view.getLoginFailedLabel().setVisible(true);
			    	}
			    }
			}
		});
		contentPanel.add(usernameField, contentConstr);
		
		passwordField.setToolTipText("Password");
		contentConstr.gridy = 2;
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	JPasswordField field = (JPasswordField)e.getSource();
			    	LoginView view = (LoginView)SwingUtilities.getRoot(field);
			    	if (controller.onLoginButtonClicked(view)) {
			    		view.setVisible(false);
			    	} else {
			    		view.getLoginFailedLabel().setVisible(true);
			    	}
			    }
			}
		});
		contentPanel.add(passwordField, contentConstr);
		
		contentConstr.gridy = 3;
		contentConstr.insets = new Insets(30, 0, 0, 0);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton)e.getSource();
				LoginView view = (LoginView)SwingUtilities.getRoot(source);
				if (controller.onLoginButtonClicked(view)) {
					view.setVisible(false);
					dispose();
				} else {
					view.getLoginFailedLabel().setVisible(true);
				}
			}
		});
		contentPanel.add(loginButton, contentConstr);
		
		loginFailedLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		contentConstr.gridy = 4;
		contentConstr.insets = new Insets(50, 0, 0, 0);
		loginFailedLabel.setVisible(false);
		contentPanel.add(loginFailedLabel, contentConstr);
		
		add(titlePanel, BorderLayout.NORTH);
		add(contentPanel);
	}

	public JPanel getTitlePanel() {
		return titlePanel;
	}

	public void setTitlePanel(JPanel titlePanel) {
		this.titlePanel = titlePanel;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public JTextField getUsernameField() {
		return usernameField;
	}

	public JLabel getLoginFailedLabel() {
		return loginFailedLabel;
	}

	public void setUsernameField(JTextField usernameField) {
		this.usernameField = usernameField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}

	public LoginController getController() {
		return controller;
	}

	public void setLoginFailedLabel(JLabel loginFailedLabel) {
		this.loginFailedLabel = loginFailedLabel;
	}
}
