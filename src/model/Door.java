package model;

/**
 *
 * @author Java Group
 */
public class Door
{
   
    private final Vertex _switchOn, _switchOff;
    private final Edge _door;
    private static String _switchOnPath = "/utils/button_open.png";
    private static String _switchOffPath = "/utils/button_close.png";
    private static String _doorPath = "/utils/door_open.png";

	public Door (Vertex switchOn, Vertex switchOff, Edge door)
    {
        _switchOn = switchOn;
        _switchOff = switchOff;
        _door = door;
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
     * 
     * @return the _doorPath
     */
    public static String get_doorPath() {
		return _doorPath;
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
