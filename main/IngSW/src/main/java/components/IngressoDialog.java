package main.java.components;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import main.java.controllers.MagazzinoController;
import main.java.interfaces.IUpdateView;
import main.java.miscellaneous.RefreshableListModel;
import main.java.models.Articolo;
import main.java.models.Ingresso;
import main.java.renderers.ArticoloRenderer;
import main.java.renderers.IngressoRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

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

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.Color;
import javax.swing.JScrollPane;

public class IngressoDialog extends JDialog implements IUpdateView {

	private final JPanel contentPanel = new JPanel();
	private RefreshableListModel<Articolo> articoliModel;
	private RefreshableListModel<Ingresso> ingressiModel;
	private JList<Articolo> articoliList;
	private JList<Ingresso> ingressiList;
	
	private final MagazzinoController controller;

	public IngressoDialog(MagazzinoController controller) {
		
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
		articoliScroll.setViewportView(articoliList);
		
		JLabel unicodeLabel = new JLabel("Unicode");
		unicodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		unicodeLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		unicodeLabel.setBounds(290, 40, 100, 25);
		contentPanel.add(unicodeLabel);

		JTextField unicodeField = new JTextField();
		unicodeField.setToolTipText("unicode ingresso");
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
		dateChooser.setToolTipText("data ingresso");
		dateChooser.setBounds(265, 170, 150, 25);
		contentPanel.add(dateChooser);

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
		JSpinner qtySpinner = new JSpinner(model);
		qtySpinner.setToolTipText("quantit\u00E0 articolo entrante");
		qtySpinner.setBounds(265, 260, 150, 25);
		contentPanel.add(qtySpinner);
		
		JLabel mensolaLabel = new JLabel("Mensola");
		mensolaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mensolaLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		mensolaLabel.setBounds(290, 310, 100, 25);
		contentPanel.add(mensolaLabel);

		JSeparator mensolaSeparator = new JSeparator();
		mensolaSeparator.setForeground(Color.BLUE);
		mensolaSeparator.setBackground(Color.BLACK);
		mensolaSeparator.setBounds(265, 335, 150, 1);
		contentPanel.add(mensolaSeparator);

		model = new SpinnerNumberModel(1, 1, 999, 1);
		JSpinner mensolaSpinner = new JSpinner(model);
		mensolaSpinner.setToolTipText("numero mensola");
		mensolaSpinner.setBounds(265, 350, 150, 25);
		contentPanel.add(mensolaSpinner);


		JButton addToListButton = new JButton("Add To List");
		addToListButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		addToListButton.setBounds(255, 410, 175, 35);
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
				if (dateChooser.getDate() == null || dateChooser.getDate().after(Calendar.getInstance().getTime())) {
					if (!dateLabel.getForeground().equals(Color.RED)) {
						dateLabel.setForeground(Color.RED);
					}
					check = true;
				}
				try {
					qtySpinner.commitEdit();
					mensolaSpinner.commitEdit();
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
				Ingresso ingresso = controller.addIngressoToListButtonActionPerformed(unicodeField.getText(), dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						articolo,
						articolo.getQty(),
						(Integer)mensolaSpinner.getValue());
				if (!ingressiModel.getList().contains(ingresso)) {
					ingressiModel.addElement(ingresso);
					ingressiList.updateUI();
				}
			}
		});
		contentPanel.add(addToListButton);

		JLabel ingressiLabel = new JLabel("Ingressi");
		ingressiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ingressiLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		ingressiLabel.setBounds(445, 15, 225, 25);
		contentPanel.add(ingressiLabel);

		JSeparator ingressiSeparator = new JSeparator();
		ingressiSeparator.setForeground(Color.BLUE);
		ingressiSeparator.setBackground(Color.BLACK);
		ingressiSeparator.setBounds(445, 40, 225, 1);
		contentPanel.add(ingressiSeparator);

		JScrollPane ingressiScroll = new JScrollPane();
		ingressiScroll.setToolTipText("lista ordini da aggiungere");
		ingressiScroll.setBounds(445, 65, 225, 400);
		contentPanel.add(ingressiScroll);
		
		ingressiModel = new RefreshableListModel<>(new ArrayList<Ingresso>());
		ingressiList = new JList<>(ingressiModel);
		ingressiList.setToolTipText("ingressi da confermare");
		ingressiList.setCellRenderer(new IngressoRenderer());
		ingressiList.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					ingressiModel.removeElement(ingressiList.getSelectedValue());
					ingressiList.updateUI();
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
		ingressiScroll.setViewportView(ingressiList);

		JButton createIngressiButton = new JButton("Create");
		createIngressiButton.setToolTipText("crea ingressi");
		createIngressiButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		createIngressiButton.setBounds(445, 480, 225, 35);
		createIngressiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.createIngressiButtonActionPerformed(ingressiModel.getList());
				dispose();
			}
		});
		contentPanel.add(createIngressiButton);
		
		JButton addArticoloButton = new JButton("Add Articolo");
		addArticoloButton.setToolTipText("add articolo");
		addArticoloButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		addArticoloButton.setBounds(15, 480, 225, 35);
		addArticoloButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArticoloDialog dialog = controller.addArticoloButtonActionPerformed();
				dialog.setVisible(true);
			}
		});
		contentPanel.add(addArticoloButton);
	}
	
	@Override
	public void update(Object obj) {
		if (obj instanceof Articolo) {
			articoliModel.addElement((Articolo)obj);
			articoliList.updateUI();
		}
	}
}
