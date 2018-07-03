package main.java.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.java.components.OrdineDialog;
import main.java.containers.Magazzino;
import main.java.containers.NegozioContainer;
import main.java.containers.OrdineContainer;
import main.java.interfaces.IUpdateView;
import main.java.models.Articolo;
import main.java.models.Ordine;
import main.java.models.User;

public class ResponsabileController {

	private Magazzino magazzino = Magazzino.getInstance();
	private OrdineContainer ordiniContainer = OrdineContainer.getInstance();
	private NegozioContainer negoziContainer = NegozioContainer.getInstance();
	private List<IUpdateView> windows = new ArrayList<>();
	
	private User user;
	
	public ResponsabileController(User user) {
		this.user = user;
	}
	
	public void loadArticoli() {
		if (magazzino.areArticoliLoaded()) {
			return;
		}
		magazzino.loadArticoli();
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
	
	public String getNegozio() {
		return user.getNegozio();
	}
	
	public List<Articolo> getArticoliInMagazzino() {
		return new ArrayList<Articolo>(magazzino.getArticoli().values());
	}
	
	public List<Ordine> getOrdiniInContainer() {
		return new ArrayList<Ordine>(ordiniContainer.getOrdini().values());
	}
	
	public boolean isOrdineNew(String key) {
		return ordiniContainer.getOrdini().get(key) == null;
	}
	
	public OrdineDialog addOrdineButtonActionPerformed() {
		return new OrdineDialog(this);
	}
	
	public Ordine addOrdineToListButtonActionPerformed(String unicode, LocalDate date, Articolo articolo, double totPrice) {
		Ordine ordine = new Ordine(negoziContainer.getNegozi().get(user.getNegozio()), unicode, date, articolo, totPrice, false);
		return ordine;
	}
	
	public void createOrdiniButtonActionPerformed(List<Ordine> ordini) {
		ordini.forEach(ordine -> {
			ordiniContainer.insertOrdineInContainer(ordine);
		});
		updateWindows(ordini);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				ordiniContainer.saveOrdini();
			}
		});
		t.run();
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
