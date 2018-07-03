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
import main.java.models.Tipo;

public class TipoRenderer extends JPanel implements ListCellRenderer<Tipo> {

	private JLabel uninameLabel = new JLabel();
	private JLabel descriptionLabel = new JLabel();
	private JLabel sportLabel = new JLabel();
	private JLabel materialsLabel = new JLabel();
	
	public TipoRenderer() {
		setLayout(new BorderLayout(5, 5));
		
		JPanel textPanel = new JPanel(new GridLayout(0, 1));
		textPanel.add(uninameLabel);
		textPanel.add(descriptionLabel);
		textPanel.add(sportLabel);
		textPanel.add(materialsLabel);
		
		add(textPanel, BorderLayout.CENTER);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Tipo> list, Tipo tipo, int index, boolean isSelected, boolean cellHasFocus) {
		uninameLabel.setText(tipo.getUniname());
		descriptionLabel.setText(tipo.getDescription());
		sportLabel.setText(tipo.getSport());
		materialsLabel.setText(tipo.getMaterials());
		
		uninameLabel.setOpaque(true);
		descriptionLabel.setOpaque(true);
		sportLabel.setOpaque(true);
		materialsLabel.setOpaque(true);
		
		if (isSelected) {
			uninameLabel.setBackground(list.getSelectionBackground());
			descriptionLabel.setBackground(list.getSelectionBackground());
			sportLabel.setBackground(list.getSelectionBackground());
			materialsLabel.setBackground(list.getSelectionBackground());
		} else {
			uninameLabel.setBackground(list.getBackground());
			descriptionLabel.setBackground(list.getBackground());
			sportLabel.setBackground(list.getBackground());
			materialsLabel.setBackground(list.getBackground());
		}
		
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		
		return this;
	}
}

