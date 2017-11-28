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
     * @return Return a message in case of wrong movement.
     */
    @Override
    public String getMessage ()
    {
        return "Je ne peux pas aller par là";
    }
}
