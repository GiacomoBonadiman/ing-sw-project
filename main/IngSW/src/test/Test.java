package test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.google.gson.Gson;

import controllers.LoginController;
import models.Type;
import views.LoginView;

public class Test {

	public static void main(String[] args) {
		LoginController controller = new LoginController();
		LoginView login = new LoginView(controller);
		login.setVisible(true);
	}
}
