package main.java.containers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import main.java.interfaces.IContainer;
import main.java.models.Tipo;

public class TipoContainer implements IContainer<Tipo> {

	private static final TipoContainer instance = new TipoContainer();
	public HashMap<String, Tipo> types = new HashMap<String, Tipo>();
	private Gson gson = new Gson();
	
	private TipoContainer() {}
	
	public static TipoContainer getInstance() {
		return instance;
	}
	
	public void loadTipi() {
		try (Reader reader = new FileReader("C:\\Users\\hartmann\\Desktop\\JSON\\tipi.txt")) {
			JsonParser parser = new JsonParser();
			types = gson.fromJson(parser.parse(reader), new TypeToken<HashMap<String, Tipo>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean areTipiLoaded() {
		return !types.isEmpty();
	}
	
	public void insertTipoInContainer(Tipo tipo) {
		types.put(tipo.getUniname(), tipo);
	}
	
	public void unload() {
		types.clear();
	}
	
	public HashMap<String, Tipo> getTipi() {
		return types;
	}

	@Override
	public void add(Tipo object) {
		
	}
}
