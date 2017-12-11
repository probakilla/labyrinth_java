package model;

/**
 * Class of the doors in the labyrinth, it also manage the switches.
 *
 * @author Java Group
 */
public class Door
{

    private final Vertex _switchOn, _switchOff;
    private final Edge _door;
    private final static String SWITCH_ON_PATH = "/utils/button_open.png";
    private final static String SWITCH_OFF_PATH = "/utils/button_close.png";

    /**
     * Create a door and the switches linked to it.
     *
     * Create a door from an {@link model.Edge Edge}, and the two switches from
     * two {@link model.Vertex Vertices}.
     *
     * @param switchOn A {@link model.Vertex Vertex} corresponding to the
     * coordinates of the switch which will open the door.
     * @param switchOff A {@link model.Vertex Vertex} corresponding to the
     * coordinates of the switch which will close the door.
     * @param door An {@link model.Edge Edge} where the door will be set.
     */
    public Door(Vertex switchOn, Vertex switchOff, Edge door)
    {
        _switchOn = switchOn;
        _switchOff = switchOff;
        _door = door;
    }

    /**
     * Getter on the {@link model.Edge Edge} corresponding to the door location.
     *
     * @return The {@link model.Edge Edge} of the door.
     */
    public Edge getDoor()
    {
        return _door;
    }

    /**
     * Retrieves a {@link java.String String} corresponding to the path of the
     * image for the switch used to open a door.
     *
     * @return The path to image of the switch.
     */
    public static String getSwitchOnPath()
    {
        return SWITCH_ON_PATH;
    }

    /**
     * Retrieves a {@link java.String String} corresponding to the path of the
     * image for the switch used to close a door.
     *
     * @return The path to image of the switch.
     */
    public static String getSwitchOffPath()
    {
        return SWITCH_OFF_PATH;
    }

    /**
     * Retrieves the {@link model.Vertex Vertex} corresponding to the location
     * of the switch used to open the door.
     *
     * @return The position of the switch in a {@link model.Vertex Vertex}.
     */
    public Vertex getSwitchOn()
    {
        return _switchOn;
    }

    /**
     * Retrieves the {@link model.Vertex Vertex} corresponding to the location
     * of the switch used to close the door.
     *
     * @return The position of the switch in a {@link model.Vertex Vertex}.
     */
    public Vertex getSwitchOff()
    {
        return _switchOff;
    }
}
