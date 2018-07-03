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

import main.java.models.Ingresso;

public class IngressoRenderer extends JPanel implements ListCellRenderer<Ingresso> {

	private JLabel unicodeLabel = new JLabel();
	private JLabel dataLabel = new JLabel();
	private JLabel articoloLabel = new JLabel();
	private JLabel qtyLabel = new JLabel();
	
	public IngressoRenderer() {
		setLayout(new BorderLayout(5, 5));
		
		JPanel textPanel = new JPanel(new GridLayout(0, 1));
		textPanel.add(unicodeLabel);
		textPanel.add(dataLabel);
		textPanel.add(articoloLabel);
		textPanel.add(qtyLabel);
		
		add(textPanel, BorderLayout.CENTER);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Ingresso> list, Ingresso ingresso, int index, boolean isSelected, boolean cellHasFocus) {
		unicodeLabel.setText("Codice " + ingresso.getIngrUnicode());
		dataLabel.setText(ingresso.getIngrDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		articoloLabel.setText("Articolo " + ingresso.getArticolo().getUnicode());
		qtyLabel.setText("Qty " + ingresso.getArticolo().getQty());
		
		unicodeLabel.setOpaque(true);
		dataLabel.setOpaque(true);
		articoloLabel.setOpaque(true);
		qtyLabel.setOpaque(true);
		
		if (isSelected) {
			unicodeLabel.setBackground(list.getSelectionBackground());
			dataLabel.setBackground(list.getSelectionBackground());
			articoloLabel.setBackground(list.getSelectionBackground());
			qtyLabel.setBackground(list.getSelectionBackground());
		} else {
			unicodeLabel.setBackground(list.getBackground());
			dataLabel.setBackground(list.getBackground());
			articoloLabel.setBackground(list.getBackground());
			qtyLabel.setBackground(list.getBackground());
		}
		
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		
		return this;
	}
}
