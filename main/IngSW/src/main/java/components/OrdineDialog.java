package main.java.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import main.java.controllers.ResponsabileController;
import main.java.interfaces.ILoadView;
import main.java.interfaces.IUpdateView;
import main.java.miscellaneous.RefreshableListModel;
import main.java.models.Articolo;
import main.java.models.Ordine;
import main.java.renderers.ArticoloRenderer;
import main.java.renderers.OrdineRenderer;

public class OrdineDialog extends JDialog implements ILoadView, IUpdateView {

	private ResponsabileController controller;
	
	private final JPanel contentPanel = new JPanel();
	private RefreshableListModel<Articolo> articoliModel;
	private RefreshableListModel<Ordine> ordiniModel;
	private JList<Articolo> articoliList;
	private JList<Ordine> ordiniList;

	public OrdineDialog(ResponsabileController controller) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 690, 575);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 684, 540);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		this.controller = controller;
		this.controller.addWindowToList(this);
		setTitle("Responsabile - " + this.controller.getUserFullName());
		
		load();
		initComponents();
		
	}
	
	private void initComponents() {
		JLabel articoliLabel = new JLabel("Articoli");
		articoliLabel.setHorizontalAlignment(SwingConstants.CENTER);
		articoliLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		articoliLabel.setBounds(15, 15, 225, 25);
		contentPanel.add(articoliLabel);

		JSeparator articoliSeparator = new JSeparator();
		articoliSeparator.setForeground(Color.BLUE);
		articoliSeparator.setBackground(Color.BLACK);
		articoliSeparator.setBounds(15, 40, 225, 1);
		contentPanel.add(articoliSeparator);

		JScrollPane articoliScroll = new JScrollPane();
		articoliScroll.setBounds(15, 65, 225, 400);
		contentPanel.add(articoliScroll);

		articoliModel = new RefreshableListModel<>(controller.getArticoliInMagazzino());
		articoliList = new JList<>(articoliModel);
		articoliList.setToolTipText("lista articoli");
		articoliList.setCellRenderer(new ArticoloRenderer());
		articoliScroll.setViewportView(articoliList);

		JLabel unicodeLabel = new JLabel("Unicode");
		unicodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		unicodeLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		unicodeLabel.setBounds(290, 40, 100, 25);
		contentPanel.add(unicodeLabel);

		JTextField unicodeField = new JTextField();
		unicodeField.setToolTipText("unicode ordine");
		unicodeField.setColumns(10);
		unicodeField.setBounds(265, 80, 150, 25);
		contentPanel.add(unicodeField);

		JSeparator unicodeSeparator = new JSeparator();
		unicodeSeparator.setForeground(Color.BLUE);
		unicodeSeparator.setBackground(Color.BLACK);
		unicodeSeparator.setBounds(265, 65, 150, 1);
		contentPanel.add(unicodeSeparator);

		JLabel dateLabel = new JLabel("Data");
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		dateLabel.setBounds(290, 130, 100, 25);
		contentPanel.add(dateLabel);

		JSeparator dateSeparator = new JSeparator();
		dateSeparator.setForeground(Color.BLUE);
		dateSeparator.setBackground(Color.BLACK);
		dateSeparator.setBounds(265, 155, 150, 1);
		contentPanel.add(dateSeparator);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setToolTipText("data ordine");
		dateChooser.setBounds(265, 170, 150, 25);
		contentPanel.add(dateChooser);

		JLabel qtyLabel = new JLabel("Qty");
		qtyLabel.setToolTipText("");
		qtyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qtyLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		qtyLabel.setBounds(290, 220, 100, 25);
		contentPanel.add(qtyLabel);

		JSeparator qtySeparator = new JSeparator();
		qtySeparator.setForeground(Color.BLUE);
		qtySeparator.setBackground(Color.BLACK);
		qtySeparator.setBounds(265, 245, 150, 1);
		contentPanel.add(qtySeparator);

		SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 999999, 1);
		JSpinner qtySpinner = new JSpinner(model);
		qtySpinner.setToolTipText("quantit\u00E0 ordine");
		qtySpinner.setBounds(265, 260, 150, 25);
		contentPanel.add(qtySpinner);

		JButton addToListButton = new JButton("Add To List");
		addToListButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		addToListButton.setBounds(255, 345, 175, 35);
		addToListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean check = false;
				if (articoliList.isSelectionEmpty()) {
					if (!articoliLabel.getForeground().equals(Color.RED)) {
						articoliLabel.setForeground(Color.RED);
					}
					check = true;
				}
				if (check) {
					return;
				}
				if (unicodeField.getText() == null || controller.isOrdineNew(unicodeField.getText() + articoliList.getSelectedValue().getUnicode())) {
					if (!unicodeLabel.getForeground().equals(Color.RED)) {
						unicodeLabel.setForeground(Color.RED);
					}
					check = true;
				}
				if (dateChooser.getDate() == null || dateChooser.getDate().after(Calendar.getInstance().getTime())) {
					if (!dateLabel.getForeground().equals(Color.RED)) {
						dateLabel.setForeground(Color.RED);
					}
					check = true;
				}
				try {
					qtySpinner.commitEdit();
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				if (check) {
					return;
				}
				if (articoliLabel.getForeground().equals(Color.RED)) {
					articoliLabel.setForeground(Color.BLACK);
				}
				if (unicodeLabel.getForeground().equals(Color.RED)) {
					unicodeLabel.setForeground(Color.BLACK);
				}
				if (dateLabel.getForeground().equals(Color.RED)) {
					dateLabel.setForeground(Color.BLACK);
				}
				contentPanel.updateUI();
				Articolo articolo = articoliList.getSelectedValue().clone();
				articolo.setQty((Integer)qtySpinner.getValue());
				Ordine ordine = controller.addOrdineToListButtonActionPerformed(unicodeField.getText(),
						dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						articolo,
						articolo.getPrice() * articolo.getQty());
				if (!ordiniModel.getList().contains(ordine)) {
					ordiniModel.addElement(ordine);
					ordiniList.updateUI();
				}
			}
		});
		contentPanel.add(addToListButton);

		JLabel ordiniLabel = new JLabel("Ordini");
		ordiniLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ordiniLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		ordiniLabel.setBounds(445, 15, 225, 25);
		contentPanel.add(ordiniLabel);

		JSeparator ordiniSeparator = new JSeparator();
		ordiniSeparator.setForeground(Color.BLUE);
		ordiniSeparator.setBackground(Color.BLACK);
		ordiniSeparator.setBounds(445, 40, 225, 1);
		contentPanel.add(ordiniSeparator);

		JScrollPane ordiniScroll = new JScrollPane();
		ordiniScroll.setToolTipText("lista ordini da aggiungere");
		ordiniScroll.setBounds(445, 65, 225, 400);
		contentPanel.add(ordiniScroll);

		ordiniModel = new RefreshableListModel<>(new ArrayList<Ordine>());
		ordiniList = new JList<>(ordiniModel);
		ordiniList.setCellRenderer(new OrdineRenderer());
		ordiniList.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					ordiniModel.removeElement(ordiniList.getSelectedValue());
					ordiniList.updateUI();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		ordiniScroll.setViewportView(ordiniList);

		JButton createOrdiniButton = new JButton("Create");
		createOrdiniButton.setToolTipText("create ordini");
		createOrdiniButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		createOrdiniButton.setBounds(445, 480, 225, 35);
		createOrdiniButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.createOrdiniButtonActionPerformed(ordiniModel.getList());
				dispose();
			}
		});
		contentPanel.add(createOrdiniButton);
	}
	
	@Override
	public void load() {
		controller.loadArticoli();
		controller.loadOrdini();
	}
	
	@Override
	public void update(Object obj) {
		if (obj instanceof Articolo) {
			articoliModel.addElement((Articolo)obj);
			articoliList.updateUI();
		}
		if (obj instanceof List) {
			List<Object> list = (List<Object>)obj;
			if (list.get(0) instanceof Ordine) {
				ordiniModel.addAll((List<Ordine>)obj);
				ordiniList.updateUI();
			}
		}
		
	}
}
