package main.java.test;

import main.java.controllers.LoginController;
import main.java.views.LoginView;

public class Test {

	public static void main(String[] args) {
		LoginController controller = new LoginController();
		LoginView login = new LoginView(controller);
		login.setVisible(true);
	}
}
