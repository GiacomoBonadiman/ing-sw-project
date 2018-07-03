package main.java.controllers;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.java.components.ArticoloDialog;
import main.java.components.IngressoDialog;
import main.java.components.LinkDialog;
import main.java.components.UscitaDialog;
import main.java.containers.Magazzino;
import main.java.containers.NegozioContainer;
import main.java.containers.OrdineContainer;
import main.java.containers.TipoContainer;
import main.java.interfaces.IUpdateView;
import main.java.models.Articolo;
import main.java.models.Ingresso;
import main.java.models.Ordine;
import main.java.models.Tipo;
import main.java.models.Uscita;
import main.java.models.User;
import main.java.views.MagazzinoView;

public class MagazzinoController {

	private final Magazzino magazzino = Magazzino.getInstance();
	private final TipoContainer tipiContainer = TipoContainer.getInstance();
	private final NegozioContainer negoziContainer = NegozioContainer.getInstance();
	private final OrdineContainer ordiniContainer = OrdineContainer.getInstance();
	private final List<IUpdateView> windows = new ArrayList<>();
	
	private User user;
	
	public MagazzinoController(User user) {
		this.user = user;
	}
	
	public void loadIngressi() {
		if (magazzino.areIngressiLoaded()) {
			return;
		}
		magazzino.loadIngressi();
	}
	
	public void loadArticoli() {
		if (magazzino.areArticoliLoaded()) {
			return;
		}
		magazzino.loadArticoli();
	}
	
	public void loadUscite() {
		if (magazzino.areUsciteLoaded()) {
			return;
		}
		magazzino.loadUscite();
	}
	
	public void loadTipi() {
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
	
	public void loadOrdini() {
		if (ordiniContainer.areOrdiniLoaded()) {
			return;
		}
		ordiniContainer.loadOrdini();
	}
	
	public String getUserFullName() {
		return user.getUsername();
	}
	
	public List<Articolo> getArticoliInMagazzino() {
		return new ArrayList<Articolo>(magazzino.getArticoli().values());
	}
	
	public boolean isArticoloNew(String key) {
		return magazzino.getArticoli().get(key) == null;
	}
	
	public boolean verifyEnoughArticoli(String key, int qtyToTake) {
		return magazzino.getArticoli().get(key).getQty() >= qtyToTake;
	}
	
	public List<Ingresso> getIngressiInMagazzino() {
		return new ArrayList<Ingresso>(magazzino.getIngressi().values());
	}
	
	public boolean isIngressoNew(String key) {
		return magazzino.getIngressi().get(key) == null;
	}
	
	public List<Uscita> getUsciteInMagazzino() {
		return new ArrayList<Uscita>(magazzino.getUscite().values());
	}
	
	public boolean isUscitaNew(String key) {
		return magazzino.getUscite().get(key) == null;
	}
	
	public List<Tipo> getTipiInContainer() {
		return new ArrayList<Tipo>(tipiContainer.getTipi().values());
	}
	
	public List<Ordine> getNotDispatchedOrdiniInContainer() {
		ArrayList<Ordine> notDispatched = new ArrayList<>();
		ordiniContainer.getOrdini().values().forEach(ordine -> {
			if (!ordine.isDispatched()) {
				notDispatched.add(ordine);
			}
		});
		return notDispatched;
	}
	
	public boolean isNegozioCorrect(String key) {
		return negoziContainer.getNegozi().get(key) != null;
	}
	
	public ArticoloDialog addArticoloButtonActionPerformed() {
		return new ArticoloDialog(this);
	}
	
	public IngressoDialog addIngressoButtonActionPerformed() {
		return new IngressoDialog(this);
	}
	
	public UscitaDialog addUscitaButtonActionPerformed() {
		return new UscitaDialog(this);
	}
	
	public LinkDialog linkOrdineButtonActionPerformed(UscitaDialog dialog) {
		return new LinkDialog(this, dialog);
	}
	
	public Ingresso addIngressoToListButtonActionPerformed(String ingrUnicode, LocalDate ingrDate, Articolo articolo, int mensola, int qty) {
		Ingresso ingresso = new Ingresso(ingrUnicode, ingrDate, articolo, mensola);
		return ingresso;
	}
	
	public Uscita addUscitaToListButtonActionPerformed(LocalDate uscDate, String uscBolla, Articolo articolo, String negozio, String spedizioniere, String linkedOrd, int qty) {
		Uscita uscita = new Uscita(uscDate, uscBolla, articolo, negoziContainer.getNegozi().get(negozio), spedizioniere, linkedOrd);
		return uscita;
	}
	
	public void createArticoloButtonActionPerformed(Tipo tipo, String unicode, int qty, double price, LocalDate date) {
		Articolo articolo = new Articolo(tipo, unicode, qty, price, date);
		magazzino.insertArticoloInMagazzino(articolo);
		updateWindows(articolo);
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
			magazzino.getArticoli().get(ingresso.getArticolo().getUnicode()).setQty(
					magazzino.getArticoli().get(ingresso.getArticolo().getUnicode()).getQty() + ingresso.getArticolo().getQty()
					);
			magazzino.insertIngressoInMagazzino(ingresso);
		});
		updateWindows(ingressi);
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
			if (magazzino.getArticoli().get(uscita.getArticolo().getUnicode()).getQty() - uscita.getArticolo().getQty() == 0) {
				magazzino.deleteArticolo(uscita.getArticolo());
			} else {
				magazzino.getArticoli().get(uscita.getArticolo().getUnicode()).setQty(
						magazzino.getArticoli().get(uscita.getArticolo().getUnicode()).getQty() - uscita.getArticolo().getQty());
				uscita.setArticolo(magazzino.getArticoli().get(uscita.getArticolo().getUnicode()));
			}
			magazzino.insertUscitaInMagazzino(uscita);
		});
		updateWindows(uscite);
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
	
	public void addWindowToList(IUpdateView window) {
		windows.add(window);
	}
	
	private void updateWindows(Object obj) {
		for (IUpdateView window : windows) {
			window.update(obj);
		}
	}
}
