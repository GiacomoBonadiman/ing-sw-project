package main.java.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.controllers.MagazzinoController;
import main.java.interfaces.ILoadView;
import main.java.miscellaneous.RefreshableListModel;
import main.java.models.Ordine;
import main.java.renderers.OrdineRenderer;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class LinkDialog extends JDialog implements ILoadView {

	private final JPanel contentPanel = new JPanel();
	private RefreshableListModel<Ordine> ordiniModel;
	private JList<Ordine> ordiniList;
	
	private final MagazzinoController controller;
	private final UscitaDialog dialog;

	public LinkDialog(MagazzinoController controller, UscitaDialog dialog) {
		setResizable(false);
		
		setBounds(100, 100, 600, 600);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 582, 553);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		this.controller = controller;
		this.dialog = dialog;
		setTitle("Magazzino - " + controller.getUserFullName());
		
		initComponents();
		
	}
	
	private void initComponents() {
			JLabel ordiniLabel = new JLabel("Ordini");
			ordiniLabel.setHorizontalAlignment(SwingConstants.CENTER);
			ordiniLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
			ordiniLabel.setBounds(175, 15, 225, 25);
			contentPanel.add(ordiniLabel);

			JSeparator ordiniSeparator = new JSeparator();
			ordiniSeparator.setForeground(Color.BLUE);
			ordiniSeparator.setBackground(Color.BLACK);
			ordiniSeparator.setBounds(175, 40, 225, 1);
			contentPanel.add(ordiniSeparator);

			JScrollPane ordiniScroll = new JScrollPane();
			ordiniScroll.setToolTipText("articoli");
			ordiniScroll.setBounds(144, 65, 292, 400);
			contentPanel.add(ordiniScroll);

			ordiniModel = new RefreshableListModel<>(controller.getNotDispatchedOrdiniInContainer());
			ordiniList = new JList<>(ordiniModel);
			ordiniList.setToolTipText("ordini da evadere");
			ordiniList.setCellRenderer(new OrdineRenderer());
			ordiniScroll.setViewportView(ordiniList);

			JButton linkOrdineButton = new JButton("Link");
			linkOrdineButton.setToolTipText("collega ordine");
			linkOrdineButton.setFont(new Font("Cambria", Font.PLAIN, 18));
			linkOrdineButton.setBounds(144, 490, 292, 35);
			linkOrdineButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (ordiniList.isSelectionEmpty()) {
						if (!ordiniLabel.getForeground().equals(Color.RED)) {
							ordiniLabel.setForeground(Color.RED);
						}
						return;
					}
					if (ordiniLabel.getForeground().equals(Color.RED)) {
						ordiniLabel.setForeground(Color.BLACK);
					}
					Ordine ordine = ordiniList.getSelectedValue();
					dialog.linkOrdine(ordine.getOrdUnicode(), ordine.getArticolo(), ordine.getNegozio().getCodFiscale());
					dispose();
				}
			});
			contentPanel.add(linkOrdineButton);
	}
	
	@Override
	public void load() {
		controller.loadOrdini();
	}
}
