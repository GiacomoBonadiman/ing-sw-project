package main.java.containers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import main.java.interfaces.IContainer;
import main.java.models.Ordine;

public class OrdineContainer implements IContainer<Ordine> {
	private static final OrdineContainer instance = new OrdineContainer();
	public HashMap<String, Ordine> ordini = new HashMap<String, Ordine>();
	private Gson gson = new Gson();
	
	private OrdineContainer() {}
	
	public static OrdineContainer getInstance() {
		return instance;
	}
	
	public void loadOrdini() {
		try (Reader reader = new FileReader("C:\\Users\\hartmann\\Desktop\\JSON\\ordini.txt")) {
			JsonParser parser = new JsonParser();
			ordini = gson.fromJson(parser.parse(reader), new TypeToken<HashMap<String, Ordine>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean areOrdiniLoaded() {
		return !ordini.isEmpty();
	}
	
	public void insertOrdineInContainer(Ordine ordine) {
		ordini.put(ordine.getOrdUnicode(), ordine);
	}
	
	public void saveOrdini() {
		try (Writer writer = new FileWriter("C:\\Users\\hartmann\\Desktop\\JSON\\ordini.txt")) {
			String json = gson.toJson(ordini);
			writer.write(json);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void unload() {
		ordini.clear();
	}
	
	public HashMap<String, Ordine> getOrdini() {
		return ordini;
	}

	@Override
	public void add(Ordine obj) {
		
	}
}
