package components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JDialog;
import javax.swing.JLabel;

import containers.Magazzino;
import controllers.MagazzinoController;
import models.Ingresso;

public class IngressoDataDialog extends JDialog {

	private JLabel articoloTitleLabel, articoloContentLabel, unicodeTitleLabel, unicodeContentLabel, 
				   dateTitleLabel, dateContentLabel, mensolaTitleLabel, mensolaContentLabel;
	
	private MagazzinoController controller;
	private Ingresso selectedIngresso;

	public IngressoDataDialog(MagazzinoController controller, Ingresso selectedIngresso) {
		super();
		setLayout(new GridBagLayout());
		setAlwaysOnTop(true);
		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setPreferredSize(new Dimension(400, 400));
		setSize(new Dimension(400, 400));
		
		this.controller = controller;
		this.selectedIngresso = selectedIngresso;
		
		initComponents();
		buildLayout();
	}

	private void buildLayout() {
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
		add(unicodeTitleLabel, constr);
		
		constr.gridx = 1;
		add(unicodeContentLabel, constr);
		
		constr.gridx = 2;
		constr.gridy = 0;
		constr.insets = new Insets(5, 10, 5, 5);
		add(dateTitleLabel, constr);
		
		constr.gridx = 3;
		add(dateContentLabel, constr);
		
		constr.gridx = 2;
		constr.gridy = 1;
		add(mensolaTitleLabel, constr);
		
		constr.gridx = 3;
		add(mensolaContentLabel, constr);
		
		pack();
	}

	private void initComponents() {
		articoloTitleLabel = new JLabel("Articolo:", JLabel.CENTER);
        articoloContentLabel = new JLabel(selectedIngresso.getArticolo().getUnicode() + " - " + selectedIngresso.getArticolo().getType().getUniname(), JLabel.CENTER);
        unicodeTitleLabel = new JLabel("Unicode:", JLabel.CENTER);
        unicodeContentLabel = new JLabel(selectedIngresso.getIngrUnicode(), JLabel.CENTER);
        dateTitleLabel = new JLabel("Date:", JLabel.CENTER);
        dateContentLabel = new JLabel(selectedIngresso.getIngrDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
        mensolaTitleLabel = new JLabel("Mensola:", JLabel.CENTER);
        mensolaContentLabel = new JLabel(Integer.toString(selectedIngresso.getNumMensola()));
	}
}
