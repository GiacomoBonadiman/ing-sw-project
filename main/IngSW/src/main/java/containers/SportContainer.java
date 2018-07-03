package main.java.containers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import main.java.interfaces.IContainer;

public class SportContainer implements IContainer<String> {
	
	private static final SportContainer instance = new SportContainer();
	public HashSet<String> sports = new HashSet<String>();
	private Gson gson = new Gson();
	
	private SportContainer() {}
	
	public static SportContainer getInstance() {
		return instance;
	}
	
	public void loadSports() {
		try (Reader reader = new FileReader("C:\\Users\\hartmann\\Desktop\\JSON\\sports.txt")) {
			JsonParser parser = new JsonParser();
			sports = gson.fromJson(parser.parse(reader), new TypeToken<HashSet<String>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean areSportsLoaded() {
		return !sports.isEmpty();
	}
	
	public void add(String sport) {
		if (!sports.contains(sport)) {
			sports.add(sport);
		}
	}
	
	public void unload() {
		sports.clear();
	}
	
	public HashSet<String> getSports() {
		return sports;
	}
}
