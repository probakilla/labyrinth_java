package model;

/**
 * Interface for collectable objects in the labyrinth.
 * 
 * @author Java Group
 */
public interface Candy
{
    
    /**
     * Retrieves the type of the candy.
     * 
     * @return The type of the candy.
     */
    int getType ();
    
    /**
     * Retrieves the position of the {@link model.Candy Candy} in a 
     * {@link model.Vertex Vertex}.
     * 
     * @return A {@link model.Vertex Vertex} Corresponding to his position.
     */
    Vertex getPosition ();

}
