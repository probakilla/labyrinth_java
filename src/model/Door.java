package model;

/**
 *
 * @author Java Group
 */
public class Door
{
    
    private final Vertex _switchOn, _switchOff;
    private final Edge _door;
    private final static String SWITCH_ON_PATH = "/utils/button_open.png";
    private final static String SWITCH_OFF_PATH = "/utils/button_close.png";
    private final static String DOOR_PATH = "/utils/door_open.png";
    
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
     * @return the SWITCH_ON_PATH
     */
    public static String getSwitchOnPath()
    {
        return SWITCH_ON_PATH;
    }
    
    /**
     * @return the SWITCH_OFF_PATH
     */
    public static String getSwitchOffPath()
    {
        return SWITCH_OFF_PATH;
    }
    
    /**
     *
     * @return the _doorPath
     */
    public static String get_doorPath() {
        return DOOR_PATH;
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
