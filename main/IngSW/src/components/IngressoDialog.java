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
import interfaces.IUpdateView;
import miscellaneous.RefreshableListModel;
import models.Ingresso;

public class IngressoDialog extends JDialog implements IUpdateView {

	private MagazzinoController controller;
	
	private JPanel leftPanel, centerPanel, rightPanel;
	private JScrollPane dialogArticoliScroll, dialogIngressiScroll;
	private RefreshableListModel<String> dialogArticoliModel, dialogIngressiModel;
	private JList<String> dialogArticoliList, dialogIngressiList;
	private JButton articoloButton, createIngressoButton, addToListButton;
	private JDateChooser ingressoDateChooser;
	private JLabel unicodeLabel, mensolaLabel, dateLabel, qtyLabel;
	private JTextField unicodeTextField;
	private JSpinner mensolaSpinner, qtySpinner;
	
	private final List<String> tempUnicodeIngressi = new ArrayList<>();
	private final List<Ingresso> tempIngressi = new ArrayList<>();
	
	public IngressoDialog(MagazzinoController controller) {
		super();
		setAlwaysOnTop(true);
		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setPreferredSize(new Dimension(800, 500));
		setSize(new Dimension(800, 500));
		
		this.controller = controller;
		this.controller.addWindowToList(this);
		
		initComponents();
		buildView();
	}
	
	private void initComponents() {
		leftPanel = new JPanel(new GridBagLayout());
		centerPanel = new JPanel(new GridBagLayout());
		rightPanel = new JPanel(new GridBagLayout());
		dialogArticoliModel = new RefreshableListModel<>(controller.getArticoliInMagazzino());
        dialogArticoliList = new JList<>(dialogArticoliModel);
        dialogArticoliList.setPreferredSize(new Dimension(245, 300));
        dialogArticoliList.setVisibleRowCount(-1);
        ((DefaultListCellRenderer)dialogArticoliList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        dialogArticoliScroll = new JScrollPane(dialogArticoliList);
        dialogIngressiModel = new RefreshableListModel<String>(tempUnicodeIngressi.toArray(new String[0]));
        dialogIngressiList = new JList<>(dialogIngressiModel);
        dialogIngressiList.setPreferredSize(new Dimension(245, 300));
        dialogIngressiList.setVisibleRowCount(-1);
        ((DefaultListCellRenderer)dialogIngressiList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        dialogIngressiScroll = new JScrollPane(dialogIngressiList);
        dateLabel = new JLabel("Date", JLabel.CENTER);
        ingressoDateChooser = new JDateChooser();
        unicodeLabel = new JLabel("Unicode", JLabel.CENTER);
        unicodeTextField = new JTextField();
        mensolaLabel = new JLabel("Mensola", JLabel.CENTER);
        qtyLabel = new JLabel("Qty", JLabel.CENTER);
        SpinnerNumberModel qtyModel = new SpinnerNumberModel(1, 1, 999999, 1);
		qtySpinner = new JSpinner(qtyModel);
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 999, 1);
        mensolaSpinner = new JSpinner(model);
        articoloButton = new JButton("Add Articolo");
        createIngressoButton = new JButton("Create");
        addToListButton = new JButton("Add To List");
	}
	
	private void buildView() {
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
        
        dialogIngressiList.addMouseListener(new MouseListener() {

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
					IngressoDataDialog dialog = controller.tempIngressiListItemPressed(list.getSelectedValue(), tempIngressi);
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
        articoloButton.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArticoloDialog dialog = controller.articoloButtonActionPerformed(e);
				dialog.setVisible(true);
			}
		});
        leftPanel.add(articoloButton, constr);
        
        dateLabel.setFont(new Font("Serif", Font.BOLD, 16));
        constr.gridy = 0;
        constr.weighty = 0.10;
        constr.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(dateLabel, constr);
        
        constr.gridy = 1;
        centerPanel.add(ingressoDateChooser, constr);
        
        unicodeLabel.setFont(new Font("Serif", Font.BOLD, 16));
        constr.gridy = 2;
        constr.insets = new Insets(10, 0, 0, 0);
        centerPanel.add(unicodeLabel, constr);
        
        constr.gridy = 3;
        constr.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(unicodeTextField, constr);
        
        mensolaLabel.setFont(new Font("Serif", Font.BOLD, 16));
        constr.gridy = 4;
        constr.insets = new Insets(10, 0, 0, 0);
        centerPanel.add(mensolaLabel, constr);
        
        constr.gridy = 5;
        constr.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(mensolaSpinner, constr);
        
        constr.gridy = 6;
        constr.insets = new Insets(10, 0, 0, 0);
        centerPanel.add(qtyLabel, constr);
        
        constr.gridy = 7;
        constr.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(qtySpinner, constr);
        
        constr.gridy = 8;
        constr.weighty = 0.20;
        constr.insets = new Insets(20, 0, 0, 0);
        addToListButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (dialogArticoliList.isSelectionEmpty()) {
        			return;
        		}
        		if (unicodeTextField.getText() == null || !controller.isIngressoNew(unicodeTextField.getText() + dialogArticoliList.getSelectedValue())) {
        			return;
        		}
        		if (ingressoDateChooser.getDate().after(Calendar.getInstance().getTime())) {
        			return;
        		}
        		try {
        			mensolaSpinner.commitEdit();
        			qtySpinner.commitEdit();
        		} catch (ParseException ex) {
        			ex.printStackTrace();
        		}
        		
        		if (tempUnicodeIngressi.contains(unicodeTextField.getText() + dialogArticoliList.getSelectedValue())) {
        			return;
        		}
        		tempUnicodeIngressi.add(unicodeTextField.getText() + dialogArticoliList.getSelectedValue());
        		tempIngressi.add(controller.addIngressoToListButtonActionPerformed(unicodeTextField.getText() + dialogArticoliList.getSelectedValue(), ingressoDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dialogArticoliList.getSelectedValue(), (Integer)mensolaSpinner.getValue(), (Integer)qtySpinner.getValue()));
        		update();
        	}
        });
        centerPanel.add(addToListButton, constr);
        
        constr.fill = GridBagConstraints.BOTH;
        constr.gridy = 0;
        constr.weighty = 0.75;
        constr.insets = new Insets(5, 0, 0, 0);
        rightPanel.add(dialogIngressiScroll, constr);
        
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.gridy = 1;
        constr.weighty = 0.25;
        constr.insets = new Insets(10, 0, 0, 0);
        createIngressoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.createIngressiButtonActionPerformed(tempIngressi);
				dispose();
			}
        	
        });
        rightPanel.add(createIngressoButton, constr);
        
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        
        pack();
	}
	
	@Override
	public void update() {
		dialogArticoliModel.refreshList(controller.getArticoliInMagazzino());
		dialogArticoliList.updateUI();
		dialogIngressiModel.refreshList(tempUnicodeIngressi.toArray(new String[0]));
		dialogIngressiList.updateUI();
	}
}
