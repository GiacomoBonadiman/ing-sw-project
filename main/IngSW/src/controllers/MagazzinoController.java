package controllers;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;

import components.ArticoloDialog;
import components.IngressoDataDialog;
import components.IngressoDialog;
import components.UscitaDialog;
import containers.Magazzino;
import containers.TypeContainer;
import interfaces.IJobView;
import models.Articolo;
import models.Type;
import views.MagazzinoView;

public class MagazzinoController {

	private final Magazzino mInstance = Magazzino.getInstance();
	private final TypeContainer tInstance = TypeContainer.getInstance();
	private final List<IJobView> windows = new ArrayList<>();
	
	public MagazzinoController() {}
	
	public void addWindowToList(IJobView window) {
		windows.add(window);
	}
	
	public Magazzino getMagazzino() {
		return mInstance;
	}
	
	public TypeContainer getTypeContainer() {
		return tInstance;
	}
	
	public IngressoDialog ingressiButtonActionPerformed(ActionEvent evt) {
        return new IngressoDialog(this);
    }
	
	public UscitaDialog usciteButtonActionPerformed(ActionEvent evt) {
		return new UscitaDialog(this);
	}
	
	public ArticoloDialog articoloButtonActionPerformed(ActionEvent evt) {
		return new ArticoloDialog(this);
	}
	
	public void loadIngressi(MagazzinoView view) {
		if (mInstance.areIngressiLoaded()) {
			return;
		}
		mInstance.loadIngressi();
	}
	
	public void loadArticoli(MagazzinoView view) {
		if (mInstance.areArticoliLoaded()) {
			return;
		}
		mInstance.loadArticoli();
	}
	
	public void loadUscite(MagazzinoView view) {
		if (mInstance.areUsciteLoaded()) {
			return;
		}
		mInstance.loadUscite();
	}
	
	public void loadTipi(ArticoloDialog dialog) {
		if (tInstance.areTipiLoaded()) {
			return;
		}
		tInstance.loadTipi();
	}

	public IngressoDataDialog ingressiListItemPressed(String selectedValue) {
		return new IngressoDataDialog(this, mInstance.getIngressi().get(selectedValue));
	}
	
	public void minusButtonPressed(ActionEvent evt, JTextField text) {
		int i = Integer.parseInt(text.getText());
		if (i != 1) {
			i--;
			text.setText(String.valueOf(i));
		}
	}
	
	public void plusButtonActionPerformed(ActionEvent evt, JTextField text) {
		int i = Integer.parseInt(text.getText());
		i++;
		text.setText(String.valueOf(i));
	}
	
	public void createArticoloButtonActionPerformed(ActionEvent e, ArticoloDialog dialog, Type type, String unicode, int qty, double price, LocalDate date) {
		Articolo articolo = new Articolo(type, unicode, qty, price, date);
		Magazzino.getInstance().getArticoli().put(articolo.getUnicode(), articolo);
		updateWindows();
	}
	
	private void updateWindows() {
		for (IJobView window : windows) {
			window.update();
		}
	}
}
