package containers;

import java.util.HashMap;

import interfaces.IContainer;
import models.Ordine;

public class OrdineContainer implements IContainer<Ordine> {
	private static final OrdineContainer instance = new OrdineContainer();
	public final HashMap<String, Ordine> ordini = new HashMap<String, Ordine>();
	
	private OrdineContainer() {}
	
	public static OrdineContainer getInstance() {
		return instance;
	}
	
	public void add(Ordine ordine) {
		if (!ordini.containsKey(ordine.getOrdUnicode() + ordine.getArticolo())) {
			ordini.put(ordine.getOrdUnicode() + ordine.getArticolo(), ordine);
		}
	}
	
	public void unload() {
		ordini.clear();
	}
}
