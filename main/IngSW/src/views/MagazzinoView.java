package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import components.IngressoDataDialog;
import components.IngressoDialog;
import containers.Magazzino;
import controllers.MagazzinoController;
import interfaces.IJobView;
import models.Ingresso;

public class MagazzinoView extends JFrame implements IJobView {
	
	private JPanel titlePanel, contentPanel;
	private JLabel titleLabel, ingressiLabel, articoliLabel, usciteLabel;
	private JList ingressiList, articoliList, usciteList;
	private JScrollPane ingressiScroll, articoliScroll, usciteScroll;
	private JButton ingressiButton, usciteButton;
	
	private MagazzinoController controller;
	
	public MagazzinoView(MagazzinoController controller) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));
        
        this.controller = controller;
        
        load();
        initComponents();
        buildView();
	}
	
	private void initComponents() {
		titlePanel = new JPanel(new GridBagLayout());
		contentPanel = new JPanel(new GridBagLayout());
		titleLabel = new JLabel("Magazzino", JLabel.CENTER);
		ingressiLabel = new JLabel("Ingressi", JLabel.CENTER);
		articoliLabel = new JLabel("Articoli", JLabel.CENTER);
		usciteLabel = new JLabel("Uscite", JLabel.CENTER);
		ingressiList = new JList<>(controller.getMagazzino().getIngressi().keySet().toArray());
		ingressiList.setPreferredSize(new Dimension(245, 200));
		ingressiList.setVisibleRowCount(-1);
		((DefaultListCellRenderer)ingressiList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		articoliList = new JList<>(controller.getMagazzino().getArticoli().keySet().toArray());
		articoliList.setPreferredSize(new Dimension(245, 200));
		articoliList.setVisibleRowCount(-1);
		((DefaultListCellRenderer)articoliList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		usciteList = new JList<>(controller.getMagazzino().getUscite().keySet().toArray());
		usciteList.setPreferredSize(new Dimension(245, 200));
		usciteList.setVisibleRowCount(-1);
		((DefaultListCellRenderer)usciteList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		ingressiScroll = new JScrollPane(ingressiList);
		articoliScroll = new JScrollPane(articoliList);
		usciteScroll = new JScrollPane(usciteList);
		ingressiButton = new JButton("Add Ingresso");
		usciteButton = new JButton("Add Uscita");
	}
	
	private void buildView() {
		GridBagConstraints titleConstr = new GridBagConstraints();
		GridBagConstraints contentConstr = new GridBagConstraints();
		
		ingressiList.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JList<String> list = (JList<String>)e.getSource();
					IngressoDataDialog dialog = controller.ingressiListItemPressed(list.getSelectedValue());
					dialog.setVisible(true);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {}
		
		});
		
		titleLabel.setFont(new Font("Serif", Font.PLAIN, 36));
		titleConstr.fill = GridBagConstraints.HORIZONTAL;
		titleConstr.ipady = 40;
		titleConstr.weightx = 0.0;
		titleConstr.gridwidth = 3;
		titleConstr.gridx = 0;
		titleConstr.gridy = 1;
		titleConstr.insets = new Insets(5, 0, 0, 0);
		titlePanel.add(titleLabel, titleConstr);
		
		ingressiLabel.setFont(new Font("Serif", Font.BOLD, 16));
		contentConstr.fill = GridBagConstraints.HORIZONTAL;
		contentConstr.weightx = 1/3D;
		contentConstr.gridx = 0;
		contentConstr.gridy = 0;
		contentConstr.weighty = 0.15;
		contentConstr.insets = new Insets(5, 0, 0, 0);
		contentPanel.add(ingressiLabel, contentConstr);
		
		contentConstr.fill = GridBagConstraints.BOTH;
		contentConstr.gridy = 1;
		contentConstr.weighty = 0.65;
		contentConstr.insets = new Insets(10, 0, 0, 0);
		contentPanel.add(ingressiScroll, contentConstr);
		
		contentConstr.fill = GridBagConstraints.HORIZONTAL;
		contentConstr.gridy = 5;
		contentConstr.weighty = 0.2;
		contentConstr.insets = new Insets(10, 0, 0, 0);
		ingressiButton.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				IngressoDialog dialog = controller.ingressiButtonActionPerformed(e);
				dialog.setVisible(true);
			}
		});
		contentPanel.add(ingressiButton, contentConstr);
		
		articoliLabel.setFont(new Font("Serif", Font.BOLD, 16));
		contentConstr.gridx = 1;
		contentConstr.gridy = 0;
		contentConstr.weighty = 0.15;
		contentConstr.insets = new Insets(5, 0, 0, 0);
		contentPanel.add(articoliLabel, contentConstr);
		
		contentConstr.fill = GridBagConstraints.BOTH;
		contentConstr.gridy = 1;
		contentConstr.weighty = 0.65;
		contentConstr.insets = new Insets(10, 0, 0, 0);
		contentPanel.add(articoliScroll, contentConstr);
		
		usciteLabel.setFont(new Font("Serif", Font.BOLD, 16));
		contentConstr.fill = GridBagConstraints.HORIZONTAL;
		contentConstr.gridx = 2;
		contentConstr.gridy = 0;
		contentConstr.weighty = 0.15;
		contentConstr.insets = new Insets(5, 0, 0, 0);
		contentPanel.add(usciteLabel, contentConstr);
		
		contentConstr.fill = GridBagConstraints.BOTH;
		contentConstr.gridy = 1;
		contentConstr.weighty = 0.65;
		contentConstr.insets = new Insets(10, 0, 0, 0);
		contentPanel.add(usciteScroll, contentConstr);
		
		contentConstr.fill = GridBagConstraints.HORIZONTAL;
		contentConstr.gridy = 5;
		contentConstr.weighty = 0.2;
		contentConstr.insets = new Insets(10, 0, 0, 0);
		contentPanel.add(usciteButton, contentConstr);
		
		add(titlePanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		
		pack();
	}

	@Override
	public void load() {
		controller.loadIngressi(this);
		controller.loadArticoli(this);
		controller.loadUscite(this);
	}
	
	@Override
	public void update() {
		ingressiList.updateUI();
		articoliList.updateUI();
		usciteList.updateUI();
	}
}