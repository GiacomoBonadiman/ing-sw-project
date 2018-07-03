package main.java.controllers;

import javax.swing.JFrame;

import main.java.containers.UserContainer;
import main.java.interfaces.IUpdateView;
import main.java.models.User;
import main.java.views.JobViewFactory;
import main.java.views.LoginView;

public class LoginController {
	
	private User user;
	
	public LoginController() { }
	
	public boolean onLoginButtonClicked(LoginView view) {
		user = UserContainer.getInstance().getUsers().get(view.getUsernameField().getText());
		if (user != null) {
			IUpdateView jobView = JobViewFactory.getView(user);
			((JFrame)jobView).setVisible(true);
			return true;
		}
		return false;
	}
	
	public void loadUsers() {
		UserContainer.getInstance().load();
	}
}
