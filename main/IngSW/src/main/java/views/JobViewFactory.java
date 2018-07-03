package main.java.views;

import main.java.controllers.MagazzinoController;
import main.java.controllers.ResponsabileController;
import main.java.controllers.SegreteriaController;
import main.java.interfaces.IUpdateView;
import main.java.models.User;

public class JobViewFactory {
	
	public static IUpdateView getView(User user) {
		if (user == null) {
			return null;
		}
		if (user.getUserType().equals("Magazziniere")) {
			return new MagazzinoView(new MagazzinoController(user));
		}
		if (user.getUserType().equals("Responsabile")) {
			return new ResponsabileView(new ResponsabileController(user));
		}
		if (user.getUserType().equals("Segreteria")) {
			return new SegreteriaView(new SegreteriaController(user));
		}
		return null;
	}
}
