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
     * @return A {@link model.Candy Candy} with specific coordinates.
     */
    public static Candy getCandy()
    {
        try
        {
            Random rd = new Random();
            int[] candies = new int[]
            {
                2, 3, 5, 10
            };
            int candyType = candies[rd.nextInt(4)];
            int x = rd.nextInt(16);
            int y;
            // Pour ne pas mettre de bonbon en 0.0 (sur le joueur).
            if (x == 0)
                y = rd.nextInt(15) + 1;
            else
                y = rd.nextInt(16);
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

    /**
     * Check if the position of the {@link model.Candy Candy} we want to put is
     * not already used by another {@link model.Candy Candy}.
     * 
     * @param candyList The array containing all {@link model.Candy Candies}.
     * @param candy 
     * @return 
     */
    public static boolean correctCandyPosition(AbstractCandy[] candyList, AbstractCandy candy)
    {
        int i = 0;
        while (candyList[i] != null)
        {
            if (candy.getPosition().equals(candyList[i].getPosition()))
                return false;
            ++i;
        }
        return true;
    }
}
