package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jdesktop.swingx.JXDatePicker;

import containers.Magazzino;
import containers.TypeContainer;
import controllers.MagazzinoController;
import interfaces.IJobDialog;
import interfaces.IJobView;
import models.Articolo;

public class ArticoloDialog extends JDialog implements IJobView {

	private MagazzinoController controller;
	
	private JPanel leftPanel, rightPanel, qtyPanel;
	private JScrollPane typeScroll;
	private JList typeList; 
	private JLabel productionDateLabel, unicodeLabel, qtyLabel, priceLabel;
	private JXDatePicker productionDatePicker;
	private JTextField unicodeTextField, qty, priceTextField;
	private JButton qtyPlusButton, qtyMinusButton, createArticoloButton;
	
	public ArticoloDialog(MagazzinoController controller) {
		super();
		setAlwaysOnTop(true);
		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setPreferredSize(new Dimension(500, 500));
		setSize(new Dimension(500, 500));
		
		this.controller = controller;
		
		load();
		initComponents();
		buildView();
	}
	
	private void initComponents() {
		leftPanel = new JPanel(new GridBagLayout());
		rightPanel = new JPanel(new GridBagLayout());
		qtyPanel = new JPanel();
		qtyPanel.setLayout(new BoxLayout(qtyPanel, BoxLayout.X_AXIS));
        typeList = new JList<>(controller.getTypeContainer().getTypes().keySet().toArray());
        typeList.setPreferredSize(new Dimension(245, 200));
        typeList.setVisibleRowCount(-1);
		((DefaultListCellRenderer)typeList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        typeScroll = new JScrollPane(typeList);
        productionDateLabel = new JLabel("Production Date", JLabel.CENTER);
        productionDatePicker = new JXDatePicker();
        unicodeLabel = new JLabel("Unicode", JLabel.CENTER);
        unicodeTextField = new JTextField();
        qtyLabel = new JLabel("Qty", JLabel.CENTER);
        qty = new JTextField("1");
        qtyPlusButton = new JButton("+");
        qtyMinusButton = new JButton("-");
        priceLabel = new JLabel("Price", JLabel.CENTER);
        priceTextField = new JTextField();
        createArticoloButton = new JButton("Create");
	}
	
	private void buildView() {
		GridBagConstraints constr = new GridBagConstraints();
		
		constr.fill = GridBagConstraints.BOTH;
		constr.weighty = 0.65;
		constr.insets = new Insets(5, 5, 5, 5);
		leftPanel.add(typeScroll, constr);
		
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weighty = 0.1;
		rightPanel.add(productionDateLabel, constr);
		
		constr.gridy = 1;
		rightPanel.add(productionDatePicker, constr);
		
		constr.gridy = 2;
		constr.insets = new Insets(10, 5, 5 , 5);
		rightPanel.add(unicodeLabel, constr);
		
		constr.gridy = 3;
		constr.insets = new Insets(5, 5, 5, 5);
		rightPanel.add(unicodeTextField, constr);
		
		constr.gridy = 4;
		constr.insets = new Insets(10, 5, 5 , 5);
		rightPanel.add(qtyLabel, constr);
		
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridy = 5;
		constr.insets = new Insets(5, 5, 5, 5);
		
		qtyMinusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.minusButtonPressed(e, qty);
			}
		});
		qtyPanel.add(qtyMinusButton);
		qtyPanel.add(qty);
		qtyPlusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.plusButtonActionPerformed(e, qty);
			}
		});
		qtyPanel.add(qtyPlusButton);
		rightPanel.add(qtyPanel, constr);
		
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridy = 6;
		constr.insets = new Insets(10, 5, 5, 5);
		rightPanel.add(priceLabel, constr);
		
		constr.gridy = 7;
		constr.insets = new Insets(5, 5, 5, 5);
		priceTextField.setText("0");
		rightPanel.add(priceTextField, constr);
		
		constr.gridy = 8;
		constr.insets = new Insets(20, 5, 5, 5);
		createArticoloButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (typeList.getSelectedValue() == null) {
					return;
				}
				if (unicodeTextField.getText() == null) {
					return;
				}
				if (qty.getText() == null) {
					return;
				}
				if (priceTextField.getText() == null) {
					return;
				}
				if (productionDatePicker.getDate() == null) {
					return;
				}
				try {
					Integer.parseInt(qty.getText());
				} catch(NumberFormatException ex) {
					return;
				}
				try {
					Double.parseDouble(priceTextField.getText());
				} catch(NumberFormatException ex) {
					return;
				}
				
				controller.createArticoloButtonActionPerformed(e, ArticoloDialog.this, controller.getTypeContainer().getTypes().get(String.valueOf(typeList.getSelectedValue())), unicodeTextField.getText(), Integer.parseInt(qty.getText()), Double.parseDouble(priceTextField.getText()), productionDatePicker.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				dispose();
			}
		});
		rightPanel.add(createArticoloButton, constr);
		
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
	}
	
	@Override
	public void load() {
		controller.loadTipi(this);
	}
	
	@Override
	public void update() {
		typeList.updateUI();
	}
}
