package views;

import controllers.MagazzinoController;
import interfaces.IUpdateView;

public class JobViewFactory {
	
	public static IUpdateView getView(String userType) {
		if (userType == null) {
			return null;
		}
		if (userType.equals("Magazziniere")) {
			return new MagazzinoView(new MagazzinoController());
		}
		if (userType.equals("Responsabile")) {
			return null;
		}
		if (userType.equals("Segreteria")) {
			return null;
		}
		return null;
	}
}
