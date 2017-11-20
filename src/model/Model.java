package model;

import controller.Controller;

public class Model {

	private Model() {
	}

	private static Model INSTANCE;

	public static Model getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Model();
		}
		return INSTANCE;
	}
}
