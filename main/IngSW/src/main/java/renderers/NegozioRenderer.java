package main.java.renderers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import main.java.models.Negozio;

public class NegozioRenderer extends JPanel implements ListCellRenderer<Negozio> {

	private JLabel codFiscaleLabel = new JLabel();
	private JLabel nomeLabel = new JLabel();
	private JLabel indirizzoLabel = new JLabel();
	private JLabel cittaLabel = new JLabel();
	
	public NegozioRenderer() {
		setLayout(new BorderLayout(5, 5));
		
		JPanel textPanel = new JPanel(new GridLayout(0, 1));
		textPanel.add(codFiscaleLabel);
		textPanel.add(nomeLabel);
		textPanel.add(indirizzoLabel);
		textPanel.add(cittaLabel);
		
		add(textPanel, BorderLayout.CENTER);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Negozio> list, Negozio negozio, int index, boolean isSelected, boolean cellHasFocus) {
		codFiscaleLabel.setText("CF " + negozio.getCodFiscale());
		nomeLabel.setText(negozio.getNome());
		indirizzoLabel.setText(negozio.getIndirizzo());
		cittaLabel.setText(negozio.getCitta());
		
		codFiscaleLabel.setOpaque(true);
		nomeLabel.setOpaque(true);
		indirizzoLabel.setOpaque(true);
		cittaLabel.setOpaque(true);
		
		if (isSelected) {
			codFiscaleLabel.setBackground(list.getSelectionBackground());
			nomeLabel.setBackground(list.getSelectionBackground());
			indirizzoLabel.setBackground(list.getSelectionBackground());
			cittaLabel.setBackground(list.getSelectionBackground());
		} else {
			codFiscaleLabel.setBackground(list.getBackground());
			nomeLabel.setBackground(list.getBackground());
			indirizzoLabel.setBackground(list.getBackground());
			cittaLabel.setBackground(list.getBackground());
		}
		
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		
		return this;
	}
}

