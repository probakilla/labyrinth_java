package exceptions;

/**
 * Exception for handling the wrong movements of the different characters.
 *
 * @author Java Group
 */
public class WrongMoveException extends Exception
{
    public WrongMoveException (String str)
    {
        super(str);
    }

    /**
     * Override of the super getMessage function.
     *
     * Print a message when the player tries to do an invalid movement.
     */
    public void printMessage ()
    {
        System.err.println(getMessage());
    }
}
