package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View 
{

	static final int SPAN = 4;
	static final int WALL = 2;
	static final int CELL = 9;
	public static final Paint WALL_COLOR = Color.BURLYWOOD;
	public static final Paint SCENE_COLOR = Color.BEIGE;

	private Stage _stage;
	private Scene _scene;
	private Pane _pane;

	private View() {}			

	private static View INSTANCE;

	public static View getInstance() 
	{
		if (INSTANCE == null)
			INSTANCE = new View();
		return INSTANCE;
	}

	public void start(Stage stage) 
	{
		_stage = stage;
		_pane = new Pane();
		
		drawFrame(stage, 16, 16);
		drawWall(1, 1, 2, 1, Color.CHOCOLATE);
		stage.show();
	}	

	/**
	 * Draw the labyrinth basis.
	 * @param stage Window where we draw the labyrinth.
	 * @param nbrX Height of the labyrinth.
	 * @param nbrY Width of the labyrinth.
	 */
	public void drawFrame(Stage stage, int nbrX, int nbrY) 
	{
		_scene = new Scene(_pane, ((WALL + CELL) * nbrX + WALL) * SPAN, ((WALL + CELL) * nbrY + WALL) * SPAN);

		_scene.setFill(SCENE_COLOR);
		stage.setScene(_scene);

		Rectangle square = new Rectangle(0, 0, SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
		square.setFill(WALL_COLOR);
		_pane.getChildren().add(square);

		square = new Rectangle(0, SPAN * (nbrY * (CELL + WALL)), SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
		square.setFill(WALL_COLOR);
		_pane.getChildren().add(square);

		square = new Rectangle(0, 0, WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
		square.setFill(WALL_COLOR);
		_pane.getChildren().add(square);

		square = new Rectangle(SPAN * (nbrX * (CELL + WALL)), 0, WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
		square.setFill(WALL_COLOR);
		_pane.getChildren().add(square);

		for (int x = 0; x < nbrX - 1; ++x) 
		{
			int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
			for (int y = 0; y < nbrY - 1; ++y) {
				int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
				square = new Rectangle(offsetX, offsetY, WALL * SPAN, WALL * SPAN);
				square.setFill(WALL_COLOR);
				_pane.getChildren().add(square);
			}
		}
	}

	/**
	 * Draw a line between two points.
	 * @param xs abscissa of the first point.
	 * @param ys Ordinate of the first point.
	 * @param xt Abscissa of the second point.
	 * @param yt Ordinate of the second point.
	 * @param color Color of the wall.
	 */
	public void drawWall(int xs, int ys, int xt, int yt, Paint color) 
	{
		int x = 0, y = 0, xspan = 0, yspan = 0;
		if (ys == yt) 
		{
			x = ((WALL + CELL) + (WALL + CELL) * ((int) (xs + xt) / 2)) * SPAN;
			y = (WALL + ys * (WALL + CELL)) * SPAN;
			xspan = WALL * SPAN;
			yspan = CELL * SPAN;
			Rectangle square = new Rectangle(x, y, xspan, yspan);
			square.setFill(color);
			_pane.getChildren().add(square);
		} 
		else if (xs == xt) 
		{
			x = (WALL + xs * (WALL + CELL)) * SPAN;
			y = ((WALL + CELL) + (WALL + CELL) * ((int) (ys + yt) / 2)) * SPAN;
			xspan = CELL * SPAN;
			yspan = WALL * SPAN;
			Rectangle square = new Rectangle(x, y, xspan, yspan);
			square.setFill(color);
			_pane.getChildren().add(square);
		}
	}
}
