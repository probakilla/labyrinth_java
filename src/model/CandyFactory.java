package model;

import java.util.Random;

import exceptions.WrongCandyException;

/**
 * Factory of Candy.
 *
 *
 * @author Java Group
 */
public class CandyFactory
{
    /**
     * Retrieves a {@link model.Candy Candy} among 4 types of 
     * {@link model.Candy Candies}.
     *
     * @param candyType Define the type of {@link model.Candy Candy} to get in 
     * this list: 
     * 2 for a {@link model.RedCandy RedCandy}, 
     * 3 for a {@link model.PinkCandy PinkCandy}, 
     * 5 for a {@link model.RoundCandy RoundCandy}, 
     * 10 for a {@link model.Cherry Cherry}.
     * Set the coordinates of the {@link model.Candy Candy} with x and y 
     * parameters.
     * @param x The Abscissa of the {@link model.Candy Candy}.
     * @param y The ordinate of the {@link model.Candy Candy}.
     * @return A {@link model.Candy Candy} with specific coordinates.
     */
    public static Candy getCandy (int candyType, int x, int y)
    {
        try
        {
        	Random rd = new Random();
        	int[] candies = new int[] {2, 3, 5, 10};
        	candyType = candies[rd.nextInt(4)];
        	x = 1 + rd.nextInt(15);
        	y = 1 + rd.nextInt(15);
            switch (candyType)
            {
                case 2:
                    return new RedCandy(x, y);
                case 3:
                    return new PinkCandy(x, y);
                case 5:
                    return new RoundCandy(x, y);
                case 10:
                    return new Cherry(x, y);
                default:
                    throw new WrongCandyException("Wrong Candy");
            }
        }
        catch (WrongCandyException e)
        {
            e.printMessage();
            return null;
        }
    }
}
