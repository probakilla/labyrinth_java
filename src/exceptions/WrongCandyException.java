package exceptions;

/**
 *
 * @author Java Group
 */
public class WrongCandyException extends Exception
{
    public WrongCandyException (String str)
    {
        super(str);
    }
    
    /**
     * Display the type of all candies.
     */
    public void printMessage ()
    {
        System.err.println ("Wrong Candy selected");
        System.err.println ("Possible Candy are 2, 3, 5 & 10");
    }
}
