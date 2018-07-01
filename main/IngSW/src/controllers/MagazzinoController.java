package controllers;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import components.ArticoloDataDialog;
import components.ArticoloDialog;
import components.IngressoDataDialog;
import components.IngressoDialog;
import components.UscitaDataDialog;
import components.UscitaDialog;
import containers.Magazzino;
import containers.NegozioContainer;
import containers.TypeContainer;
import interfaces.IUpdateView;
import models.Articolo;
import models.Ingresso;
import models.Uscita;
import views.MagazzinoView;

public class MagazzinoController {

	private final Magazzino magazzino = Magazzino.getInstance();
	private final TypeContainer tipiContainer = TypeContainer.getInstance();
	private final NegozioContainer negoziContainer = NegozioContainer.getInstance();
	private final List<IUpdateView> windows = new ArrayList<>();
	
	public MagazzinoController() {}
	
	public void addWindowToList(IUpdateView window) {
		windows.add(window);
	}
	
	public Magazzino getMagazzino() {
		return magazzino;
	}
	
	public TypeContainer getTypeContainer() {
		return tipiContainer;
	}
	
	public String[] getArticoliInMagazzino() {
		String articoli[] = magazzino.getArticoli().keySet().toArray(new String[0]);
		return articoli;
	}
	
	public boolean isArticoloNew(String key) {
		return magazzino.getArticoli().get(key) == null;
	}
	
	public boolean verifyEnoughArticoli(String key, int qtyToTake) {
		return magazzino.getArticoli().get(key).getQty() >= qtyToTake;
	}
	
	public String[] getIngressiInMagazzino() {
		String ingressi[] = magazzino.getIngressi().keySet().toArray(new String[0]);
		return ingressi;
	}
	
	public boolean isIngressoNew(String key) {
		return magazzino.getIngressi().get(key) == null;
	}
	
	public String[] getUsciteInMagazzino() {
		String uscite[] = magazzino.getUscite().keySet().toArray(new String[0]);
		return uscite;
	}
	
	public boolean isUscitaNew(String key) {
		return magazzino.getUscite().get(key) == null;
	}
	
	public String[] getTipiInContainer() {
		String tipi[] = tipiContainer.getTipi().keySet().toArray(new String[0]);
		return tipi;
	}
	
	public boolean isNegozioCorrect(String key) {
		return negoziContainer.getNegozi().get(key) != null;
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
		if (magazzino.areIngressiLoaded()) {
			return;
		}
		magazzino.loadIngressi();
	}
	
	public void loadArticoli(MagazzinoView view) {
		if (magazzino.areArticoliLoaded()) {
			return;
		}
		magazzino.loadArticoli();
	}
	
	public void loadUscite(MagazzinoView view) {
		if (magazzino.areUsciteLoaded()) {
			return;
		}
		magazzino.loadUscite();
	}
	
	public void loadTipi(ArticoloDialog dialog) {
		if (tipiContainer.areTipiLoaded()) {
			return;
		}
		tipiContainer.loadTipi();
	}
	
	public void loadNegozi() {
		if (negoziContainer.areNegoziLoaded()) {
			return;
		}
		negoziContainer.loadNegozi();
	}

	public IngressoDataDialog ingressiListItemPressed(String selectedValue) {
		return new IngressoDataDialog(this, magazzino.getIngressi().get(selectedValue));
	}
	
	public IngressoDataDialog tempIngressiListItemPressed(String selectedValue, List<Ingresso> temp) {
		Ingresso ingresso = null;
		for (Ingresso ingr : temp) {
			if ((ingr.getIngrUnicode()).equals(selectedValue)) {
				ingresso = ingr;
				break;
			}
		}
		return new IngressoDataDialog(this, ingresso);
	}
	
	public ArticoloDataDialog articoliListItemPressed(String selectedValue) {
		return new ArticoloDataDialog(this, magazzino.getArticoli().get(selectedValue));
	}
	
	public UscitaDataDialog usciteListItemPressed(String selectedValue) {
		return new UscitaDataDialog(this, magazzino.getUscite().get(selectedValue));
	}
	
	public UscitaDataDialog tempUsciteListItemPressed(String selectedValue, List<Uscita> temp) {
		Uscita uscita = null;
		for (Uscita usc : temp) {
			if ((usc.getUscBolla()).equals(selectedValue)) {
				uscita = usc;
				break;
			}
		}
		return new UscitaDataDialog(this, uscita);
	}
	
	public Ingresso addIngressoToListButtonActionPerformed(String ingrUnicode, LocalDate ingrDate, String articoloKey, int mensola, int qty) {
		Articolo articolo = magazzino.getArticoli().get(articoloKey), copy;
		copy = new Articolo(articolo.getType(), articolo.getUnicode(), articolo.getQty() + qty, articolo.getPrice(), articolo.getDate());
		Ingresso ingresso = new Ingresso(ingrUnicode, ingrDate, copy, mensola);
		return ingresso;
	}
	
	public Uscita addUscitaToListButtonActionPerformed(LocalDate uscDate, String uscBolla, String articoloKey, String negozio, String spedizioniere, String linkedOrd, int qty) {
		Articolo articolo = magazzino.getArticoli().get(articoloKey), copy;
		copy = new Articolo(articolo.getType(), articolo.getUnicode(), articolo.getQty() - qty, articolo.getPrice(), articolo.getDate());
		Uscita uscita = new Uscita(uscDate, uscBolla, copy, negoziContainer.getNegozi().get(negozio), spedizioniere, linkedOrd);
		return uscita;
	}
	
	public void createArticoloButtonActionPerformed(ActionEvent e, ArticoloDialog dialog, String tipo, String unicode, int qty, double price, LocalDate date) {
		Articolo articolo = new Articolo(tipiContainer.getTipi().get(tipo), unicode, qty, price, date);
		magazzino.insertArticoloInMagazzino(articolo);
		updateWindows();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				magazzino.saveArticoli();
			}
		});
		t.start();
	}
	
	public void createIngressiButtonActionPerformed(List<Ingresso> ingressi) {
		ingressi.forEach(ingresso -> {
			magazzino.getArticoli().get(ingresso.getArticolo().getUnicode()).setQty(ingresso.getArticolo().getQty());
			ingresso.setArticolo(magazzino.getArticoli().get(ingresso.getArticolo().getUnicode()));
			magazzino.insertIngressoInMagazzino(ingresso);
		});
		updateWindows();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				magazzino.saveIngressi();
			}
		});
		t.run();
	}
	
	public void createUsciteButtonActionPerformed(List<Uscita> uscite) {
		uscite.forEach(uscita -> {
			if (uscita.getArticolo().getQty() == 0) {
				magazzino.deleteArticolo(uscita.getArticolo());
			} else {
				magazzino.getArticoli().get(uscita.getArticolo().getUnicode()).setQty(uscita.getArticolo().getQty());
				uscita.setArticolo(magazzino.getArticoli().get(uscita.getArticolo().getUnicode()));
			}
			magazzino.insertUscitaInMagazzino(uscita);
		});
		updateWindows();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				magazzino.saveUscite();
			}
		});
		t.run();
	}
	
	public void saveAll() {
		magazzino.saveIngressi();
		magazzino.saveArticoli();
		magazzino.saveUscite();
	}
	
	private void updateWindows() {
		for (IUpdateView window : windows) {
			window.update();
		}
	}
}
