package components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.format.DateTimeFormatter;

import javax.swing.JDialog;
import javax.swing.JLabel;

import controllers.MagazzinoController;
import models.Articolo;

public class ArticoloDataDialog extends JDialog {
	
	private MagazzinoController controller;
	private Articolo articolo;
	
	private JLabel articoloTitleLabel, articoloContentLabel, tipoTitleLabel, tipoContentLabel, productionDateTitleLabel,
				   productionDateContentLabel, qtyTitleLabel, qtyContentLabel, priceTitleLabel, priceContentLabel;

	public ArticoloDataDialog(MagazzinoController controller, Articolo articolo) {
		super();
		setLayout(new GridBagLayout());
		setAlwaysOnTop(true);
		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setPreferredSize(new Dimension(400, 400));
		setSize(new Dimension(400, 400));
		
		this.controller = controller;
		this.articolo = articolo;
		
		initComponents();
		buildView();
	}

	private void buildView() {
		GridBagConstraints constr = new GridBagConstraints();
        
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 0.25;
		constr.weighty = 0.25;
		constr.insets = new Insets(5, 5, 5, 5);
		add(articoloTitleLabel, constr);
		
		constr.gridx = 1;
		add(articoloContentLabel, constr);
		
		constr.gridx = 0;
		constr.gridy = 1;
		add(tipoTitleLabel, constr);
		
		constr.gridx = 1;
		add(tipoContentLabel, constr);
		
		constr.gridx = 2;
		constr.gridy = 0;
		constr.insets = new Insets(5, 10, 5, 5);
		add(productionDateTitleLabel, constr);
		
		constr.gridx = 3;
		add(productionDateContentLabel, constr);
		
		constr.gridx = 2;
		constr.gridy = 1;
		add(qtyTitleLabel, constr);
		
		constr.gridx = 3;
		add(qtyContentLabel, constr);
		
		constr.gridx = 4;
		constr.gridy = 0;
		add(priceTitleLabel, constr);
		
		constr.gridx = 5;
		add(priceContentLabel, constr);
		
		pack();
	}

	private void initComponents() {
		articoloTitleLabel = new JLabel("Articolo:", JLabel.CENTER);
        articoloContentLabel = new JLabel(articolo.getUnicode(), JLabel.CENTER);
        tipoTitleLabel = new JLabel("Tipo:", JLabel.CENTER);
        tipoContentLabel = new JLabel(articolo.getType().getUniname(), JLabel.CENTER);
        productionDateTitleLabel = new JLabel("Data Produzione:", JLabel.CENTER);
        productionDateContentLabel = new JLabel(articolo.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")), JLabel.CENTER);
        qtyTitleLabel = new JLabel("Qty:", JLabel.CENTER);
        qtyContentLabel = new JLabel(Integer.toString(articolo.getQty()), JLabel.CENTER);
        priceTitleLabel = new JLabel("Prezzo:", JLabel.CENTER);
        priceContentLabel = new JLabel(Double.toString(articolo.getPrice()), JLabel.CENTER);
	}
}
