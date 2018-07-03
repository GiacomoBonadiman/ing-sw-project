package main.java.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.components.IngressoDialog;
import main.java.components.UscitaDialog;
import main.java.controllers.MagazzinoController;
import main.java.interfaces.ILoadView;
import main.java.interfaces.IUpdateView;
import main.java.miscellaneous.RefreshableListModel;
import main.java.models.Articolo;
import main.java.models.Ingresso;
import main.java.models.Uscita;
import main.java.renderers.ArticoloRenderer;
import main.java.renderers.IngressoRenderer;
import main.java.renderers.UscitaRenderer;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class MagazzinoView extends JFrame implements ILoadView, IUpdateView {
	
	private JPanel contentPane;
	private RefreshableListModel<Ingresso> ingressiModel;
	private RefreshableListModel<Articolo> articoliModel;
	private RefreshableListModel<Uscita> usciteModel;
	private JList<Ingresso> ingressiList;
	private JList<Articolo> articoliList;
	private JList<Uscita> usciteList;
	
	private final MagazzinoController controller;

	public MagazzinoView(MagazzinoController controller) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.controller = controller;
		this.controller.addWindowToList(this);
		setTitle("Magazzino - " + this.controller.getUserFullName());
		
		load();
		initComponent();
	}
	
	private void initComponent() {
		JLabel ingressiLabel = new JLabel("Ingressi");
		ingressiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ingressiLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		ingressiLabel.setBounds(15, 15, 225, 25);
		contentPane.add(ingressiLabel);

		JSeparator ingressiSeparator = new JSeparator();
		ingressiSeparator.setForeground(Color.BLUE);
		ingressiSeparator.setBackground(Color.BLACK);
		ingressiSeparator.setBounds(15, 40, 225, 1);
		contentPane.add(ingressiSeparator);
		
		JScrollPane ingressiScroll = new JScrollPane();
		ingressiScroll.setToolTipText("ingressi");
		ingressiScroll.setBounds(15, 65, 225, 400);
		contentPane.add(ingressiScroll);
		
		ingressiModel = new RefreshableListModel<>(controller.getIngressiInMagazzino());
		ingressiList = new JList<>(ingressiModel);
		ingressiList.setToolTipText("ingressi");
		ingressiList.setCellRenderer(new IngressoRenderer());
		ingressiScroll.setViewportView(ingressiList);
		
		JLabel articoliLabel = new JLabel("Articoli");
		articoliLabel.setHorizontalAlignment(SwingConstants.CENTER);
		articoliLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		articoliLabel.setBounds(275, 15, 225, 25);
		contentPane.add(articoliLabel);
		
		JSeparator articoliSeparator = new JSeparator();
		articoliSeparator.setForeground(Color.BLUE);
		articoliSeparator.setBackground(Color.BLACK);
		articoliSeparator.setBounds(275, 40, 225, 1);
		contentPane.add(articoliSeparator);
		
		JScrollPane articoliScroll = new JScrollPane();
		articoliScroll.setToolTipText("articoli");
		articoliScroll.setBounds(275, 65, 225, 400);
		contentPane.add(articoliScroll);
		
		articoliModel = new RefreshableListModel<>(controller.getArticoliInMagazzino());
		articoliList = new JList<>(articoliModel);
		articoliList.setToolTipText("articoli");
		articoliList.setCellRenderer(new ArticoloRenderer());
		articoliScroll.setViewportView(articoliList);
		
		JLabel usciteLabel = new JLabel("Uscite");
		usciteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usciteLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		usciteLabel.setBounds(535, 15, 225, 25);
		contentPane.add(usciteLabel);
		
		JSeparator usciteSeparator = new JSeparator();
		usciteSeparator.setForeground(Color.BLUE);
		usciteSeparator.setBackground(Color.BLACK);
		usciteSeparator.setBounds(535, 40, 225, 1);
		contentPane.add(usciteSeparator);
		
		JScrollPane usciteScroll = new JScrollPane();
		usciteScroll.setToolTipText("articoli");
		usciteScroll.setBounds(535, 65, 225, 400);
		contentPane.add(usciteScroll);
		
		usciteModel = new RefreshableListModel<>(controller.getUsciteInMagazzino());
		usciteList = new JList<>(usciteModel);
		usciteList.setToolTipText("uscite");
		usciteList.setCellRenderer(new UscitaRenderer());
		usciteScroll.setViewportView(usciteList);
		
		JButton addIngressoButton = new JButton("Add Ingresso");
		addIngressoButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		addIngressoButton.setBounds(15, 490, 225, 35);
		addIngressoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IngressoDialog dialog = controller.addIngressoButtonActionPerformed();
				dialog.setVisible(true);
			}
		});
		contentPane.add(addIngressoButton);
		
		JButton addUscitaButton = new JButton("Add Uscita");
		addUscitaButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		addUscitaButton.setBounds(535, 490, 225, 35);
		addUscitaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UscitaDialog dialog = controller.addUscitaButtonActionPerformed();
				dialog.setVisible(true);
			}
		});
		contentPane.add(addUscitaButton);
	}
	
	@Override
	public void load() {
		controller.loadIngressi();
		controller.loadArticoli();
		controller.loadUscite();
	}
	
	@Override
	public void update(Object obj) {
		if (obj instanceof List) {
			List<Object> list = (List<Object>)obj;
			if (list.get(0) instanceof Ingresso) {
				ingressiModel.addAll((List<Ingresso>)obj);
				ingressiList.updateUI();
				articoliList.updateUI();
				return;
			}
			if (list.get(0) instanceof Uscita) {
				usciteModel.addAll((List<Uscita>)obj);
				usciteList.updateUI();
				articoliList.updateUI();
				return;
			}
		}
		if (obj instanceof Articolo) {
			articoliModel.addElement((Articolo)obj);
			articoliList.updateUI();
			return;
		}
	}
}
