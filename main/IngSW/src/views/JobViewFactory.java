package views;

import controllers.MagazzinoController;
import interfaces.IJobView;

public class JobViewFactory {
	
	public static IJobView getView(String userType) {
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
