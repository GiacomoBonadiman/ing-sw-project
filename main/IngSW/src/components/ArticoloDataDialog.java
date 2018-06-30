package components;

import java.awt.Dialog.ModalExclusionType;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class ArticoloDataDialog extends JDialog {
	
	private JLabel articoloTitleLabel, articoloContentLabel, typeTitleLabel, typeContentLabel, productionDateTitleLabel,
				   productionDateContentLabel, qtyTitleLabel, qtyContentLabel, prezzoTitleLabel, prezzoContentLabel;

	public ArticoloDataDialog() {
		super();
		setAlwaysOnTop(true);
		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		
		initComponents();
		buildLayout();
	}

	private void buildLayout() {
		articoloTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        articoloTitleLabel.setText("Unicode:");

        articoloContentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        typeTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        typeTitleLabel.setText("Type:");

        typeContentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        productionDateTitleLabel.setText("Production Date:");

        productionDateContentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        qtyTitleLabel.setText("Qty:");

        qtyContentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        prezzoTitleLabel.setText("Prezzo:");

        prezzoContentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout articoloDataDialogLayout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(articoloDataDialogLayout);
        articoloDataDialogLayout.setHorizontalGroup(
            articoloDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(articoloDataDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(articoloDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(articoloDataDialogLayout.createSequentialGroup()
                        .addComponent(productionDateTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productionDateContentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                    .addGroup(articoloDataDialogLayout.createSequentialGroup()
                        .addComponent(typeTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(typeContentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(articoloDataDialogLayout.createSequentialGroup()
                        .addGroup(articoloDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(qtyTitleLabel)
                            .addComponent(articoloTitleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(articoloContentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(articoloDataDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(qtyContentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(articoloDataDialogLayout.createSequentialGroup()
                        .addComponent(prezzoTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prezzoContentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        articoloDataDialogLayout.setVerticalGroup(
            articoloDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(articoloDataDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(articoloDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(articoloTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(articoloContentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(articoloDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeContentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(articoloDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productionDateTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productionDateContentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(articoloDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qtyTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qtyContentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(articoloDataDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prezzoTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(prezzoContentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
	}

	private void initComponents() {
		articoloTitleLabel = new JLabel();
        articoloContentLabel = new JLabel();
        typeTitleLabel = new JLabel();
        typeContentLabel = new JLabel();
        productionDateTitleLabel = new JLabel();
        productionDateContentLabel = new JLabel();
        qtyTitleLabel = new JLabel();
        qtyContentLabel = new JLabel();
        prezzoTitleLabel = new JLabel();
        prezzoContentLabel = new JLabel();
	}
}
