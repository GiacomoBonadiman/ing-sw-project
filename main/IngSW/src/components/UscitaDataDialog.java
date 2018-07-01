package components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.format.DateTimeFormatter;

import javax.swing.JDialog;
import javax.swing.JLabel;

import controllers.MagazzinoController;
import models.Uscita;

public class UscitaDataDialog extends JDialog {

	private MagazzinoController controller;
	private Uscita uscita;
	
	private JLabel articoloTitleLabel, articoloContentLabel, bollaTitleLabel, bollaContentLabel, dateTitleLabel,
				   dateContentLabel, negozioTitleLabel, negozioContentLabel, spedizioniereTitleLabel, spedizioniereContentLabel,
				   qtyTitleLabel, qtyContentLabel;

	public UscitaDataDialog(MagazzinoController controller, Uscita uscita) {
		super();
		setLayout(new GridBagLayout());
		setAlwaysOnTop(true);
		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setPreferredSize(new Dimension(500, 400));
		setSize(new Dimension(500, 400));
		
		this.controller = controller;
		this.uscita = uscita;
		
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
		add(bollaTitleLabel, constr);
		
		constr.gridx = 1;
		add(bollaContentLabel, constr);
		
		constr.gridx = 2;
		constr.gridy = 0;
		constr.insets = new Insets(5, 10, 5, 5);
		add(dateTitleLabel, constr);
		
		constr.gridx = 3;
		add(dateContentLabel, constr);
		
		constr.gridx = 2;
		constr.gridy = 1;
		add(negozioTitleLabel, constr);
		
		constr.gridx = 3;
		add(negozioContentLabel, constr);
		
		constr.gridx = 4;
		constr.gridy = 0;
		add(spedizioniereTitleLabel, constr);
		
		constr.gridx = 5;
		add(spedizioniereContentLabel, constr);
		
		pack();
	}

	private void initComponents() {
		articoloTitleLabel = new JLabel("Articolo: ", JLabel.CENTER);
        articoloContentLabel = new JLabel(uscita.getArticolo().getUnicode() + " - " + uscita.getArticolo().getType().getUniname(), JLabel.CENTER);
        bollaTitleLabel = new JLabel("Bolla: ", JLabel.CENTER);
        bollaContentLabel = new JLabel(uscita.getUscBolla(), JLabel.CENTER);
        dateTitleLabel = new JLabel("Data: ", JLabel.CENTER);
        dateContentLabel = new JLabel(uscita.getUscDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")), JLabel.CENTER);
        negozioTitleLabel = new JLabel("Negozio: ", JLabel.CENTER);
        negozioContentLabel = new JLabel(uscita.getNegozio().getCodFiscale() + " - " + uscita.getNegozio().getNome(), JLabel.CENTER);
        spedizioniereTitleLabel = new JLabel("Spedizioniere: ", JLabel.CENTER);
        spedizioniereContentLabel = new JLabel(uscita.getSpedizioniere(), JLabel.CENTER);
	}
}
