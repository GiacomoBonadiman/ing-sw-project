package controllers;

import javax.swing.JFrame;

import com.google.gson.Gson;

import containers.UserContainer;
import interfaces.IJobView;
import models.User;
import views.JobViewFactory;
import views.LoginView;

public class LoginController {
	
	private User user;
	
	public LoginController() { }
	
	public boolean onLoginButtonClicked(LoginView view) {
		user = UserContainer.getInstance().getUsers().get(view.getUsernameField().getText());
		if (user != null) {
			IJobView jobView = JobViewFactory.getView(user.getUserType());
			((JFrame)jobView).setVisible(true);
			return true;
		}
		return false;
	}
	
	public void loadUsers() {
		UserContainer.getInstance().load();
	}
}
