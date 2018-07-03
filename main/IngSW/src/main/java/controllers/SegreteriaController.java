package main.java.controllers;

import java.util.ArrayList;
import java.util.List;

import main.java.components.TipoDialog;
import main.java.containers.Magazzino;
import main.java.containers.SportContainer;
import main.java.containers.TipoContainer;
import main.java.interfaces.IUpdateView;
import main.java.models.Ingresso;
import main.java.models.Tipo;
import main.java.models.Uscita;
import main.java.models.User;

public class SegreteriaController {

	private final Magazzino magazzino = Magazzino.getInstance();
	private final TipoContainer tipiContainer = TipoContainer.getInstance();
	private final SportContainer sportsContainer = SportContainer.getInstance();
	private final List<IUpdateView> windows = new ArrayList<>();
	
	private final User user;
	
	public SegreteriaController(User user) {
		this.user = user;
	}
	
	public void loadIngressi() {
		if (magazzino.areIngressiLoaded()) {
			return;
		}
		magazzino.loadIngressi();
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
	
	public void loadSports() {
		if (sportsContainer.areSportsLoaded()) {
			return;
		}
		sportsContainer.loadSports();
	}
	
	public String getUserFullName() {
		return user.getUsername();
	}
	
	public List<Ingresso> getIngressiInMagazzino() {
		return new ArrayList<Ingresso>(magazzino.getIngressi().values());
	}
	
	public List<Uscita> getUsciteInMagazzino() {
		return new ArrayList<Uscita>(magazzino.getUscite().values());
	}
	
	public List<Tipo> getTipiInContainer() {
		return new ArrayList<Tipo>(tipiContainer.getTipi().values());
	}
	
	public boolean isTipoNew(String key) {
		return tipiContainer.getTipi().get(key) == null;
	}
	
	public List<String> getSportsInContainer() {
		List<String> sports = new ArrayList<String>(sportsContainer.getSports());
		return sports;
	}
	
	public TipoDialog addTipoButtonActionPerformed() {
		return new TipoDialog(this);
	}
	
	public void createTipoButtonActionPerformed(String uniname, String descrizione, String materiali, String sport) {
		Tipo tipo = new Tipo(uniname, descrizione, sport, materiali);
		tipiContainer.insertTipoInContainer(tipo);
		updateWindows(tipo);
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
