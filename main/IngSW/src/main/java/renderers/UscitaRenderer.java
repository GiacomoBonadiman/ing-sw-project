package main.java.renderers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import main.java.models.Uscita;

public class UscitaRenderer extends JPanel implements ListCellRenderer<Uscita> {

	private JLabel dateLabel = new JLabel();
	private JLabel bollaLabel = new JLabel();
	private JLabel articoloLabel = new JLabel();
	private JLabel qtyLabel = new JLabel();
	private JLabel negozioLabel = new JLabel();
	private JLabel spedizioniereLabel = new JLabel();
	private JLabel ordLabel = new JLabel();
	
	public UscitaRenderer() {
		setLayout(new BorderLayout(5, 5));
		
		JPanel textPanel = new JPanel(new GridLayout(0, 1));
		textPanel.add(dateLabel);
		textPanel.add(bollaLabel);
		textPanel.add(articoloLabel);
		textPanel.add(qtyLabel);
		textPanel.add(negozioLabel);
		textPanel.add(spedizioniereLabel);
		textPanel.add(ordLabel);
		
		add(textPanel, BorderLayout.CENTER);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Uscita> list, Uscita uscita, int index, boolean isSelected, boolean cellHasFocus) {
		dateLabel.setText(uscita.getUscDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		bollaLabel.setText("Bolla " + uscita.getUscBolla());
		articoloLabel.setText("Articolo " + uscita.getArticolo().getUnicode());
		qtyLabel.setText("Qty " + uscita.getArticolo().getQty());
		negozioLabel.setText("Negozio " + uscita.getNegozio().getCodFiscale());
		spedizioniereLabel.setText("Spedizioniere " + uscita.getSpedizioniere());
		ordLabel.setText("Ordine " + uscita.getOrdUnicode());
		
		dateLabel.setOpaque(true);
		bollaLabel.setOpaque(true);
		articoloLabel.setOpaque(true);
		qtyLabel.setOpaque(true);
		negozioLabel.setOpaque(true);
		spedizioniereLabel.setOpaque(true);
		ordLabel.setOpaque(true);
		
		if (isSelected) {
			dateLabel.setBackground(list.getSelectionBackground());
			bollaLabel.setBackground(list.getSelectionBackground());
			articoloLabel.setBackground(list.getSelectionBackground());
			qtyLabel.setBackground(list.getSelectionBackground());
			negozioLabel.setBackground(list.getSelectionBackground());
			spedizioniereLabel.setBackground(list.getSelectionBackground());
			ordLabel.setBackground(list.getSelectionBackground());
		} else {
			dateLabel.setBackground(list.getBackground());
			bollaLabel.setBackground(list.getBackground());
			articoloLabel.setBackground(list.getBackground());
			qtyLabel.setBackground(list.getBackground());
			negozioLabel.setBackground(list.getBackground());
			spedizioniereLabel.setBackground(list.getBackground());
			ordLabel.setBackground(list.getBackground());
		}
		
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		
		return this;
	}
}
