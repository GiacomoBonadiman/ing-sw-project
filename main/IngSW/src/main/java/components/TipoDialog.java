package main.java.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.controllers.SegreteriaController;
import main.java.interfaces.ILoadView;
import main.java.interfaces.IUpdateView;
import main.java.miscellaneous.RefreshableListModel;

public class TipoDialog extends JDialog implements ILoadView, IUpdateView {

	private SegreteriaController controller;
	
	private final JPanel contentPanel = new JPanel();
	private RefreshableListModel<String> sportModel;
	private JList<String> sportList;

	public TipoDialog(SegreteriaController controller) {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		this.controller = controller;
		setTitle("Segreteria - " + controller.getUserFullName());
		
		load();
		initComponents();
	}
	
	public void initComponents() {
		JLabel uninameLabel = new JLabel("Uniname");
		uninameLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		uninameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		uninameLabel.setBounds(65, 15, 100, 25);
		contentPanel.add(uninameLabel);
		
		JTextField uninameField = new JTextField();
		uninameField.setToolTipText("uniname tipo");
		uninameField.setBounds(40, 55, 150, 25);
		contentPanel.add(uninameField);
		uninameField.setColumns(10);
		
		JSeparator uninameSeparator = new JSeparator();
		uninameSeparator.setForeground(Color.BLUE);
		uninameSeparator.setBackground(Color.BLACK);
		uninameSeparator.setBounds(40, 40, 150, 1);
		contentPanel.add(uninameSeparator);
		
		JSeparator descrizioneSeparator = new JSeparator();
		descrizioneSeparator.setForeground(Color.BLUE);
		descrizioneSeparator.setBackground(Color.BLACK);
		descrizioneSeparator.setBounds(243, 40, 250, 1);
		contentPanel.add(descrizioneSeparator);
		
		JLabel descrizioneLabel = new JLabel("Descrizione");
		descrizioneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descrizioneLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		descrizioneLabel.setBounds(268, 15, 100, 25);
		contentPanel.add(descrizioneLabel);
		
		JTextPane descrizioneText = new JTextPane();
		descrizioneText.setToolTipText("descrizione tipo");
		descrizioneText.setBounds(243, 55, 250, 160);
		contentPanel.add(descrizioneText);
		
		JScrollPane sportScroll = new JScrollPane();
		sportScroll.setBounds(15, 183, 200, 280);
		contentPanel.add(sportScroll);
		
		sportModel = new RefreshableListModel<>(controller.getSportsInContainer());
		sportList = new JList<>(sportModel);
		sportList.setToolTipText("lista sport");
		sportScroll.setViewportView(sportList);
		
		JSeparator materialiSeparator = new JSeparator();
		materialiSeparator.setForeground(Color.BLUE);
		materialiSeparator.setBackground(Color.BLACK);
		materialiSeparator.setBounds(243, 327, 250, 1);
		contentPanel.add(materialiSeparator);
		
		JLabel materialiLabel = new JLabel("Materiali");
		materialiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		materialiLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		materialiLabel.setBounds(268, 302, 100, 25);
		contentPanel.add(materialiLabel);
		
		JTextPane materialiText = new JTextPane();
		materialiText.setToolTipText("materiali tipo");
		materialiText.setBounds(243, 342, 250, 160);
		contentPanel.add(materialiText);
		
		JButton createTipoButton = new JButton("Create");
		createTipoButton.setToolTipText("crea tipo");
		createTipoButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		createTipoButton.setBounds(15, 478, 200, 25);
		createTipoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uninameField.getText() == null || !controller.isTipoNew(uninameField.getText())) {
					return;
				}
				if (descrizioneText.getText() == null) {
					return;
				}
				if (materialiText.getText() == null) {
					return;
				}
				if (sportList.isSelectionEmpty()) {
					return;
				}
				controller.createTipoButtonActionPerformed(uninameField.getText(), descrizioneText.getText(), materialiText.getText(), sportList.getSelectedValue());
				dispose();
			}
		});
		contentPanel.add(createTipoButton);
		
		JSeparator generalVerticalSeparator = new JSeparator();
		generalVerticalSeparator.setOrientation(SwingConstants.VERTICAL);
		generalVerticalSeparator.setBackground(Color.WHITE);
		generalVerticalSeparator.setForeground(Color.LIGHT_GRAY);
		generalVerticalSeparator.setBounds(227, 0, 2, 515);
		contentPanel.add(generalVerticalSeparator);
		
		JSeparator generalHorizontalSeparator = new JSeparator();
		generalHorizontalSeparator.setForeground(Color.LIGHT_GRAY);
		generalHorizontalSeparator.setBounds(229, 269, 275, 2);
		contentPanel.add(generalHorizontalSeparator);
		
		JLabel sportLabel = new JLabel("Sport");
		sportLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sportLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		sportLabel.setBounds(65, 143, 100, 25);
		contentPanel.add(sportLabel);
		
		JSeparator sportSeparator = new JSeparator();
		sportSeparator.setForeground(Color.BLUE);
		sportSeparator.setBackground(Color.BLACK);
		sportSeparator.setBounds(40, 168, 150, 1);
		contentPanel.add(sportSeparator);
	}
	
	@Override
	public void load() {
		controller.loadSports();
	}
	
	@Override
	public void update(Object obj) {
		contentPanel.updateUI();
	}
}
