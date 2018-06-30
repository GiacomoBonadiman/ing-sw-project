package components;

import java.awt.Dialog.ModalExclusionType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class UscitaDataDialog extends JDialog {

	private JLabel articoloTitleLabel, articoloContentLabel, bollaTitleLabel, bollaContentLabel, dateTitleLabel,
				   dateContentLabel, negozioTitleLabel, negozioContentLabel, spedizioniereTitleLabel, spedizioniereContentLabel;

	public UscitaDataDialog() {
		super();
		setAlwaysOnTop(true);
		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		
		initComponents();
		buildLayout();
	}

	private void buildLayout() {
        articoloTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        articoloTitleLabel.setText("Articolo:");

        articoloContentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        bollaTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bollaTitleLabel.setText("Bolla:");

        bollaContentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        dateTitleLabel.setText("Date:");

        dateContentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        negozioTitleLabel.setText("Negozio:");

        negozioContentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        spedizioniereTitleLabel.setText("Spedizioniere:");

        spedizioniereContentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout uscitaDataDialogLayout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(uscitaDataDialogLayout);
        uscitaDataDialogLayout.setHorizontalGroup(
            uscitaDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uscitaDataDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(uscitaDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(uscitaDataDialogLayout.createSequentialGroup()
                        .addGroup(uscitaDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uscitaDataDialogLayout.createSequentialGroup()
                                .addComponent(articoloTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))
                            .addGroup(uscitaDataDialogLayout.createSequentialGroup()
                                .addGroup(uscitaDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(negozioTitleLabel)
                                    .addComponent(dateTitleLabel)
                                    .addComponent(bollaTitleLabel))
                                .addGap(10, 10, 10)))
                        .addGroup(uscitaDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(negozioContentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateContentLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bollaContentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(articoloContentLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(uscitaDataDialogLayout.createSequentialGroup()
                        .addComponent(spedizioniereTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spedizioniereContentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))))
        );
        uscitaDataDialogLayout.setVerticalGroup(
            uscitaDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uscitaDataDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(uscitaDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(articoloTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(articoloContentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(uscitaDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bollaTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bollaContentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(uscitaDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateContentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(uscitaDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(negozioTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(negozioContentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(uscitaDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spedizioniereTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(spedizioniereContentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
	}

	private void initComponents() {
		articoloTitleLabel = new JLabel();
        articoloContentLabel = new JLabel();
        bollaTitleLabel = new JLabel();
        bollaContentLabel = new JLabel();
        dateTitleLabel = new JLabel();
        dateContentLabel = new JLabel();
        negozioTitleLabel = new JLabel();
        negozioContentLabel = new JLabel();
        spedizioniereTitleLabel = new JLabel();
        spedizioniereContentLabel = new JLabel();
	}
	
	public void setData(String articolo, String bolla, LocalDate date, String negozio, String spedizioniere) {
		articoloContentLabel.setText(articolo);
		bollaContentLabel.setText(bolla);
		dateContentLabel.setText(date.format(DateTimeFormatter.ofPattern("dd/MM/yy")));
		negozioContentLabel.setText(negozio);
		spedizioniereContentLabel.setText(spedizioniere);
	}
}
