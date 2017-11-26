package epl342_UI_Horses;

import java.util.ArrayList;
import java.util.List;

public class ModelChooseUser {
	private List<String> userTypes;
	
	public ModelChooseUser(){
		userTypes = new ArrayList<String> ();
	}
	
	public List<String> getUserTypes(){
		return userTypes;
	}
	
	public void addUserType(String n){
		userTypes.add(n);
	}	

}
