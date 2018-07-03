package main.java.components;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import main.java.controllers.MagazzinoController;
import main.java.interfaces.IUpdateView;
import main.java.miscellaneous.RefreshableListModel;
import main.java.models.Articolo;
import main.java.models.Ordine;
import main.java.models.Uscita;
import main.java.renderers.ArticoloRenderer;
import main.java.renderers.UscitaRenderer;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;

public class UscitaDialog extends JDialog implements IUpdateView {

	private final JPanel contentPanel = new JPanel();
	private RefreshableListModel<Articolo> articoliModel;
	private RefreshableListModel<Uscita> usciteModel;
	private JList<Articolo> articoliList;
	private JList<Uscita> usciteList;
	
	private JTextField unicodeField, negozioField, spedizioniereField;
	private JDateChooser dataChooser;
	private JSpinner qtySpinner;
	
	private final MagazzinoController controller;
	private String linkedOrdine = "";

	public UscitaDialog(MagazzinoController controller) {
		
		setResizable(false);
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 700, 600);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 694, 565);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		this.controller = controller;
		this.controller.addWindowToList(this);
		setTitle("Magazzino - " + this.controller.getUserFullName());
		
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
		articoliScroll.setToolTipText("articoli");
		articoliScroll.setBounds(15, 65, 225, 400);
		contentPanel.add(articoliScroll);
		
		articoliModel = new RefreshableListModel<>(controller.getArticoliInMagazzino());
		articoliList = new JList<>(articoliModel);
		articoliList.setToolTipText("articoli");
		articoliList.setCellRenderer(new ArticoloRenderer());
		articoliList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!linkedOrdine.equals("")) {
					linkedOrdine = "";
				}
			}
		});
		articoliScroll.setViewportView(articoliList);
		
		JButton linkOrdineButton = new JButton("Link Ordine");
		linkOrdineButton.setToolTipText("collega ordine");
		linkOrdineButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		linkOrdineButton.setBounds(15, 490, 225, 35);
		linkOrdineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					LinkDialog dialog = controller.linkOrdineButtonActionPerformed(UscitaDialog.this);
					dialog.setVisible(true);
			}
		});
		contentPanel.add(linkOrdineButton);
		
		JLabel unicodeLabel = new JLabel("Unicode");
		unicodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		unicodeLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		unicodeLabel.setBounds(290, 40, 100, 25);
		contentPanel.add(unicodeLabel);
		
		unicodeField = new JTextField();
		unicodeField.setToolTipText("unicode uscita");
		unicodeField.setColumns(10);
		unicodeField.setBounds(265, 80, 150, 25);
		contentPanel.add(unicodeField);
		
		JSeparator unicodeSeparator = new JSeparator();
		unicodeSeparator.setForeground(Color.BLUE);
		unicodeSeparator.setBackground(Color.BLACK);
		unicodeSeparator.setBounds(265, 65, 150, 1);
		contentPanel.add(unicodeSeparator);
		
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
		
		dataChooser = new JDateChooser();
		dataChooser.setToolTipText("data uscita");
		dataChooser.setBounds(265, 170, 150, 25);
		contentPanel.add(dataChooser);
		
		JLabel qtyLabel = new JLabel("Qty");
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
		qtySpinner = new JSpinner(model);
		qtySpinner.setToolTipText("quantit\u00E0 articolo uscente");
		qtySpinner.setBounds(265, 260, 150, 25);
		contentPanel.add(qtySpinner);
		
		JLabel negozioLabel = new JLabel("Negozio");
		negozioLabel.setHorizontalAlignment(SwingConstants.CENTER);
		negozioLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		negozioLabel.setBounds(290, 310, 100, 25);
		contentPanel.add(negozioLabel);
		
		JSeparator negozioSeparator = new JSeparator();
		negozioSeparator.setForeground(Color.BLUE);
		negozioSeparator.setBackground(Color.BLACK);
		negozioSeparator.setBounds(265, 335, 150, 1);
		contentPanel.add(negozioSeparator);
		
		negozioField = new JTextField();
		negozioField.setToolTipText("negozio richiedente");
		negozioField.setColumns(10);
		negozioField.setBounds(265, 350, 150, 25);
		negozioField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				if (!linkedOrdine.equals("")) {
					linkedOrdine.equals("");
				}
			}
			@Override
			public void insertUpdate(DocumentEvent e) {}
			@Override
			public void removeUpdate(DocumentEvent e) {}
			
		});
		contentPanel.add(negozioField);
		
		JLabel spedizioniereLabel = new JLabel("Spedizioniere");
		spedizioniereLabel.setHorizontalAlignment(SwingConstants.CENTER);
		spedizioniereLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		spedizioniereLabel.setBounds(290, 400, 100, 25);
		contentPanel.add(spedizioniereLabel);
		
		JSeparator spedizioniereSeparator = new JSeparator();
		spedizioniereSeparator.setForeground(Color.BLUE);
		spedizioniereSeparator.setBackground(Color.BLACK);
		spedizioniereSeparator.setBounds(265, 425, 150, 1);
		contentPanel.add(spedizioniereSeparator);
		
		spedizioniereField = new JTextField();
		spedizioniereField.setToolTipText("spedizioniere incaricato");
		spedizioniereField.setColumns(10);
		spedizioniereField.setBounds(265, 440, 150, 25);
		contentPanel.add(spedizioniereField);
		
		JLabel usciteLabel = new JLabel("Uscite");
		usciteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usciteLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		usciteLabel.setBounds(445, 15, 225, 25);
		contentPanel.add(usciteLabel);
		
		JSeparator usciteSeparator = new JSeparator();
		usciteSeparator.setForeground(Color.BLUE);
		usciteSeparator.setBackground(Color.BLACK);
		usciteSeparator.setBounds(445, 40, 225, 1);
		contentPanel.add(usciteSeparator);
		
		JScrollPane usciteScroll = new JScrollPane();
		usciteScroll.setToolTipText("lista ordini da aggiungere");
		usciteScroll.setBounds(445, 65, 225, 400);
		contentPanel.add(usciteScroll);
		
		usciteModel = new RefreshableListModel<Uscita>(new ArrayList<Uscita>());
		usciteList = new JList<>(usciteModel);
		usciteList.setToolTipText("uscite da confermare");
		usciteList.setCellRenderer(new UscitaRenderer());
		usciteList.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					usciteModel.removeElement(usciteList.getSelectedValue());
					usciteList.updateUI();
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
		usciteScroll.setViewportView(usciteList);
		
		JButton addToListButton = new JButton("Add To List");
		addToListButton.setToolTipText("add to uscite list");
		addToListButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		addToListButton.setBounds(255, 500, 175, 35);
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
				if (unicodeField.getText() == null || !controller.isIngressoNew(unicodeField.getText() + articoliList.getSelectedValue().getUnicode())) {
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
				if (negozioField.getText() == null || !controller.isNegozioCorrect(negozioField.getText())) {
					if (!negozioLabel.getForeground().equals(Color.RED)) {
						negozioLabel.setForeground(Color.RED);
					}
					check = true;
				}
				if ((spedizioniereField.getText() == null)) {
					if (!spedizioniereLabel.getForeground().equals(Color.RED)) {
						spedizioniereLabel.setForeground(Color.RED);
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
				if (dataLabel.getForeground().equals(Color.RED)) {
					dataLabel.setForeground(Color.BLACK);
				}
				if (negozioLabel.getForeground().equals(Color.RED)) {
					negozioLabel.setForeground(Color.BLACK);
				}
				if (spedizioniereLabel.getForeground().equals(Color.RED)) {
					spedizioniereLabel.setForeground(Color.BLACK);
				}
				contentPanel.updateUI();
				Articolo articolo = articoliList.getSelectedValue().clone();
				articolo.setQty((Integer)qtySpinner.getValue());
				Uscita uscita = controller.addUscitaToListButtonActionPerformed(dataChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						unicodeLabel.getText(),
						articolo,
						negozioField.getText(),
						spedizioniereField.getText(),
						linkedOrdine,
						articolo.getQty());
				if (!usciteModel.getList().contains(uscita)) {
					usciteModel.addElement(uscita);
					usciteList.updateUI();
				}
				
			}
		});
		contentPanel.add(addToListButton);
		
		JButton createUsciteButton = new JButton("Create");
		createUsciteButton.setToolTipText("crea uscite");
		createUsciteButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		createUsciteButton.setBounds(445, 480, 225, 35);
		contentPanel.add(createUsciteButton);
	}
	
	public void linkOrdine(String ordine, Articolo articolo, String negozio) {
		linkedOrdine = ordine;
		articoliList.setSelectedValue(articolo, true);
		negozioField.setText(negozio);
		contentPanel.updateUI();
	}
	
	public void update(Object obj) {
		if (obj instanceof Articolo) {
			articoliModel.addElement((Articolo)obj);
			articoliList.updateUI();
		}
	}
}
