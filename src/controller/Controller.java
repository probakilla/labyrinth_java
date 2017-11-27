package controller;

import javafx.stage.Stage;
import model.*;
import view.*;

public class Controller 
{

	private static Controller INSTANCE;

	private Model _model;
	private View _view;

	private Controller() 
	{
		_model = Model.getInstance();
		_view = View.getInstance();
	}

	public static Controller getInstance() 
	{
		if (INSTANCE == null)
			INSTANCE = new Controller();
		return INSTANCE;
	}

	public void start(Stage stage) 
	{
		_model.GraphToDot();
		_view.start(stage);
	}
}