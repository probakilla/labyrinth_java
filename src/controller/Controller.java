package controller;

import javafx.stage.Stage;
import model.*;
import view.*;

public class Controller {

	private static Controller INSTANCE;

	private Model model;
	private View view;

	private Controller() {
		model = Model.getInstance();
		view = View.getInstance();
	}

	public static Controller getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Controller();
		return INSTANCE;
	}

	public void start(Stage stage) {
		view.start(stage);
	}
}
