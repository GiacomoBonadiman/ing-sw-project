package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import models.Uscita;

public class UscitaDialog extends JDialog implements ILoadView, IUpdateView {

	private MagazzinoController controller;
	
	private JPanel leftPanel, centerPanel, rightPanel;
	private JScrollPane dialogArticoliScroll, dialogUsciteScroll;
	private RefreshableListModel<String> dialogArticoliModel, dialogUsciteModel;
	private JList<String> dialogArticoliList, dialogUsciteList;
	private JDateChooser uscitaDateChooser;
	private JLabel bollaLabel, negozioLabel, dateLabel, spedizioniereLabel, qtyLabel;
	private JTextField bollaTextField, negozioTextField, spedizioniereTextField;
	private JButton addToListButton, createUscitaButton, linkOrdineButton;
	private JSpinner qtySpinner;
	
	private final List<String> tempBollaUscite = new ArrayList<String>();
	private final List<Uscita> tempUscite = new ArrayList<Uscita>();
	private String selectedOrd;

	public UscitaDialog(MagazzinoController controller) {
		super();
	
		setAlwaysOnTop(true);
        setModal(true);
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        setPreferredSize(new Dimension(800, 500));
        setSize(new Dimension(800, 500));
        
		this.controller = controller;
		controller.addWindowToList(this);
		
        
        load();
		initComponents();
		buildLayout();
		
	}
	
	private void initComponents() {
		leftPanel = new JPanel(new GridBagLayout());
		centerPanel = new JPanel(new GridBagLayout());
		rightPanel = new JPanel(new GridBagLayout());
        dialogArticoliModel = new RefreshableListModel<>(controller.getUsciteInMagazzino());
        dialogArticoliList = new JList<>(dialogArticoliModel);
        dialogArticoliList.setPreferredSize(new Dimension(245, 300));
        dialogArticoliList.setVisibleRowCount(-1);
        ((DefaultListCellRenderer)dialogArticoliList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        dialogArticoliScroll = new JScrollPane(dialogArticoliList);
        linkOrdineButton = new JButton("Link Ordine");
        dialogUsciteModel = new RefreshableListModel<>(tempBollaUscite.toArray(new String[0]));
        dialogUsciteList = new JList<>(dialogUsciteModel);
        dialogUsciteList.setPreferredSize(new Dimension(245, 300));
        dialogUsciteList.setVisibleRowCount(-1);
        ((DefaultListCellRenderer)dialogUsciteList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        dialogUsciteScroll = new JScrollPane(dialogUsciteList);
        uscitaDateChooser = new JDateChooser();
        addToListButton = new JButton("Add to List");
        bollaLabel = new JLabel("Bolla", JLabel.CENTER);
        bollaTextField = new JTextField();
        negozioLabel = new JLabel("Negozio", JLabel.CENTER);
        negozioTextField = new JTextField();
        dateLabel = new JLabel("Data", JLabel.CENTER);
        qtyLabel = new JLabel("Qty", JLabel.CENTER);
        SpinnerNumberModel qtyModel = new SpinnerNumberModel(1, 1, 999999, 1);
		qtySpinner = new JSpinner(qtyModel);
        createUscitaButton = new JButton("Create Uscita");
        spedizioniereLabel = new JLabel("Spedizioniere", JLabel.CENTER);
        spedizioniereTextField = new JTextField();
	}
	
	private void buildLayout() {
		GridBagConstraints constr = new GridBagConstraints();
        
		dialogArticoliList.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JList<String> list = (JList<String>)e.getSource();
					ArticoloDataDialog dialog = controller.articoliListItemPressed(list.getSelectedValue());
					dialog.setVisible(true);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {}
		
		});
		
		dialogUsciteList.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JList<String> list = (JList<String>)e.getSource();
					UscitaDataDialog dialog = controller.tempUsciteListItemPressed(list.getSelectedValue(), tempUscite);
					dialog.setVisible(true);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {}
		
		});
		
        constr.fill = GridBagConstraints.BOTH;
        constr.weighty = 0.75;
        constr.insets = new Insets(5, 0, 0, 0);
        leftPanel.add(dialogArticoliScroll, constr);
        
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.gridy = 1;
        constr.weighty = 0.25;
        constr.insets = new Insets(10, 0, 0, 0);
        linkOrdineButton.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
        leftPanel.add(linkOrdineButton, constr);
        
        dateLabel.setFont(new Font("Serif", Font.BOLD, 16));
        constr.gridy = 0;
        constr.weighty = 0.10;
        constr.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(dateLabel, constr);
        
        constr.gridy = 1;
        centerPanel.add(uscitaDateChooser, constr);
        
        bollaLabel.setFont(new Font("Serif", Font.BOLD, 16));
        constr.gridy = 2;
        constr.insets = new Insets(10, 0, 0, 0);
        centerPanel.add(bollaLabel, constr);
        
        constr.gridy = 3;
        constr.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(bollaTextField, constr);
        
        negozioLabel.setFont(new Font("Serif", Font.BOLD, 16));
        constr.gridy = 4;
        constr.insets = new Insets(10, 0, 0, 0);
        centerPanel.add(negozioLabel, constr);
        
        constr.gridy = 5;
        constr.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(negozioTextField, constr);
        
        constr.gridy = 6;
        constr.insets = new Insets(10, 0, 0, 0);
        centerPanel.add(spedizioniereLabel, constr);
        
        constr.gridy = 7;
        constr.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(spedizioniereTextField, constr);
        
        constr.gridy = 8;
        constr.insets = new Insets(10, 0, 0, 0);
        centerPanel.add(qtyLabel, constr);
        
        constr.gridy = 9;
        constr.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(qtySpinner, constr);
        
        constr.gridy = 10;
        constr.weighty = 0.20;
        constr.insets = new Insets(20, 0, 0, 0);
        addToListButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (dialogArticoliList.isSelectionEmpty()) {
        			return;
        		}
        		if (bollaTextField.getText() == null || !controller.isUscitaNew(bollaTextField.getText())) {
        			return;
        		}
        		if (uscitaDateChooser.getCalendar().after(Calendar.getInstance().getTime())) {
        			return;
        		}
        		if (negozioTextField.getText() == null || !controller.isNegozioCorrect(negozioTextField.getText())) {
        			return;
        		}
        		if (spedizioniereTextField.getText() == null) {
        			return;
        		}
        		try {
        			qtySpinner.commitEdit();
        		} catch (ParseException ex) {
        			ex.printStackTrace();
        		}
        		if (!controller.verifyEnoughArticoli(dialogArticoliList.getSelectedValue(), (Integer)qtySpinner.getValue())) {
        			return;
        		}
        		
        		if (tempBollaUscite.contains(bollaTextField.getText() + dialogArticoliList.getSelectedValue())) {
        			return;
        		}
        		tempBollaUscite.add(bollaTextField.getText() + dialogArticoliList.getSelectedValue());
        		tempUscite.add(controller.addUscitaToListButtonActionPerformed(uscitaDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), bollaTextField.getText() + dialogArticoliList.getSelectedValue(), dialogArticoliList.getSelectedValue(), negozioTextField.getText(), spedizioniereTextField.getText(), selectedOrd, (Integer)qtySpinner.getValue()));
        		update();
        	}
        });
        centerPanel.add(addToListButton, constr);
        
        constr.fill = GridBagConstraints.BOTH;
        constr.gridy = 0;
        constr.weighty = 0.75;
        constr.insets = new Insets(5, 0, 0, 0);
        rightPanel.add(dialogUsciteScroll, constr);
        
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.gridy = 1;
        constr.weighty = 0.25;
        constr.insets = new Insets(10, 0, 0, 0);
        createUscitaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.createUsciteButtonActionPerformed(tempUscite);
				dispose();
			}
        	
        });
        rightPanel.add(createUscitaButton, constr);
        
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        
        pack();
	}
	
	public void load() {
		controller.loadNegozi();
	}
	
	public void update() {
		dialogArticoliModel.refreshList(controller.getArticoliInMagazzino());
		dialogArticoliList.updateUI();
		dialogUsciteModel.refreshList(tempBollaUscite.toArray(new String[0]));
		dialogUsciteList.updateUI();
	}
}
