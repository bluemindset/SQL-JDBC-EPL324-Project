package epl342_UI_Horses;

public class MainUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //ViewChooseUser view = new ViewChooseUser(model);
		 ControllerChooseUser controller = new ControllerChooseUser();
		 controller.addUserType("R1 - DATA USER");
		 controller.addUserType("SA - SYSTEM ADMINISTRATOR");
		 controller.updateView();
	}

}
