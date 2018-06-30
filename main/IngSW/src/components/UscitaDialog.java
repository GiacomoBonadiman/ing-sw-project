package components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import containers.Magazzino;
import controllers.MagazzinoController;

public class UscitaDialog extends JDialog {

	private MagazzinoController controller;
	
	private JDialog uscitaDialog;
	private JScrollPane dialogArticoliScroll;
	private JList dialogArticoliList;
	private JXDatePicker uscitaDatePicker;
	private JLabel uscBolla, negozioLabel, dateLabel, spedizioniereLabel;
	private JTextField uscBollaTextField, negozioTextField, spedizioniereTextField;
	private JButton articoloButton, createUscitaButton;

	public UscitaDialog(MagazzinoController controller) {
		super();
		uscitaDialog.setAlwaysOnTop(true);
        uscitaDialog.setModal(true);
        uscitaDialog.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        uscitaDialog.setPreferredSize(new Dimension(600, 400));
        uscitaDialog.setSize(new Dimension(600, 400));
        
		initComponents();
		buildLayout();
		
		this.controller = controller;
	}
	
	private void initComponents() {
		uscitaDialog = new JDialog();
        dialogArticoliScroll = new JScrollPane();
        dialogArticoliList = new JList<>();
        uscitaDatePicker = new JXDatePicker();
        uscBolla = new JLabel();
        uscBollaTextField = new JTextField();
        negozioLabel = new JLabel();
        negozioTextField = new JTextField();
        articoloButton = new JButton();
        dateLabel = new JLabel();
        createUscitaButton = new JButton();
        spedizioniereLabel = new JLabel();
        spedizioniereTextField = new JTextField();
	}
	
	private void buildLayout() {
		dialogArticoliList.setModel(new AbstractListModel<String>() {
            String[] strings = (String[]) Magazzino.getInstance().getArticoli().keySet().toArray();
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        dialogArticoliScroll.setViewportView(dialogArticoliList);
		
        uscBolla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        uscBolla.setText("Bolla");

        uscBollaTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        uscBollaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uscBollaTextFieldActionPerformed(evt);
            }
        });

        negozioLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        negozioLabel.setText("Negozio");

        negozioTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        negozioTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                negozioTextFieldActionPerformed(evt);
            }
        });

        articoloButton.setText("Add Articolo");
        articoloButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.articoloButtonActionPerformed(evt).setVisible(true);
            }
        });

        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateLabel.setText("Date");

        createUscitaButton.setText("Create");
        createUscitaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUscitaButtonActionPerformed(evt);
            }
        });

        spedizioniereLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        spedizioniereLabel.setText("Spedizioniere");

        spedizioniereTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spedizioniereTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spedizioniereTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout uscitaDialogLayout = new javax.swing.GroupLayout(uscitaDialog.getContentPane());
        uscitaDialog.getContentPane().setLayout(uscitaDialogLayout);
        uscitaDialogLayout.setHorizontalGroup(
            uscitaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uscitaDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dialogArticoliScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(articoloButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(uscitaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spedizioniereTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addGroup(uscitaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(spedizioniereLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uscitaDatePicker, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(uscBolla, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uscBollaTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(negozioLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(negozioTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createUscitaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        uscitaDialogLayout.setVerticalGroup(
            uscitaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uscitaDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(uscitaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(uscitaDialogLayout.createSequentialGroup()
                        .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uscitaDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uscBolla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uscBollaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(negozioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(negozioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spedizioniereLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spedizioniereTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(uscitaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(articoloButton, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(createUscitaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(dialogArticoliScroll))
                .addContainerGap())
        );
	}

	protected void spedizioniereTextFieldActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	protected void createUscitaButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	protected void negozioTextFieldActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	protected void uscBollaTextFieldActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
