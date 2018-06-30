package containers;

import java.util.HashSet;

import interfaces.IContainer;

public class SportContainer implements IContainer<String> {
	
	private static final SportContainer instance = new SportContainer();
	public final HashSet<String> sports = new HashSet<String>();
	
	private SportContainer() {}
	
	public static SportContainer getInstance() {
		return instance;
	}
	
	public void add(String sport) {
		if (!sports.contains(sport)) {
			sports.add(sport);
		}
	}
	
	public void unload() {
		sports.clear();
	}
}
