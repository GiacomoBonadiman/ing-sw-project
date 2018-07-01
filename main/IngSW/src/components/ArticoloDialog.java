package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import controllers.MagazzinoController;
import interfaces.ILoadView;
import interfaces.IUpdateView;
import miscellaneous.RefreshableListModel;

public class ArticoloDialog extends JDialog implements ILoadView, IUpdateView {

	private MagazzinoController controller;
	
	private JPanel leftPanel, rightPanel, qtyPanel;
	private JScrollPane typeScroll;
	private RefreshableListModel<String> tipiModel;
	private JList<String> tipiList; 
	private JLabel productionDateLabel, unicodeLabel, qtyLabel, priceLabel;
	private JDateChooser dateChooser;
	private JTextField unicodeTextField;
	private JSpinner priceSpinner;
	private JButton createArticoloButton;
	
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
		tipiModel = new RefreshableListModel<>(controller.getTipiInContainer());
        tipiList = new JList<>(tipiModel);
        tipiList.setPreferredSize(new Dimension(245, 200));
        tipiList.setVisibleRowCount(-1);
		((DefaultListCellRenderer)tipiList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        typeScroll = new JScrollPane(tipiList);
        productionDateLabel = new JLabel("Production Date", JLabel.CENTER);
        dateChooser = new JDateChooser();
        unicodeLabel = new JLabel("Unicode", JLabel.CENTER);
        unicodeTextField = new JTextField();
        priceLabel = new JLabel("Price", JLabel.CENTER);
        SpinnerNumberModel priceModel = new SpinnerNumberModel(0.1, 0.1, 999999.99, 0.01);
        priceSpinner = new JSpinner(priceModel);
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
		rightPanel.add(dateChooser, constr);
		
		constr.gridy = 2;
		constr.insets = new Insets(10, 5, 5 , 5);
		rightPanel.add(unicodeLabel, constr);
		
		constr.gridy = 3;
		constr.insets = new Insets(5, 5, 5, 5);
		rightPanel.add(unicodeTextField, constr);
		
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridy = 4;
		constr.insets = new Insets(10, 5, 5, 5);
		rightPanel.add(priceLabel, constr);
		
		constr.gridy = 5;
		constr.insets = new Insets(5, 5, 5, 5);
		rightPanel.add(priceSpinner, constr);
		
		constr.gridy = 6;
		constr.insets = new Insets(20, 5, 5, 5);
		createArticoloButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (unicodeTextField.getText() == null || !controller.isArticoloNew(unicodeTextField.getText())) {
					return;
				}
				if (dateChooser.getCalendar().after(Calendar.getInstance().getTime())) {
					return;
				}
				if (tipiList.isSelectionEmpty()) {
					return;
				}
				try {
					priceSpinner.commitEdit();
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				
				controller.createArticoloButtonActionPerformed(e, ArticoloDialog.this, tipiList.getSelectedValue(), unicodeTextField.getText(), 0, (Double)priceSpinner.getValue(), dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
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
		tipiModel.refreshList(controller.getTipiInContainer());
		tipiList.updateUI();
	}
}
