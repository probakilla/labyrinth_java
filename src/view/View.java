package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * This class manage the display of the project.
 *
 * @author Java Group
 */
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

    /**
     * Retrives an instance of View.
     * 
     * Retrives the instance of the View, there can be only one instance
     * of the View at once thanks to the singleton design pattern.
     * @return Instance of View.
     */
    public static View getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new View();
        }
        return INSTANCE;
    }

    /**
     * Start the View.
     * 
     * @param stage Stage used for the graphical interface.
     */
    public void start(Stage stage)
    {
        _stage = stage;
        _pane = new Pane();

        drawFrame(stage, 16, 16);

        drawWall(1, 1, 2, 1, WALL_COLOR);
        stage.show();
    }

    /**
     * Draw the labyrinth basis.
     *
     * Draw the main structure of the labyrinth (e.g borders of the labyrinth).
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
            for (int y = 0; y < nbrY - 1; ++y)
            {
                int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
                square = new Rectangle(offsetX, offsetY, WALL * SPAN, WALL * SPAN);
                square.setFill(WALL_COLOR);
                _pane.getChildren().add(square);
            }
        }
    }

    /**
     * Draw a line between two points.
     * 
     * This method is used to build walls in the labyrinth. Draw a line
     * between two points given in parameters (with their coordinates). Color
     * of the wall is also set by this method.
     * 
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
        } else if (xs == xt)
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
