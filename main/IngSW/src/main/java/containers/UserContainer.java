package main.java.containers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import main.java.models.User;

public class UserContainer {
	
	private static final UserContainer instance = new UserContainer();
	public HashMap<String, User> users;
	private Gson gson = new Gson();
	
	private UserContainer() {}
	
	public static UserContainer getInstance() {
		return instance;
	}
	
	public HashMap<String, User> getUsers() {
		return users;
	}

	public void load() {
		try {
			Reader reader = new FileReader("C:\\Users\\hartmann\\Desktop\\JSON\\users.txt");
			JsonParser parser = new JsonParser();
			users = gson.fromJson(parser.parse(reader), new TypeToken<HashMap<String, User>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void add(User user) {
		if (!users.containsKey(user.getUsername())) {
			users.put(user.getUsername(), user);
		}
	}
	
	public void unload() {
		users.clear();
	}
}
