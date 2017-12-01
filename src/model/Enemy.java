package model;

import exceptions.WrongMoveException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 * Class used to define an enemy.
 *
 * TYPE is the "id" of the class and it's used to manage collisions. When an
 * enemy and a player collide, the addition of their type should be 0. If this
 * is a candy, it will be something else, but positive.
 *
 * @author Java Group
 */
public class Enemy extends AbstractCharacter implements Runnable
{
    private static final int TYPE = -1;
    private int _running;

    /**
     * Constructor of Enemy.
     *
     * Retrieves and instance of Enemy with randomized coordinates.
     */
    public Enemy()
    {
        this.randomizePosition();
        _imageFile = new Image("file:../../bad.png");
        _image.setImage(_imageFile);
        _running = 0;
    }

    /**
     * Constructor of Enemy.
     *
     * Retrieves an instance of Enemy with specific coordinates.
     *
     * @param x Abscissa in the labyrinth of the Enemy.
     * @param y Ordinate in the labyrinth of the Enemy.
     */
    public Enemy(int x, int y)
    {
        super();
        _position.setX(x);
        _position.setY(y);
        _imageFile = new Image("../../bad.png");
        _image.setImage(_imageFile);
        _running = 0;
    }

    public int type()
    {
        return TYPE;
    }

    public void stopRunning()
    {
        _running = 0;
    }


    // Pour l'instant le perso bouge au pif, mais on pourra changer ça.
    @Override
    public void run()
    {
        _running = 1;
        Random rand = new Random();
        int max, min, rd;
        max = 3;
        min = 0;
        while (_running == 1)
        {
            // Formule trouvée sur internet pour générer des nombres entre min et
            // max inclus.
            rd = rand.nextInt(max - min + 1) + min;
            try
            {
                switch (rd)
                {
                    case 0:
                        this.up();
                        break;
                    case 1:
                        this.down();
                        break;
                    case 2:
                        this.left();
                        break;
                    case 3:
                        this.right();
                        break;
                    default:
                        break;
                }
            }
            catch (WrongMoveException exception)
            {
                exception.printMessage();
            }

            try
            {
                Enemy.sleep(1000);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
