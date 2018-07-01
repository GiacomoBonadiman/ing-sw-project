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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import components.ArticoloDataDialog;
import components.IngressoDataDialog;
import components.IngressoDialog;
import components.UscitaDataDialog;
import components.UscitaDialog;
import controllers.MagazzinoController;
import interfaces.ILoadView;
import interfaces.IUpdateView;
import miscellaneous.RefreshableListModel;

public class MagazzinoView extends JFrame implements ILoadView, IUpdateView {
	
	private JPanel titlePanel, contentPanel;
	private JLabel titleLabel, ingressiLabel, articoliLabel, usciteLabel;
	private RefreshableListModel<String> ingressiModel, articoliModel, usciteModel;
	private JList<String> ingressiList, articoliList, usciteList;
	private JScrollPane ingressiScroll, articoliScroll, usciteScroll;
	private JButton ingressiButton, usciteButton;
	
	private MagazzinoController controller;
	
	public MagazzinoView(MagazzinoController controller) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));
        
        this.controller = controller;
        this.controller.addWindowToList(this);
        
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
		ingressiModel = new RefreshableListModel<>(controller.getIngressiInMagazzino());
		ingressiList = new JList<>(ingressiModel);
		ingressiList.setPreferredSize(new Dimension(245, 200));
		ingressiList.setVisibleRowCount(-1);
		((DefaultListCellRenderer)ingressiList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		articoliModel = new RefreshableListModel<>(controller.getArticoliInMagazzino());
		articoliList = new JList<>(articoliModel);
		articoliList.setPreferredSize(new Dimension(245, 200));
		articoliList.setVisibleRowCount(-1);
		((DefaultListCellRenderer)articoliList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		usciteModel = new RefreshableListModel<>(controller.getUsciteInMagazzino());
		usciteList = new JList<>(usciteModel);
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
		
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent arg0) {}
			@Override
			public void windowClosed(WindowEvent e) {
				MagazzinoView view = (MagazzinoView)e.getSource();
				view.controller.saveAll();
			}
			@Override
			public void windowClosing(WindowEvent arg0) {}
			@Override
			public void windowDeactivated(WindowEvent arg0) {}
			@Override
			public void windowDeiconified(WindowEvent arg0) {}
			@Override
			public void windowIconified(WindowEvent arg0) {}
			@Override
			public void windowOpened(WindowEvent arg0) {}
		});
		
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
		
		usciteList.addMouseListener(new MouseListener() {

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
					UscitaDataDialog dialog = controller.usciteListItemPressed(list.getSelectedValue());
					dialog.setVisible(true);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {}
		
		});
		
		articoliList.addMouseListener(new MouseListener() {

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
					ArticoloDataDialog dialog = controller.articoliListItemPressed(list.getSelectedValue());
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
		usciteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UscitaDialog dialog = controller.usciteButtonActionPerformed(e);
				dialog.setVisible(true);
			}
			
		});
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
		ingressiModel.refreshList(controller.getIngressiInMagazzino());
		ingressiList.updateUI();
		articoliModel.refreshList(controller.getArticoliInMagazzino());
		articoliList.updateUI();
		usciteModel.refreshList(controller.getUsciteInMagazzino());
		usciteList.updateUI();
	}
}