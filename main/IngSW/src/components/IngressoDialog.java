package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXDatePicker;

import containers.Magazzino;
import controllers.MagazzinoController;
import interfaces.IJobView;

public class IngressoDialog extends JDialog implements IJobView {

	private MagazzinoController controller;
	
	private JPanel leftPanel, centerPanel, rightPanel;
	private JScrollPane dialogArticoliScroll, dialogIngressiScroll;
	private JList dialogArticoliList, dialogIngressiList;
	private JButton articoloButton, createIngressoButton, addToListButton;
	private JXDatePicker ingressoDatePicker;
	private JLabel unicodeLabel, mensolaLabel, dateLabel;
	private JTextField unicodeTextField, mensolaTextField;
	
	public IngressoDialog(MagazzinoController controller) {
		super();
		setAlwaysOnTop(true);
		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setPreferredSize(new Dimension(800, 400));
		setSize(new Dimension(800, 400));
		this.controller = controller;
		
		initComponents();
		buildView();
	}
	
	private void initComponents() {
		leftPanel = new JPanel(new GridBagLayout());
		centerPanel = new JPanel(new GridBagLayout());
		rightPanel = new JPanel(new GridBagLayout());
        dialogArticoliList = new JList<>(Magazzino.getInstance().getArticoli().keySet().toArray());
        dialogArticoliList.setPreferredSize(new Dimension(245, 300));
        dialogArticoliList.setVisibleRowCount(-1);
        ((DefaultListCellRenderer)dialogArticoliList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        dialogArticoliScroll = new JScrollPane(dialogArticoliList);
        dialogIngressiList = new JList<>();
        dialogIngressiList.setPreferredSize(new Dimension(245, 300));
        dialogIngressiList.setVisibleRowCount(-1);
        ((DefaultListCellRenderer)dialogIngressiList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        dialogIngressiScroll = new JScrollPane(dialogIngressiList);
        dateLabel = new JLabel("Date", JLabel.CENTER);
        ingressoDatePicker = new JXDatePicker();
        unicodeLabel = new JLabel("Unicode", JLabel.CENTER);
        unicodeTextField = new JTextField();
        mensolaLabel = new JLabel("Mensola", JLabel.CENTER);
        mensolaTextField = new JTextField();
        articoloButton = new JButton("Add Articolo");
        createIngressoButton = new JButton("Create");
        addToListButton = new JButton("Add To List");
	}
	
	private void buildView() {
        GridBagConstraints constr = new GridBagConstraints();
        
        constr.fill = GridBagConstraints.BOTH;
        constr.weighty = 0.75;
        constr.insets = new Insets(5, 0, 0, 0);
        leftPanel.add(dialogArticoliScroll, constr);
        
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.gridy = 1;
        constr.weighty = 0.25;
        constr.insets = new Insets(10, 0, 0, 0);
        articoloButton.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArticoloDialog dialog = controller.articoloButtonActionPerformed(e);
				dialog.setVisible(true);
			}
		});
        leftPanel.add(articoloButton, constr);
        
        dateLabel.setFont(new Font("Serif", Font.BOLD, 16));
        constr.gridy = 0;
        constr.weighty = 0.10;
        constr.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(dateLabel, constr);
        
        constr.gridy = 1;
        centerPanel.add(ingressoDatePicker, constr);
        
        unicodeLabel.setFont(new Font("Serif", Font.BOLD, 16));
        constr.gridy = 2;
        constr.insets = new Insets(10, 0, 0, 0);
        centerPanel.add(unicodeLabel, constr);
        
        constr.gridy = 3;
        constr.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(unicodeTextField, constr);
        
        mensolaLabel.setFont(new Font("Serif", Font.BOLD, 16));
        constr.gridy = 4;
        constr.insets = new Insets(10, 0, 0, 0);
        centerPanel.add(mensolaLabel, constr);
        
        constr.gridy = 5;
        constr.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(mensolaTextField, constr);
        
        constr.gridy = 6;
        constr.weighty = 0.20;
        constr.insets = new Insets(20, 0, 0, 0);
        centerPanel.add(addToListButton, constr);
        
        constr.fill = GridBagConstraints.BOTH;
        constr.gridy = 0;
        constr.weighty = 0.75;
        constr.insets = new Insets(5, 0, 0, 0);
        rightPanel.add(dialogIngressiScroll, constr);
        
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.gridy = 1;
        constr.weighty = 0.25;
        constr.insets = new Insets(10, 0, 0, 0);
        rightPanel.add(createIngressoButton, constr);
        
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        
        pack();
	}
	
	@Override
	public void load() {}
	
	@Override
	public void update() {
		dialogArticoliList.updateUI();
		dialogIngressiList.updateUI();
	}
}
