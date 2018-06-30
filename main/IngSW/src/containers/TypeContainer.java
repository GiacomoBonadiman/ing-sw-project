package containers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import interfaces.IContainer;
import models.Ingresso;
import models.Type;

public class TypeContainer implements IContainer<Type> {

	private static final TypeContainer instance = new TypeContainer();
	public HashMap<String, Type> types = new HashMap<String, Type>();
	private Gson gson = new Gson();
	
	private TypeContainer() {}
	
	public static TypeContainer getInstance() {
		return instance;
	}
	
	public void loadTipi() {
		try {
			Reader reader = new FileReader("C:\\Users\\hartmann\\Desktop\\JSON\\tipi.txt");
			JsonParser parser = new JsonParser();
			types = gson.fromJson(parser.parse(reader), new TypeToken<HashMap<String, Type>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean areTipiLoaded() {
		return !types.isEmpty();
	}
	
	public HashMap<String, Type> getTypes() {
		return types;
	}
	
	public void add(Type type) {
		if (!types.containsKey(type.getUniname())) {
			types.put(type.getUniname(), type);
		}
	}
	
	public void unload() {
		types.clear();
	}
}
