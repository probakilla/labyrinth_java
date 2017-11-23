package view;

import controller.Controller;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View {

	static final int SPAN = 4;
	static final int WALL = 2;
	static final int CELL = 9;
	public static final Paint WALL_COLOR = Color.BURLYWOOD;
	public static final Paint SCENE_COLOR = Color.BEIGE;

	Stage stage;
	Scene scene;
	Pane pane;

	private View() {
	}

	private static View INSTANCE;

	public static View getInstance() {
		if (INSTANCE == null)
			INSTANCE = new View();
		return INSTANCE;
	}

	public void start(Stage stage) {
		this.stage = stage;
		this.pane = new Pane();
		
		drawFrame(stage, 16, 16);
		drawWall(1, 1, 2, 1, Color.CHOCOLATE);
		stage.show();
	}

	/**
	 * Dessine la base du labyrinthe.
	 * @param stage Fenêtre ou l'on dessine le labyrinthe
	 * @param nbrX Taille du labyrinthe en longueur
	 * @param nbrY Taille du labyrinthe en largeur
	 */
	public void drawFrame(Stage stage, int nbrX, int nbrY) {
		scene = new Scene(pane, ((WALL + CELL) * nbrX + WALL) * SPAN, ((WALL + CELL) * nbrY + WALL) * SPAN);

		scene.setFill(SCENE_COLOR);
		stage.setScene(scene);

		Rectangle square = new Rectangle(0, 0, SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);

		square = new Rectangle(0, SPAN * (nbrY * (CELL + WALL)), SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);

		square = new Rectangle(0, 0, WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);

		square = new Rectangle(SPAN * (nbrX * (CELL + WALL)), 0, WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);

		for (int x = 0; x < nbrX - 1; ++x) {
			int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
			for (int y = 0; y < nbrY - 1; ++y) {
				int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
				square = new Rectangle(offsetX, offsetY, WALL * SPAN, WALL * SPAN);
				square.setFill(WALL_COLOR);
				pane.getChildren().add(square);
			}
		}
	}

	/**
	 * Dessine un entre deux point adjacent du labyrinthe
	 * @param xs coordonnée en x du premier point
	 * @param ys coordonnée en y du premier point
	 * @param xt coordonnée en x du deuxième point
	 * @param yt coordonnée en y du deuxième point
	 * @param color couleur du mur
	 */
	public void drawWall(int xs, int ys, int xt, int yt, Paint color) {
		int x = 0, y = 0, xspan = 0, yspan = 0;
		if (ys == yt) {
			x = ((WALL + CELL) + (WALL + CELL) * ((int) (xs + xt) / 2)) * SPAN;
			y = (WALL + ys * (WALL + CELL)) * SPAN;
			xspan = WALL * SPAN;
			yspan = CELL * SPAN;
			Rectangle square = new Rectangle(x, y, xspan, yspan);
			square.setFill(color);
			pane.getChildren().add(square);
		} else if (xs == xt) {
			x = (WALL + xs * (WALL + CELL)) * SPAN;
			y = ((WALL + CELL) + (WALL + CELL) * ((int) (ys + yt) / 2)) * SPAN;
			xspan = CELL * SPAN;
			yspan = WALL * SPAN;
			Rectangle square = new Rectangle(x, y, xspan, yspan);
			square.setFill(color);
			pane.getChildren().add(square);
		}
	}

}
