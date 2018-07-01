package containers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import interfaces.IContainer;
import models.Negozio;
import models.Type;

public class NegozioContainer implements IContainer<Negozio> {

	private static final NegozioContainer instance = new NegozioContainer();
	private HashMap<String, Negozio> negozi = new HashMap<String, Negozio>();
	private Gson gson = new Gson();
	
	private NegozioContainer() {}
	
	public static NegozioContainer getInstance() {
		return instance;
	}
	
	public void loadNegozi() {
		try {
			Reader reader = new FileReader("C:\\Users\\hartmann\\Desktop\\JSON\\negozi.txt");
			JsonParser parser = new JsonParser();
			negozi = gson.fromJson(parser.parse(reader), new TypeToken<HashMap<String, Negozio>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean areNegoziLoaded() {
		return !negozi.isEmpty();
	}
	
	public HashMap<String, Negozio> getNegozi() {
		return negozi;
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
