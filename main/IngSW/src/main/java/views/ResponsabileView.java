package main.java.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.components.OrdineDialog;
import main.java.controllers.ResponsabileController;
import main.java.interfaces.ILoadView;
import main.java.interfaces.IUpdateView;
import main.java.miscellaneous.RefreshableListModel;
import main.java.models.Ordine;
import main.java.renderers.OrdineRenderer;

public class ResponsabileView extends JFrame implements ILoadView, IUpdateView {

	private JPanel contentPane;
	private RefreshableListModel<Ordine> ordiniModel;
	private JList<Ordine> ordiniList;
	
	private final ResponsabileController controller;

	public ResponsabileView(ResponsabileController controller) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.controller = controller;
		this.controller.addWindowToList(this);
		setTitle("Responsabile - " + this.controller.getUserFullName());
		
		load();
		initComponents();
	}
	
	private void initComponents() {
		JLabel ordiniLabel = new JLabel("Ordini Passati");
		ordiniLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ordiniLabel.setFont(new Font("Cambria", Font.PLAIN, 18));
		ordiniLabel.setBounds(15, 15, 275, 25);
		contentPane.add(ordiniLabel);
		
		JSeparator ordiniSeparator = new JSeparator();
		ordiniSeparator.setForeground(Color.BLUE);
		ordiniSeparator.setBackground(Color.BLACK);
		ordiniSeparator.setBounds(15, 40, 275, 1);
		contentPane.add(ordiniSeparator);
		
		JScrollPane ordiniScroll = new JScrollPane();
		ordiniScroll.setBounds(15, 65, 275, 400);
		contentPane.add(ordiniScroll);
		
		ordiniModel = new RefreshableListModel<>(controller.getOrdiniInContainer());
		ordiniList = new JList<>(ordiniModel);
		ordiniList.setCellRenderer(new OrdineRenderer());
		ordiniScroll.setViewportView(ordiniList);
		
		JButton addOrdineButton = new JButton("Add Ordine");
		addOrdineButton.setFont(new Font("Cambria", Font.PLAIN, 18));
		addOrdineButton.setBounds(305, 430, 175, 35);
		addOrdineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrdineDialog dialog = controller.addOrdineButtonActionPerformed();
				dialog.setVisible(true);
			}
		});
		contentPane.add(addOrdineButton);
	}

	@Override
	public void load() {
		controller.loadOrdini();
	}
	
	@Override
	public void update(Object obj) {
		if (obj instanceof List) {
			List<Object> list = (List<Object>)obj;
			if (list.get(0) instanceof Ordine) {
				ordiniModel.addAll((List<Ordine>)obj);
				ordiniList.updateUI();
			}
		}
	}

}
