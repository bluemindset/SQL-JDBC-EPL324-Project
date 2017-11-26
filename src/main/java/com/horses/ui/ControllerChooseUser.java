package com.horses.ui;

import java.util.List;

public class ControllerChooseUser {

	private ModelChooseUser model;
	private ViewChooseUser view;
	
	
	public ControllerChooseUser() {
		model = new ModelChooseUser();
		view = new ViewChooseUser(model);
	}
	
	public ControllerChooseUser(ModelChooseUser m, ViewChooseUser v) {
		model = m;
		view = v;
	}
	
	public List<String> getUserTypes(){
		return model.getUserTypes();
	}
	
	public void addUserType(String n){
		model.addUserType(n);
	}	

	public void updateView(){
		view.run(model);
	}
	
}
