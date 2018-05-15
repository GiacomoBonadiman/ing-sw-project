package sport;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.*;

public class SportFrame extends JFrame{
	private static final String titolo = "Magazzino";
	private static final JLabel testo = new JLabel("Ordini");
	private static final int larghezza=200, altezza=200;
	private static final JButton piu = new JButton("+");
	public SportFrame() {
		super(titolo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(larghezza, altezza);
		Container frmContentPane = this.getContentPane();
		frmContentPane.setLayout(new FlowLayout());
		frmContentPane.add(piu);
		this.getContentPane().add(testo);
		this.setVisible(true);
	}
}
