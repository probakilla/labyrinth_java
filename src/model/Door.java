package model;

/**
 *
 * @author Java Group
 */
public class Door
{
   
    private final Vertex _switchOn, _switchOff;
    private final Edge _door;
    private static String _switchOnPath, _switchOffPath;
    
    public Door (Vertex switchOn, Vertex switchOff, Edge door)
    {
        _switchOn = switchOn;
        _switchOff = switchOff;
        _door = door;
        _switchOnPath = "XD";
        _switchOffPath = "LMAO";
    }    
   
       
    public Edge getDoor ()
    {
        return _door;
    }

    /**
     * @return the _switchOnPath
     */
    public static String getSwitchOnPath()
    {
        return _switchOnPath;
    }

    /**
     * @return the _switchOffPath
     */
    public static String getSwitchOffPath()
    {
        return _switchOffPath;
    }

    /**
     * @return the _switchOn
     */
    public Vertex getSwitchOn()
    {
        return _switchOn;
    }

    /**
     * @return the _switchOff
     */
    public Vertex getSwitchOff()
    {
        return _switchOff;
    }
}
