package controller;
import model.*;
import view.*;

public class Controller{
	
	private Model model;
	private View view;
	
	private Controller(){
		model = Model.getInstance();
		view = View.getInstance();
	}
	
	private static Controller INSTANCE;
	
	public static Controller getInstance()
	{
		if(INSTANCE == null)
			INSTANCE = new Controller();
		return INSTANCE;
	}
}
