package model;

public class Vertex 
{

	private int _x, _y;

	public Vertex (int x, int y)
	{
		_x = x;
		_y = y;
	}

	public int getX() 
	{
		return _x;
	}

	public void setX(int x) 
	{
		_x = x;
	}

	public int getY() 
	{
		return _y;
	}

	public void setY(int y) 
	{
		_y = y;
	}
	
	public int compareTo (Vertex v)
	{
		if (v.getX() == _x && v.getY() == _y)
			return 0;
		return 1;
	}
	
	public boolean inBorders(Model.Directions dir) {
		return false;
	}
}
