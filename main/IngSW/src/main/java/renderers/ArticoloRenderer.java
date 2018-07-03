package main.java.renderers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import main.java.models.Articolo;

public class ArticoloRenderer extends JPanel implements ListCellRenderer<Articolo> {

	private JLabel tipoLabel = new JLabel();
	private JLabel unicodeLabel = new JLabel();
	private JLabel qtyLabel = new JLabel();
	private JLabel priceLabel = new JLabel();
	private JLabel dataLabel = new JLabel();
	
	
	public ArticoloRenderer() {
		setLayout(new BorderLayout(5, 5));
		
		JPanel textPanel = new JPanel(new GridLayout(0, 1));
		textPanel.add(tipoLabel);
		textPanel.add(unicodeLabel);
		textPanel.add(qtyLabel);
		textPanel.add(priceLabel);
		textPanel.add(dataLabel);
		
		add(textPanel, BorderLayout.CENTER);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Articolo> list, Articolo articolo, int index, boolean isSelected, boolean cellHasFocus) {
		tipoLabel.setText("Tipo " + articolo.getType().getUniname());
		unicodeLabel.setText("Codice " + articolo.getUnicode());
		qtyLabel.setText("Qty " + articolo.getQty());
		priceLabel.setText(articolo.getPrice() + " " + Currency.getInstance("EUR").getSymbol());
		dataLabel.setText("Produzione " + articolo.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		tipoLabel.setOpaque(true);
		unicodeLabel.setOpaque(true);
		qtyLabel.setOpaque(true);
		priceLabel.setOpaque(true);
		dataLabel.setOpaque(true);
		
		if (isSelected) {
			tipoLabel.setBackground(list.getSelectionBackground());
			unicodeLabel.setBackground(list.getSelectionBackground());
			qtyLabel.setBackground(list.getSelectionBackground());
			priceLabel.setBackground(list.getSelectionBackground());
			dataLabel.setBackground(list.getSelectionBackground());
		} else {
			tipoLabel.setBackground(list.getBackground());
			unicodeLabel.setBackground(list.getBackground());
			qtyLabel.setBackground(list.getBackground());
			priceLabel.setBackground(list.getBackground());
			dataLabel.setBackground(list.getBackground());
		}
		
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		
		return this;
	}
}
