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

import main.java.models.Ordine;

public class OrdineRenderer extends JPanel implements ListCellRenderer<Ordine> {

	private JLabel unicodeLabel = new JLabel();
	private JLabel negozioLabel = new JLabel();
	private JLabel dataLabel = new JLabel();
	private JLabel articoloLabel = new JLabel();
	private JLabel priceLabel = new JLabel();
	private JLabel statusLabel = new JLabel();
	
	public OrdineRenderer() {
		setLayout(new BorderLayout(5, 5));
		
		JPanel textPanel = new JPanel(new GridLayout(0, 1));
		textPanel.add(unicodeLabel);
		textPanel.add(negozioLabel);
		textPanel.add(dataLabel);
		textPanel.add(articoloLabel);
		textPanel.add(priceLabel);
		
		add(statusLabel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Ordine> list, Ordine ordine, int index, boolean isSelected, boolean cellHasFocus) {
		if (ordine.isDispatched()) {
			statusLabel.setText("Status: Evaso");
		} else {
			statusLabel.setText("Status: Attesa");
		}
		unicodeLabel.setText("Codice " + ordine.getOrdUnicode());
		negozioLabel.setText("Negozio " + ordine.getNegozio().getCodFiscale() + " - " + ordine.getNegozio().getNome());
		dataLabel.setText(ordine.getOrdDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		articoloLabel.setText("Articolo " + ordine.getArticolo().getUnicode());
		priceLabel.setText(Double.toString(ordine.getTotPrice()) + " " + Currency.getInstance("EUR").getSymbol());
		
		statusLabel.setOpaque(true);
		unicodeLabel.setOpaque(true);
		negozioLabel.setOpaque(true);
		dataLabel.setOpaque(true);
		articoloLabel.setOpaque(true);
		priceLabel.setOpaque(true);
		
		if (isSelected) {
			statusLabel.setBackground(list.getSelectionBackground());
			unicodeLabel.setBackground(list.getSelectionBackground());
			negozioLabel.setBackground(list.getSelectionBackground());
			dataLabel.setBackground(list.getSelectionBackground());
			articoloLabel.setBackground(list.getSelectionBackground());
			priceLabel.setBackground(list.getSelectionBackground());
		} else {
			statusLabel.setBackground(list.getBackground());
			unicodeLabel.setBackground(list.getBackground());
			negozioLabel.setBackground(list.getBackground());
			dataLabel.setBackground(list.getBackground());
			articoloLabel.setBackground(list.getBackground());
			priceLabel.setBackground(list.getBackground());
		}
		
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		
		return this;
	}
}
