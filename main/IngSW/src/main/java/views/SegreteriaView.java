package main.java.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.components.TipoDialog;
import main.java.controllers.SegreteriaController;
import main.java.interfaces.ILoadView;
import main.java.interfaces.IUpdateView;
import main.java.miscellaneous.RefreshableListModel;
import main.java.models.Ingresso;
import main.java.models.Tipo;
import main.java.models.Uscita;

public class SegreteriaView extends JFrame implements ILoadView, IUpdateView {

	private SegreteriaController controller;
	
	private JPanel contentPane;
	private RefreshableListModel<Ingresso> ingressiModel;
	private RefreshableListModel<Uscita> usciteModel;
	private RefreshableListModel<Tipo> tipiModel;
	private JList<Ingresso> ingressiList;
	private JList<Uscita> usciteList;
	private JList<Tipo> tipiList;

	public SegreteriaView(SegreteriaController controller) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.controller = controller;
		controller.addWindowToList(this);
		setTitle("Segreteria - " + controller.getUserFullName());
		
		load();
		initComponents();
	}
	
	public void initComponents() {
		JScrollPane ingressiScroll = new JScrollPane();
		ingressiScroll.setBounds(15, 65, 225, 400);
		contentPane.add(ingressiScroll);
		
		ingressiModel = new RefreshableListModel<>(controller.getIngressiInMagazzino());
		ingressiList = new JList<>(ingressiModel);
		ingressiList.setToolTipText("lista ingressi");
		ingressiScroll.setViewportView(ingressiList);
		
		JScrollPane usciteScroll = new JScrollPane();
		usciteScroll.setBounds(265, 65, 225, 400);
		contentPane.add(usciteScroll);
		
		usciteModel = new RefreshableListModel<>(controller.getUsciteInMagazzino());
		usciteList = new JList<>(usciteModel);
		usciteList.setToolTipText("lista uscite");
		usciteScroll.setViewportView(usciteList);
		
		JLabel ingressiLabel = new JLabel("Ingressi");
		ingressiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ingressiLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		ingressiLabel.setBounds(15, 30, 225, 25);
		contentPane.add(ingressiLabel);
		
		JLabel usciteLabel = new JLabel("Uscite");
		usciteLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		usciteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usciteLabel.setBounds(265, 30, 225, 25);
		contentPane.add(usciteLabel);
		
		JScrollPane tipiScroll = new JScrollPane();
		tipiScroll.setBounds(590, 65, 175, 300);
		contentPane.add(tipiScroll);
		
		tipiModel = new RefreshableListModel<>(controller.getTipiInContainer());
		tipiList = new JList<>(tipiModel);
		tipiScroll.setViewportView(tipiList);
		
		JLabel tipiLabel = new JLabel("Tipi");
		tipiLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		tipiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tipiLabel.setBounds(590, 30, 175, 25);
		contentPane.add(tipiLabel);
		
		JButton addTipoButton = new JButton("Add Tipo");
		addTipoButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		addTipoButton.setBounds(590, 380, 175, 35);
		addTipoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TipoDialog dialog = controller.addTipoButtonActionPerformed();
				dialog.setVisible(true);
			}
		});
		contentPane.add(addTipoButton);
		
		JSeparator ingressiSeparator = new JSeparator();
		ingressiSeparator.setForeground(Color.BLUE);
		ingressiSeparator.setBackground(Color.BLACK);
		ingressiSeparator.setBounds(15, 55, 225, 1);
		contentPane.add(ingressiSeparator);
		
		JSeparator usciteSeparator = new JSeparator();
		usciteSeparator.setForeground(Color.BLUE);
		usciteSeparator.setBackground(Color.BLACK);
		usciteSeparator.setBounds(265, 55, 225, 1);
		contentPane.add(usciteSeparator);
		
		JSeparator tipiSeparator = new JSeparator();
		tipiSeparator.setForeground(Color.BLUE);
		tipiSeparator.setBackground(Color.BLACK);
		tipiSeparator.setBounds(590, 55, 175, 1);
		contentPane.add(tipiSeparator);
		
		JSeparator generalSeparator = new JSeparator();
		generalSeparator.setOrientation(SwingConstants.VERTICAL);
		generalSeparator.setForeground(Color.LIGHT_GRAY);
		generalSeparator.setBounds(540, 0, 2, 515);
		contentPane.add(generalSeparator);
	}
	
	public void load() {
		controller.loadIngressi();
		controller.loadUscite();
		controller.loadTipi();
	}
	
	public void update(Object obj) {
		if (obj instanceof Tipo) {
			tipiModel.addElement((Tipo)obj);
		}
	}
}
