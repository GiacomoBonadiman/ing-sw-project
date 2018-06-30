package containers;

import java.util.HashMap;

import interfaces.IContainer;
import models.Negozio;

public class NegozioContainer implements IContainer<Negozio> {

	private static final NegozioContainer instance = new NegozioContainer();
	public final HashMap<String, Negozio> negozi = new HashMap<String, Negozio>();
	
	private NegozioContainer() {}
	
	public static NegozioContainer getInstance() {
		return instance;
	}
	
	public void add(Negozio negozio) {
		if (!negozi.containsKey(negozio.getCodFiscale())) {
			negozi.put(negozio.getCodFiscale(), negozio);
		}
	}
	
	public void unload() {
		negozi.clear();
	}
}
