package containers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import models.Articolo;
import models.Ingresso;
import models.Uscita;
import models.User;

public class Magazzino {

	private static final Magazzino instance = new Magazzino();
	
	private HashMap<String, Articolo> articoli = new HashMap<String, Articolo>();
	private HashMap<String, Ingresso> ingressi = new HashMap<String, Ingresso>();
	private HashMap<String, Uscita> uscite = new HashMap<String, Uscita>();
	private Gson gson = new Gson();
	
	private Magazzino() {}
	
	public static Magazzino getInstance() {
		return instance;
	}
	
	public void loadIngressi() {
		try {
			Reader reader = new FileReader("C:\\Users\\hartmann\\Desktop\\JSON\\ingressi.txt");
			JsonParser parser = new JsonParser();
			ingressi = gson.fromJson(parser.parse(reader), new TypeToken<HashMap<String, Ingresso>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean areIngressiLoaded() {
		return !ingressi.isEmpty();
	}
	
	public void loadArticoli() {
		try {
			Reader reader = new FileReader("C:\\Users\\hartmann\\Desktop\\JSON\\articoli.txt");
			JsonParser parser = new JsonParser();
			articoli = gson.fromJson(parser.parse(reader), new TypeToken<HashMap<String, Articolo>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean areArticoliLoaded() {
		return !articoli.isEmpty();
	}
	
	public void loadUscite() {
		try {
			Reader reader = new FileReader("C:\\Users\\hartmann\\Desktop\\JSON\\uscite.txt");
			JsonParser parser = new JsonParser();
			uscite = gson.fromJson(parser.parse(reader), new TypeToken<HashMap<String, Uscita>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean areUsciteLoaded() {
		return !uscite.isEmpty();
	}
	
	public void registerIngresso(HashSet<Ingresso> other) {
		other.forEach(ingr -> {
			ingressi.put(ingr.getIngrUnicode() + ingr.getArticolo().getUnicode(), ingr);
			if (!articoli.containsKey(ingr.getArticolo().getUnicode())) {
				articoli.put(ingr.getArticolo().getUnicode(), ingr.getArticolo());
			} else {
				Articolo articolo = articoli.get(ingr.getArticolo().getUnicode());
				articolo.setQty(articolo.getQty() + ingr.getArticolo().getQty());
			}
		});
	}
	
	public void registerUscita(HashSet<Uscita> other) {
		other.forEach(usc -> {
			uscite.put(usc.getUscBolla() + usc.getArticolo().getUnicode(), usc);
			Articolo articolo = articoli.get(usc.getArticolo().getUnicode());
			if (articolo.getQty() - usc.getArticolo().getQty() < 0) {
				articoli.remove(articolo.getUnicode());
			} else {
			articolo.setQty(articolo.getQty() - usc.getArticolo().getQty());
			}
		});
	}
	
	public void unloadArticoli() {
		articoli.clear();
	}
	
	public void unloadIngressi() {
		ingressi.clear();
	}
	
	public void unloadUscite() {
		uscite.clear();
	}

	public HashMap<String, Articolo> getArticoli() {
		return articoli;
	}

	public HashMap<String, Ingresso> getIngressi() {
		return ingressi;
	}

	public HashMap<String, Uscita> getUscite() {
		return uscite;
	}
	
}
