package main.java.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import main.java.controllers.MagazzinoController;
import main.java.interfaces.ILoadView;
import main.java.miscellaneous.RefreshableListModel;
import main.java.models.Tipo;
import main.java.renderers.TipoRenderer;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;

public class ArticoloDialog extends JDialog implements ILoadView {

	private final JPanel contentPanel = new JPanel();
	private RefreshableListModel<Tipo> tipiModel;
	private JList<Tipo> tipiList;
	
	private final MagazzinoController controller;

	public ArticoloDialog(MagazzinoController controller) {

		setResizable(false);
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 500, 550);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 494, 515);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		this.controller = controller;
		setTitle("Magazzino - " + this.controller.getUserFullName());
		
		load();
		initComponents();
		
	}
	
	private void initComponents() {
		JLabel tipiLabel = new JLabel("Tipi");
		tipiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tipiLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		tipiLabel.setBounds(15, 15, 225, 25);
		contentPanel.add(tipiLabel);
		
		JSeparator tipiSeparator = new JSeparator();
		tipiSeparator.setForeground(Color.BLUE);
		tipiSeparator.setBackground(Color.BLACK);
		tipiSeparator.setBounds(15, 40, 225, 1);
		contentPanel.add(tipiSeparator);
		
		JScrollPane tipiScroll = new JScrollPane();
		tipiScroll.setToolTipText("tipi articolo");
		tipiScroll.setBounds(15, 65, 225, 400);
		contentPanel.add(tipiScroll);
		
		tipiModel = new RefreshableListModel<Tipo>(controller.getTipiInContainer());
		tipiList = new JList<>(tipiModel);
		tipiList.setToolTipText("tipi articolo");
		tipiList.setCellRenderer(new TipoRenderer());
		tipiScroll.setViewportView(tipiList);
		
		JLabel unicodeLabel = new JLabel("Unicode");
		unicodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		unicodeLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		unicodeLabel.setBounds(290, 40, 100, 25);
		contentPanel.add(unicodeLabel);
		
		JSeparator unicodeSeparator = new JSeparator();
		unicodeSeparator.setForeground(Color.BLUE);
		unicodeSeparator.setBackground(Color.BLACK);
		unicodeSeparator.setBounds(265, 65, 150, 1);
		contentPanel.add(unicodeSeparator);
		
		JTextField unicodeField = new JTextField();
		unicodeField.setToolTipText("unicode articolo");
		unicodeField.setColumns(10);
		unicodeField.setBounds(265, 80, 150, 25);
		contentPanel.add(unicodeField);
		
		JLabel dataLabel = new JLabel("Data");
		dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dataLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		dataLabel.setBounds(290, 130, 100, 25);
		contentPanel.add(dataLabel);
		
		JSeparator dataSeparator = new JSeparator();
		dataSeparator.setForeground(Color.BLUE);
		dataSeparator.setBackground(Color.BLACK);
		dataSeparator.setBounds(265, 155, 150, 1);
		contentPanel.add(dataSeparator);
		
		JDateChooser dataChooser = new JDateChooser();
		dataChooser.setToolTipText("data produzione");
		dataChooser.setBounds(265, 170, 150, 25);
		contentPanel.add(dataChooser);
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		priceLabel.setBounds(290, 220, 100, 25);
		contentPanel.add(priceLabel);
		
		JSeparator priceSeparator = new JSeparator();
		priceSeparator.setForeground(Color.BLUE);
		priceSeparator.setBackground(Color.BLACK);
		priceSeparator.setBounds(265, 245, 150, 1);
		contentPanel.add(priceSeparator);
		
		SpinnerNumberModel model = new SpinnerNumberModel(0.01, 0.01, 999999.99, 0.01);
		JSpinner priceSpinner = new JSpinner(model);
		priceSpinner.setToolTipText("costo");
		priceSpinner.setBounds(265, 260, 150, 25);
		contentPanel.add(priceSpinner);
		
		JButton createArticoloButton = new JButton("Create Articolo");
		createArticoloButton.setToolTipText("crea articolo");
		createArticoloButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		createArticoloButton.setBounds(255, 345, 175, 35);
		createArticoloButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean check = false;
				if (tipiList.isSelectionEmpty()) {
					if (!tipiLabel.getForeground().equals(Color.RED)) {
						tipiLabel.setForeground(Color.RED);
					}
					check = true;
				}
				if (unicodeField.getText() == null || !controller.isIngressoNew(unicodeField.getText())) {
					if (!unicodeLabel.getForeground().equals(Color.RED)) {
						unicodeLabel.setForeground(Color.RED);
					}
					check = true;
				}
				if (dataChooser.getDate() == null || dataChooser.getDate().after(Calendar.getInstance().getTime())) {
					if (!dataLabel.getForeground().equals(Color.RED)) {
						dataLabel.setForeground(Color.RED);
					}
					check = true;
				}
				try {
					priceSpinner.commitEdit();
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				if (check) {
					return;
				}
				if (tipiLabel.getForeground().equals(Color.RED)) {
					tipiLabel.setForeground(Color.BLACK);
				}
				if (unicodeLabel.getForeground().equals(Color.RED)) {
					unicodeLabel.setForeground(Color.BLACK);
				}
				if (dataLabel.getForeground().equals(Color.RED)) {
					dataLabel.setForeground(Color.BLACK);
				}
				contentPanel.updateUI();
				controller.createArticoloButtonActionPerformed(tipiList.getSelectedValue(), unicodeField.getText(), 0, (Double)priceSpinner.getValue(), dataChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}
		});
		contentPanel.add(createArticoloButton);
	}
	
	@Override
	public void load() {
		controller.loadTipi();
	}
}
