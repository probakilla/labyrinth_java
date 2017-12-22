package model;

import java.util.ArrayList;

/**
 * Class of doors from the labyrinth, it also manage the switches.
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
     * Retrieves a string corresponding to the path of the
     * image for the switch used to open a door.
     *
     * @return The path to image of the switch.
     */
    public static String getSwitchOnPath()
    {
        return SWITCH_ON_PATH;
    }

    /**
     * Retrieves a string corresponding to the path of the
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
    
    /**
     * Check if the position of the actual switch we want to put is
     * not already used by another Switch or {@link model.Candy Candy} or the exit door or the {@link model.PlayableCharacter player} himself.
     * 
     * @param _closedDoor The array containing all {@link model.Door closed door}.
     * @param candyList The array containing all {@link model.Candy Candies}.
     * @param actualSwitch The switch to be tested
     * @param exitDoor The {@link model.Vertex vertex} of the exit door.
     * @return True, if there is nothing else, except {@link model.Enemy enemies}, at the same position, otherwise return false.
     */
    public static boolean correctSwitchPosition (ArrayList<Door> _closedDoor, AbstractCandy[] candyList, Vertex actualSwitch, Vertex exitDoor)
    {
    	//Check if the actual Switch is on the player position or the exit position.
    	if (actualSwitch.equals(new Vertex (0, 0)) || actualSwitch.equals(exitDoor))
    		return false;
    	int i;
    	int length = _closedDoor.size();
    	 for (i = 0; i < length; i++)
         {
             if (actualSwitch.equals(_closedDoor.get(i).getSwitchOn()) || actualSwitch.equals(_closedDoor.get(i).getSwitchOff()))
                 return false;
         }
    	length = candyList.length;
    	for (i = 0; i < length; i++)
         {
    		if (candyList[i] == null)
    			return true;
             if (actualSwitch.equals(candyList[i].getPosition()))
                 return false;
             
         }
    	return true;
    }
}
